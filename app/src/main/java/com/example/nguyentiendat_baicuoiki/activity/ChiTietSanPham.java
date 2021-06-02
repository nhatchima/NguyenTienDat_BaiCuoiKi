package com.example.nguyentiendat_baicuoiki.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.nguyentiendat_baicuoiki.MainActivity;
import com.example.nguyentiendat_baicuoiki.R;
import com.example.nguyentiendat_baicuoiki.model.Cart;
import com.example.nguyentiendat_baicuoiki.model.Product;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;


public class ChiTietSanPham extends AppCompatActivity {

    Toolbar toolbarchitiet;
    ImageView imgChitiet;
    TextView txtten, txtgia, txtmota;
    Spinner spinner;
    Button buttonBuy;
    int id =0;
    String tenchitiet = "";
    int giachitiet = 0;
    String hinhanhchitiet ="";
    String motachitiet ="";
    int idsp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        initView();
        ActionToolBar();
        GetInformation();
        CatchEventSpinner();
        EventButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.nguyentiendat_baicuoiki.activity.Cart.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void EventButton() {
        buttonBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size()>0){
                    int sl = Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exist = false;
                    for (int i=0; i<MainActivity.manggiohang.size();i++){
                        if (MainActivity.manggiohang.get(i).getIdsp() == id){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                            if(MainActivity.manggiohang.get(i).getSoluongsp()>=10){
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(giachitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exist  = true;
                        }
                    }
                    if (exist ==false){
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi = soluong * giachitiet;
                        MainActivity.manggiohang.add(new Cart(id,tenchitiet,giamoi,hinhanhchitiet,soluong));
                    }
                }else{
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi = soluong * giachitiet;
                    MainActivity.manggiohang.add(new Cart(id,tenchitiet,giamoi,hinhanhchitiet,soluong));
                }
                Intent intent = new Intent(getApplicationContext(), com.example.nguyentiendat_baicuoiki.activity.Cart.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer [] {1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter= new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
     }

    private void GetInformation() {

        Product product = (Product) getIntent().getSerializableExtra("thongtinsanpham");
        id = product.getId();
        tenchitiet = product.getTensanpham();
        giachitiet = product.getGiasanpham();
        hinhanhchitiet = product.getHinhanhsanpham();
        motachitiet = product.getMotasanpham();
        idsp = product.getIdsanpham();
        txtten.setText(tenchitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgia.setText("Giá: "+ decimalFormat.format(giachitiet)+"VNĐ");
        txtmota.setText(motachitiet);
        Picasso.get().load(hinhanhchitiet).placeholder(R.drawable.noimage)
                    .error(R.drawable.error)
                    .into(imgChitiet);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView()     {
        toolbarchitiet = (Toolbar) findViewById(R.id.toolbar_chitietsp);
        imgChitiet = (ImageView) findViewById(R.id.imageview_chitietsp);
        txtten = (TextView) findViewById(R.id.txtchitietsp);
        txtmota = (TextView) findViewById(R.id.txtmotachitietsp);
        txtgia = (TextView) findViewById(R.id.txtgiachitietsp);
        spinner = (Spinner) findViewById(R.id.spinnerchitietsp);
        buttonBuy =(Button) findViewById(R.id.buttondatmua);

    }
}