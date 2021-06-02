package com.example.nguyentiendat_baicuoiki.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.nguyentiendat_baicuoiki.MainActivity;
import com.example.nguyentiendat_baicuoiki.R;
import com.example.nguyentiendat_baicuoiki.adapter.CartAdapter;
import com.example.nguyentiendat_baicuoiki.ultil.CheckConnection;

import java.text.DecimalFormat;

public class Cart extends AppCompatActivity {

    ListView listviewCart;
    TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan;
    Button btntieptucmua;
    Toolbar toolbarcart;
    CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        ActionToolBar();
        CheckData();
        EventUtil();
        CatchOnItemListView();
        EventButton();
    }

    private void EventButton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size()>0){
                    Intent intent = new Intent(getApplicationContext(),ThongTinKhachHang.class);
                    startActivity(intent);
                }else{
                    CheckConnection.ShowToast(getApplicationContext(),"Giỏ hàng đang trống");
                }
            }
        });
    }

    private void CatchOnItemListView(){
        listviewCart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
                builder.setTitle("Bạn muốn xoá sản phẩm");
                builder.setMessage("Bạn có chắc chắn muốn xoá");
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (MainActivity.manggiohang.size()<=0){
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.manggiohang.remove(position);
                            cartAdapter.notifyDataSetChanged();
                            EventUtil();
                            if (MainActivity.manggiohang.size()<=0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else{
                                txtthongbao.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                EventUtil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Huỷ bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartAdapter.notifyDataSetChanged();
                        EventUtil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }
    // tinh tong so tien
    public static void EventUtil() {
        long tongtien = 0;
        for (int i=0; i<MainActivity.manggiohang.size();i++){
            tongtien +=MainActivity.manggiohang.get(i).getGiasp();

        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien)+"VND");
    }
    //an hien thong bao tinh trang gio hang
    private void CheckData() {
        if(MainActivity.manggiohang.size()<=0){
            cartAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);
            listviewCart.setVisibility(View.INVISIBLE);

        }else{
            cartAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);
            listviewCart.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarcart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarcart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        listviewCart = (ListView) findViewById(R.id.listview_giohang);
        txtthongbao = (TextView) findViewById(R.id.txtthongbao);
        txttongtien = (TextView) findViewById(R.id.txttongtien);
        btnthanhtoan = (Button) findViewById(R.id.buttonthanhtoan);
        btntieptucmua = (Button) findViewById(R.id.buttontieptucmuahang);
        toolbarcart = (Toolbar) findViewById(R.id.toolbar_cart);
        cartAdapter = new CartAdapter(Cart.this, MainActivity.manggiohang);
        listviewCart.setAdapter(cartAdapter);

    }
}