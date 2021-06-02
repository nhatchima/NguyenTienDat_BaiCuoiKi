package com.example.nguyentiendat_baicuoiki.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nguyentiendat_baicuoiki.R;

public class YeuThichActivity extends AppCompatActivity {
    Toolbar toolbaryeuthich;
    ImageView imgYeuthich;
    TextView txtten, txtgia, txtmota;
    CheckBox checkBox;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeu_thich);
        initView();
        ActionToolBar();
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbaryeuthich);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbaryeuthich.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView()     {
        toolbaryeuthich = (Toolbar) findViewById(R.id.toolbar_chitietsp);
        imgYeuthich = (ImageView) findViewById(R.id.imageview_chitietsp);
        txtten = (TextView) findViewById(R.id.txtchitietsp);
        txtmota = (TextView) findViewById(R.id.txtmotachitietsp);
        txtgia = (TextView) findViewById(R.id.txtgiachitietsp);
        checkBox = (CheckBox) findViewById(R.id.icon);
        listView = findViewById(R.id.listview_fav);


    }
}