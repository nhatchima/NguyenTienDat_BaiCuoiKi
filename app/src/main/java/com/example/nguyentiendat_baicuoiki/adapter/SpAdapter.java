package com.example.nguyentiendat_baicuoiki.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import com.example.nguyentiendat_baicuoiki.R;
import com.example.nguyentiendat_baicuoiki.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SpAdapter extends RecyclerView.Adapter<SpAdapter.ItemHolder> implements Filterable {
    Context context;
    ArrayList<Product> arrayproduct;
    ArrayList<Product> marrayproduct;

    public SpAdapter(Context context, ArrayList<Product> arrayproduct) {
        this.context = context;
        this.arrayproduct = arrayproduct;
        this.marrayproduct = arrayproduct;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_sanphammoinhat,null);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
        Product product = arrayproduct.get(position);
        holder.txttensp.setText(product.getTensanpham());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.txtgiasp.setText("Giá: "+ decimalFormat.format(product.getGiasanpham())+" VNĐ");
        Picasso.get().load(product.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(holder.imghinhsp);

    }

    @Override
    public int getItemCount() {
        return arrayproduct.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String search = charSequence.toString();
                if(search.isEmpty()){
                    arrayproduct = marrayproduct;
                }else{
                    List<Product> list = new ArrayList<>();
                    for (Product product : marrayproduct){
                        if(product.getTensanpham().toLowerCase().contains(search.toLowerCase())){
                            list.add(product);
                        }
                    }
                    arrayproduct= (ArrayList<Product>) list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = marrayproduct;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                marrayproduct = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imghinhsp;
        public TextView txttensp, txtgiasp;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsp = itemView.findViewById(R.id.imageviewsp);
            txtgiasp = itemView.findViewById(R.id.txtgiasanpham);
            txttensp = itemView.findViewById(R.id.txttensanpham);
        }
    }
}
