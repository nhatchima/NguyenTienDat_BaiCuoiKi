package com.example.nguyentiendat_baicuoiki;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyentiendat_baicuoiki.activity.LienHeActivity;
import com.example.nguyentiendat_baicuoiki.activity.SRMActivity;
import com.example.nguyentiendat_baicuoiki.activity.SuaTamActivity;
import com.example.nguyentiendat_baicuoiki.activity.ThongTinActivity;
import com.example.nguyentiendat_baicuoiki.activity.YeuThichActivity;
import com.example.nguyentiendat_baicuoiki.adapter.LoaiSpAdapter;
import com.example.nguyentiendat_baicuoiki.adapter.ProductTitleAdapter;
import com.example.nguyentiendat_baicuoiki.adapter.SpAdapter;
import com.example.nguyentiendat_baicuoiki.model.Cart;
import com.example.nguyentiendat_baicuoiki.model.Product;
import com.example.nguyentiendat_baicuoiki.model.ProductCategory;
import com.example.nguyentiendat_baicuoiki.model.ProductsTitle;
import com.example.nguyentiendat_baicuoiki.ultil.CheckConnection;
import com.example.nguyentiendat_baicuoiki.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    CheckBox checkBox;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView, recyclerViewTitle;
    NavigationView navigationView;
    ListView listView;
    DrawerLayout drawerLayout;
    ArrayList<ProductCategory> productCategoryArrayList;
    ArrayList<Product> productNewArrayList;
    SpAdapter spAdapter;
    LoaiSpAdapter loaiSpAdapter;

    int id=0;
    String loaisanpham ="";
    String hinhanhsanpham= "";
    ProductTitleAdapter productTitleAdapter;

    public static ArrayList<Cart> manggiohang;
    public SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        //them san pham vao recycler view title
        List<ProductsTitle> productsList = new ArrayList<>();
        productsList.add(new ProductsTitle(1, "Gel sữa tăm Châu Phi","350ml" ,"300.000", R.drawable.prod1));
        productsList.add(new ProductsTitle(1, "Sữa tắm hoa anh đào","250ml" ,"250.000",R.drawable.prod2));
        productsList.add(new ProductsTitle(1, "Gel sữa tăm Châu Phi","220ml" ,"150.000",R.drawable.prod1));
        productsList.add(new ProductsTitle(1, "Sữa tắm hoa anh đào","450ml" ,"300.000",R.drawable.prod2));
        productsList.add(new ProductsTitle(1, "Gel sữa tăm Châu Phi","150ml" ,"150.000",R.drawable.prod1));

        setProdItemrecycler(productsList);
        //kiem tra ket noi internet roi moi goi toi cac ham
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewFlipper();
            GetDulieuLoaiSp();
            GetDuLieuSpMoiNhat();
            CatchOnItemListView();

        }else{
            CheckConnection.ShowToast(getApplicationContext(),"Check Internet Connection")  ;
            finish();
        }
    }
    // gan menu cho toolbar de hien thi icon gio hang va search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //tro toi giao dien menu
        getMenuInflater().inflate(R.menu.menu_cart,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView =(SearchView) menu.findItem(R.id.actionsearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                spAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                spAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    //click vao nut back trong search view de ve lan luot
    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    //click vao icon gio hang de sang activity gio hang
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.nguyentiendat_baicuoiki.activity.Cart.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    //su kien click cac thanh phan tren menu de sang mot activity moi
    private void CatchOnItemListView() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnection.ShowToast(getApplicationContext(),"Check Internet Connection");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, SRMActivity.class);
                            //truyen du lieu giua cac activity
                            intent.putExtra("idsanpham",productCategoryArrayList.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.ShowToast(getApplicationContext(),"Check Internet Connection");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, SuaTamActivity.class);
                            intent.putExtra("idsanpham",productCategoryArrayList.get(i).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.ShowToast(getApplicationContext(),"Check Internet Connection");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, LienHeActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnection.ShowToast(getApplicationContext(),"Check Internet Connection");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent = new Intent(MainActivity.this, ThongTinActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnection.ShowToast(getApplicationContext(),"Check Internet Connection");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    default:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                        Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                        startActivity(intent);
                    }else{
                        CheckConnection.ShowToast(getApplicationContext(),"Check Internet Connection");
                    }
                }
            }
        });
    }
    //lay du lieu san pham trong bang csdl de hien thi trong recycler view
    private void GetDuLieuSpMoiNhat() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.pathspmoinhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null) {
                    int ID = 0;
                    String tensanpham = "";
                    Integer giasanpham = 0;
                    String hinhanhsanpham = "";
                    String motasanpham = "";
                    int idsanpham = 0;
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            ID = jsonObject.getInt("id");
                            tensanpham = jsonObject.getString("tensanpham");
                            giasanpham = jsonObject.getInt("giasanpham");
                            hinhanhsanpham = jsonObject.getString("hinhanhsanpham");
                            motasanpham = jsonObject.getString("motasanpham");
                            idsanpham = jsonObject.getInt("idsanpham");
                            productNewArrayList.add(new Product(ID, tensanpham, giasanpham, hinhanhsanpham, motasanpham, idsanpham));
                            spAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            }
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    //lay du lieu loai san pham tu csdl de hien thi trong left menu
    private void GetDulieuLoaiSp(){
    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
    JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Server.pathloaisp, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            if(response!=null){
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject jsonObject=response.getJSONObject(i);
                        id=jsonObject.getInt("id");
                        loaisanpham=jsonObject.getString("tensanpham");
                        hinhanhsanpham=jsonObject.getString("hinhanhsanpham");
                        productCategoryArrayList.add(new ProductCategory(id,loaisanpham,hinhanhsanpham));
                        loaiSpAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                productCategoryArrayList.add(3,new ProductCategory(0,"Liên Hệ","https://i0.wp.com/s1.uphinh.org/2021/05/21/phone.jpg"));
                productCategoryArrayList.add(4,new ProductCategory(0,"Thông Tin","https://i0.wp.com/s1.uphinh.org/2021/05/21/information.png"));
                productCategoryArrayList.add(5,new ProductCategory(0,"Đăng Xuất","https://i0.wp.com/s1.uphinh.org/2021/05/23/exit.jpg"));
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
    requestQueue.add(jsonArrayRequest);
}
    // function cho view flipper
    private void ActionViewFlipper() {
        //khoi tao mang
        ArrayList<String> carousel = new ArrayList<>();
        //them du lieu vao mang
        carousel.add("https://i0.wp.com/s1.uphinh.org/2021/05/20/carouselthree.jpg");
        carousel.add("https://i0.wp.com/s1.uphinh.org/2021/05/20/carouselone.jpg");
        carousel.add("https://i0.wp.com/s1.uphinh.org/2021/05/19/dove.jpg");
        carousel.add("https://i0.wp.com/s1.uphinh.org/2021/05/20/carouseltwo.jpg");

        for (int i=0; i<carousel.size(); i++){
            //khoi tao bien imageview
            ImageView imageView = new ImageView(getApplicationContext());
            if(i==0){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://www.nivea.com.vn/"));
                        startActivity(intent);
                    }
                });
            } else if (i==1){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://www.chotinhcuaboo.com/products/gel-tam-japanese-cherry-blossom-shower-gel"));
                        startActivity(intent);
                    }
                });
            } else if(i==2) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://www.dove.com/vn/home.html"));
                        startActivity(intent);
                    }
                });
            } else{
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent= new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://sammishop.com/collections/dau-goi-shampoo"));
                        startActivity(intent);
                    }
                });
            }

            //doc du lieu tu online qua url lay tu trong mang khoi tao o tren roi truyen vao bien imageview
            Picasso.get().load(carousel.get(i)).into(imageView);
            //set kich co hinh anh
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //them hinh anh vao viewFlipper de chay
            viewFlipper.addView(imageView);

        }
        //set hieu ung chuyen canh va thoi gian chay
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_to_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }
    // set chuc nang cho thanh toolbar
    private void ActionBar() {
        //truyen vao thanh toolbar
        setSupportActionBar(toolbar);
        // hien thi nut back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //anh xa
    private void initView () {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewFlipper= (ViewFlipper) findViewById(R.id.view_flipper);
        checkBox = (CheckBox) findViewById(R.id.icon);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        navigationView= (NavigationView) findViewById(R.id.navigation_view);
        listView= (ListView) findViewById(R.id.listview_navigation);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        searchView = (SearchView) findViewById(R.id.actionsearch) ;
        //xu ly hien thi tren left menu
        productCategoryArrayList = new ArrayList<>();
        productCategoryArrayList.add(0,new ProductCategory(0,"Trang chủ","https://i0.wp.com/s1.uphinh.org/2021/05/21/home-icon.png"));
        loaiSpAdapter = new LoaiSpAdapter(productCategoryArrayList,getApplicationContext());
        listView.setAdapter(loaiSpAdapter);
        //xu ly hien thi trong muc san pham moi
        productNewArrayList = new ArrayList<>();
        spAdapter = new SpAdapter(getApplicationContext(),productNewArrayList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(spAdapter);
        if (manggiohang !=null){

        }else{
            manggiohang = new ArrayList<>();
        }
    }
    //function hien thi san pham ra view ngang
    private void setProdItemrecycler(List<ProductsTitle> productsList){
        recyclerViewTitle = findViewById(R.id.recyclerviewtitle);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);
        recyclerViewTitle.setLayoutManager(layoutManager);
        productTitleAdapter = new ProductTitleAdapter(this, productsList);
        recyclerViewTitle.setAdapter(productTitleAdapter);
    }
}