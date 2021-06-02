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
import android.widget.TextView;

import com.example.nguyentiendat_baicuoiki.R;
import com.example.nguyentiendat_baicuoiki.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SrmAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<Product> arraysuaruamat;
    ArrayList<Product> arraysuaruamatFilter;


    public SrmAdapter(Context context, ArrayList<Product> arraysuaruamat) {
        this.context = context;
        this.arraysuaruamat = arraysuaruamat;
        this.arraysuaruamatFilter = arraysuaruamat;
        getFilter();
    }

    @Override
    public int getCount() {
        return arraysuaruamatFilter.size();
    }

    @Override
    public Object getItem(int i) {
        return arraysuaruamatFilter.get(i);
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
                    filterResults.count = arraysuaruamat.size();
                    filterResults.values = arraysuaruamat;
                }else{
                    String searchStr = charSequence.toString().toLowerCase();
                    ArrayList<Product> resultData =new ArrayList<>();
                    for(Product product : arraysuaruamat){
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
                arraysuaruamatFilter = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }


    public class ViewHolder{
        public TextView txttensuaruamat, txtgiasuaruamat, txtmotasuaruamat;
        public ImageView imgsuaruamat;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder= null;
        if(view==null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_suaruamat, null);
            viewHolder.txttensuaruamat = view.findViewById(R.id.textviewtensuaruamat);
            viewHolder.txtgiasuaruamat = view.findViewById(R.id.textviewgiasuaruamat);
            viewHolder.txtmotasuaruamat = view.findViewById(R.id.textviewmotasuaruamat);
            viewHolder.imgsuaruamat = view.findViewById(R.id.imageviewsuaruamat);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();

        }
        Product product = (Product) getItem(i);
        viewHolder.txttensuaruamat.setText(product.getTensanpham());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txtgiasuaruamat.setText("Giá: "+ decimalFormat.format(product.getGiasanpham())+" VNĐ");
        viewHolder.txtmotasuaruamat.setMaxLines(3);
        viewHolder.txtmotasuaruamat.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotasuaruamat.setText(product.getMotasanpham());
        Picasso.get().load(product.getHinhanhsanpham())
            .placeholder(R.drawable.noimage)
            .error(R.drawable.error)
            .into(viewHolder.imgsuaruamat);
        return view;
    }
}
