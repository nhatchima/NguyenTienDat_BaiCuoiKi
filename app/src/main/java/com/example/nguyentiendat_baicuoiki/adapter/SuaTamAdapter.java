package com.example.nguyentiendat_baicuoiki.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nguyentiendat_baicuoiki.R;
import com.example.nguyentiendat_baicuoiki.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SuaTamAdapter extends BaseAdapter implements Filterable{

    Context context;
    ArrayList<Product> arraysuatam;
    ArrayList<Product> arraysuatamFilter;


    public SuaTamAdapter(Context context, ArrayList<Product> arraysuatam) {
        this.context = context;
        this.arraysuatam = arraysuatam;
        this.arraysuatamFilter = arraysuatam;
    }
    @Override
    public int getCount() {
        return arraysuatamFilter.size();
    }

    @Override
    public Object getItem(int i) {
        return arraysuatamFilter.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if(charSequence == null|| charSequence.length()==0){
                    filterResults.count = arraysuatam.size();
                    filterResults.values = arraysuatam;
                }else{
                    String searchStr = charSequence.toString().toLowerCase();
                    ArrayList<Product> resultData =new ArrayList<>();
                    for(Product product : arraysuatam){
                        if(product.getTensanpham().toLowerCase().contains(charSequence.toString().toLowerCase())){
                            resultData.add(product);
                        }

                    }
                    filterResults.count = resultData.size();
                    filterResults.values= resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                arraysuatamFilter = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

    public class ViewHolder {
        public TextView txttensuatam, txtgiasuatam, txtmotasuatam;
        public ImageView imgsuatam;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_suatam, null);
            viewHolder.txttensuatam = view.findViewById(R.id.textviewtensuatam);
            viewHolder.txtgiasuatam = view.findViewById(R.id.textviewgiasuatam);
            viewHolder.txtmotasuatam = view.findViewById(R.id.textviewmotasuatam);
            viewHolder.imgsuatam = view.findViewById(R.id.imageviewsuatam);
            view.setTag(viewHolder);
        } else {
            viewHolder = (SuaTamAdapter.ViewHolder) view.getTag();

        }
        Product product = (Product) getItem(i);
        viewHolder.txttensuatam.setText(product.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiasuatam.setText("Giá: " + decimalFormat.format(product.getGiasanpham()) + " VNĐ");
        viewHolder.txtmotasuatam.setMaxLines(3);
        viewHolder.txtmotasuatam.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotasuatam.setText(product.getMotasanpham());
        Picasso.get().load(product.getHinhanhsanpham())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgsuatam);
        return view;

    }

}