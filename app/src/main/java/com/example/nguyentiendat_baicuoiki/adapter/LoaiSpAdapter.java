package com.example.nguyentiendat_baicuoiki.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.nguyentiendat_baicuoiki.R;
import com.example.nguyentiendat_baicuoiki.model.ProductCategory;
import com.squareup.picasso.Picasso;



import java.util.ArrayList;

public class LoaiSpAdapter extends BaseAdapter {

    ArrayList<ProductCategory> loaispArrayList;
    Context context;

    public LoaiSpAdapter(ArrayList<ProductCategory> loaispArrayList, Context context) {
        this.loaispArrayList = loaispArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return loaispArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return loaispArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView   txttenloaisp;
        ImageView  imgloaisp;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_category,null);
            viewHolder.txttenloaisp = (TextView) view.findViewById(R.id.textviewloaisp);
            viewHolder.imgloaisp = (ImageView) view.findViewById(R.id.imageviewloaisp);
            view.setTag(viewHolder);
        }else{
                viewHolder = (ViewHolder) view.getTag();
        }
        ProductCategory productCategory = (ProductCategory) getItem(i);
        viewHolder.txttenloaisp.setText(productCategory.getTenloaisp());
        Picasso.get().load(productCategory.getHinhanhloaisp())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imgloaisp);
        return view;
    }
}
