package com.example.nguyentiendat_baicuoiki.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;



import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyentiendat_baicuoiki.R;
import com.example.nguyentiendat_baicuoiki.adapter.SrmAdapter;
import com.example.nguyentiendat_baicuoiki.model.Cart;
import com.example.nguyentiendat_baicuoiki.model.Product;
import com.example.nguyentiendat_baicuoiki.ultil.CheckConnection;
import com.example.nguyentiendat_baicuoiki.ultil.Server;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SRMActivity extends AppCompatActivity {

    Toolbar toolbarsrm;
    ListView listView;
    SrmAdapter srmAdapter;
    ArrayList<Product> mangsrm;
    int idsrm = 0;
    int page=1;
    View footerview;
    boolean isLoading = false;
    boolean limitData = false;
    mHandler mHandler;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srmactivity);
        initView();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            GetIdLoaiSanPham();
            ActionToolbar();
            GetData(page);
            LoadMoreData();

        }else{
            CheckConnection.ShowToast(getApplicationContext(),"Check Internet");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =(SearchView) menu.findItem(R.id.actionsearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                srmAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                srmAdapter.getFilter().filter(newText);
                return false;
            }
        });
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
    private void LoadMoreData() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",mangsrm.get(i));
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if(FirstItem + VisibleItem == TotalItem && TotalItem !=0 && isLoading== false && limitData==false){
                        isLoading =true;
                        ThreadData threadData = new ThreadData();
                        threadData.start();
                }
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.pathspsuaruamat + String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id =0;
                String tenSuaRuaMat ="";
                int giaSuaRuaMat = 0;
                String hinhanhSuaRuaMat ="";
                String motaSuaRuaMat ="";
                int Idsrm = 0 ;
                if (response!=null && response.length()!=2){
                    listView.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0; i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenSuaRuaMat = jsonObject.getString("tensanpham");
                            giaSuaRuaMat = jsonObject.getInt("giasanpham");
                            hinhanhSuaRuaMat= jsonObject.getString("hinhanhsanpham");
                            motaSuaRuaMat = jsonObject.getString("motasanpham");
                            Idsrm = jsonObject.getInt("idsanpham");
                            mangsrm.add(new Product(id,tenSuaRuaMat,giaSuaRuaMat,hinhanhSuaRuaMat,motaSuaRuaMat,Idsrm));
                            srmAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    limitData = true;
                    listView.removeFooterView(footerview);
                    CheckConnection.ShowToast(getApplicationContext(),"Loading Full");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String,String>();
                param.put("idsanpham",String.valueOf(idsrm));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarsrm);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarsrm.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    private void GetIdLoaiSanPham() {
        idsrm = getIntent().getIntExtra("idsanpham",-1);
        Log.d("giatriloaisanpham",idsrm+"");
    }

    private void initView() {
        toolbarsrm = (Toolbar) findViewById(R.id.toolbar_srm);
        listView = (ListView) findViewById(R.id.listview_srm);
        mangsrm = new ArrayList<>();
        srmAdapter = new SrmAdapter(getApplicationContext(),mangsrm);
        listView.setAdapter(srmAdapter);
        LayoutInflater inflater =  (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.processbar,null);
        mHandler = new mHandler();
    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listView.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}