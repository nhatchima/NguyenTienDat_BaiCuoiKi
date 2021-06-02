package com.example.nguyentiendat_baicuoiki.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyentiendat_baicuoiki.MainActivity;
import com.example.nguyentiendat_baicuoiki.R;
import com.example.nguyentiendat_baicuoiki.model.Cart;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {
    Context context;
    ArrayList<Cart> cartArrayList;

    public CartAdapter(Context context, ArrayList<Cart> cartArrayList) {
        this.context = context;
        this.cartArrayList = cartArrayList;
    }

    @Override
    public int getCount() {
        return cartArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return cartArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView texttengiohang, textgiagiohang;
        public ImageView imggiohang;
        public Button btnminus, btnvalues, btnplus;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder ;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.listview_cart,null);
            viewHolder.texttengiohang = (TextView) view.findViewById(R.id.txttengiohang);
            viewHolder.textgiagiohang = (TextView) view.findViewById(R.id.txtgiagiohang);
            viewHolder.imggiohang = (ImageView) view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus = (Button) view.findViewById(R.id.buttonminus);
            viewHolder.btnvalues = (Button) view.findViewById(R.id.buttonvalue);
            viewHolder.btnplus = (Button) view.findViewById(R.id.buttonplus);
            view.setTag(viewHolder);

        }else{
            viewHolder =(ViewHolder) view.getTag();
        }
        Cart giohang =(Cart) getItem(i);
        viewHolder.texttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.textgiagiohang.setText(String.valueOf(decimalFormat.format(giohang.getGiasp())+"VND"));
        Picasso.get().load(giohang.getHinhsp()).placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHolder.imggiohang);
        viewHolder.btnvalues.setText(giohang.getSoluongsp()+"");
        int sl = Integer.parseInt(viewHolder.btnvalues.getText().toString());
        if(sl>=10){
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }else if(sl<=1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }else if(sl>=1){
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        //xu ly logic cho viec an nut tang so luong thi so luong se tang va gia cung se thay doi
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(viewHolder.btnvalues.getText().toString())+1;
                int slht = MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat = (giaht * slmoinhat) /slht;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.textgiagiohang.setText(String.valueOf(decimalFormat.format(giamoinhat)+"VND"));
                com.example.nguyentiendat_baicuoiki.activity.Cart.EventUtil();
                if(slmoinhat > 9){
                    viewHolder.btnplus.setVisibility(View.INVISIBLE);
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                } else{
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }

            }
        });
        //xu ly logic cho viec an nut giam so luong thi so luong se giam va gia cung se thay doi
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(viewHolder.btnvalues.getText().toString())-1;
                int slht = MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giamoinhat = (giaht * slmoinhat) /slht;
                MainActivity.manggiohang.get(i).setGiasp(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                viewHolder.textgiagiohang.setText(String.valueOf(decimalFormat.format(giamoinhat)+"VND"));
                com.example.nguyentiendat_baicuoiki.activity.Cart.EventUtil();
                if(slmoinhat <2){
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnminus.setVisibility(View.INVISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                } else{
                    viewHolder.btnplus.setVisibility(View.VISIBLE);
                    viewHolder.btnminus.setVisibility(View.VISIBLE);
                    viewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
}
