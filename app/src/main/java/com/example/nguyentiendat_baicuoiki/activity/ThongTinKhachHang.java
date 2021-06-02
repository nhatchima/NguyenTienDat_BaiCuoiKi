package com.example.nguyentiendat_baicuoiki.activity;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyentiendat_baicuoiki.MainActivity;
import com.example.nguyentiendat_baicuoiki.R;
import com.example.nguyentiendat_baicuoiki.ultil.CheckConnection;
import com.example.nguyentiendat_baicuoiki.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHang extends AppCompatActivity {

    EditText txttenkh, txtemail, txtsodienthoai;
    Button buttonsubmit, buttonback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        initView();
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }else{
            CheckConnection.ShowToast(getApplicationContext(),"Check your internet");
        }
    }
    private void initView() {
        txttenkh = (EditText) findViewById(R.id.edttenkh);
        txtsodienthoai = (EditText) findViewById(R.id.edtsodienthoai);
        txtemail = (EditText) findViewById(R.id.edtemail);
        buttonsubmit = (Button) findViewById(R.id.btnsubmit);
        buttonback = (Button) findViewById(R.id.btnback);
    }
    private void EventButton() {
        buttonsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ten =txttenkh.getText().toString().trim();
                final String sdt =txtsodienthoai.getText().toString().trim();
                final String email =txtemail.getText().toString().trim();
                if(ten.length()>0 && sdt.length()>0 && email.length()>0){
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.pathdonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang",madonhang);
                            if (Integer.parseInt(madonhang)>0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.pathchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if(response.equals("1")){
                                            MainActivity.manggiohang.clear();
                                            CheckConnection.ShowToast(getApplicationContext(),"Thêm dữ liệu giỏ hàng thành công");
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToast(getApplicationContext(),"Đơn hàng của bạn đã được đặt thành công!");
                                        }else{
                                            CheckConnection.ShowToast(getApplicationContext(),"Thêm dữ liệu giở hàng không thành công");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0; i < MainActivity.manggiohang.size(); i++) {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang", madonhang);
                                                jsonObject.put("masanpham", MainActivity.manggiohang.get(i).getIdsp());
                                                jsonObject.put("tensanpham", MainActivity.manggiohang.get(i).getTensp());
                                                jsonObject.put("giasanpham", MainActivity.manggiohang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham", MainActivity.manggiohang.get(i).getSoluongsp());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap = new HashMap<String,String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String,String>();
                            hashMap.put("tenkhachhang",ten);
                            hashMap.put("sodienthoai",sdt);
                            hashMap.put("email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else{
                    CheckConnection.ShowToast(getApplicationContext(),"Check your information");
                }

            }
        });
    }


}
