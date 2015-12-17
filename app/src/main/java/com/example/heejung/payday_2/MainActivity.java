package com.example.heejung.payday_2;

import com.example.heejung.payday_2.RecyclerViewAdapter.OnItemClickListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final String urlMarket = "https://thepayday.co.kr/app/query/query.market.list.php";
    final String urlBookImage = "http://image.kyobobook.co.kr/images/book/large/";

    public ArrayList<Market> arrMarket = new ArrayList<Market>();

    Context mContext = this;

    RecyclerView myRecyclerView;
    RecyclerViewAdapter myRecyclerViewAdapter;

    StaggeredGridLayoutManager staggeredGridLayoutManagerVertical;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton add_book_button = (FloatingActionButton) findViewById(R.id.add_book);
        add_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add_book_intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(add_book_intent);
            }
        });

        toolbar.setNavigationIcon(R.drawable.ic_home_white_24dp);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        myRecyclerView = (RecyclerView) findViewById(R.id.myrecyclerview);
        staggeredGridLayoutManagerVertical =
                new StaggeredGridLayoutManager(
                        2, //The number of Columns in the grid
                        LinearLayoutManager.VERTICAL);

        getHttp();


    }

    @Override
    public void onBackPressed(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void onClick_alarm(View view)
    {
        Intent alarm = new Intent(getApplicationContext(), AlarmActivity.class);
        startActivity(alarm);
    }

    public void onClick_chat(View view)
    {
        Intent chat = new Intent(getApplicationContext(), ChatActivity.class);
        startActivity(chat);
    }

    public void onClick_search(View view)
    {
        Intent search = new Intent(getApplicationContext(), CategoryActivity.class);
        startActivity(search);
    }


    public void join_click(View view)
    {
       Intent join = new Intent(getApplicationContext(), Join.class);
        startActivity(join);
    }

    public void login_click(View view)
    {
        setContentView(R.layout.activity_main_activity__l);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_home_white_24dp);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view2);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        return true;
    }

    public void getHttp(){
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, urlMarket,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject getObject = new JSONObject(response);
                            JSONArray jsonArray = getObject.getJSONArray("result");
                            for(int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Market market = new Market();
                                if(jsonObject.has("bBookId")){
                                    market.setBookId(jsonObject.getString("bBookId"));
                                    String tempURL = urlBookImage + jsonObject.getString("bBookId").substring(10, 13) + "/l" + jsonObject.getString("bBookId") + ".jpg";
                                    market.setBookImageUrl(tempURL);
                                }
                                if(jsonObject.has("bTitle")){
                                    market.setTitle(jsonObject.getString("bTitle"));
                                }
                                if(jsonObject.has("bTitleMinor")){
                                    market.setTitleMinor(jsonObject.getString("bTitleMinor"));
                                }
                                if(jsonObject.has("bAuthor")){
                                    market.setAuthor(jsonObject.getString("bAuthor"));
                                }
                                if(jsonObject.has("bPublisher")){
                                    market.setPublisher(jsonObject.getString("bPublisher"));
                                }
                                if(jsonObject.has("bEdition")){
                                    market.setEdition(jsonObject.getString("bEdition"));
                                }
                                if(jsonObject.has("bDatePub")){
                                    market.setDatePub(jsonObject.getString("bDatePub"));
                                }
                                if(jsonObject.has("bPrice")){
                                    market.setPrice(jsonObject.getString("bPrice"));
                                }
                                if(jsonObject.has("countSeller")){
                                    market.setCountSeller(jsonObject.getString("countSeller"));
                                }
                                if(jsonObject.has("minSellPrice")) {
                                    market.setMinSellPrice(jsonObject.getString("minSellPrice"));
                                }
                                arrMarket.add(market);
                            }
                            myRecyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, arrMarket);

                            myRecyclerView.setAdapter(myRecyclerViewAdapter);
                            myRecyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.e("response", response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error)
            {
                if (error instanceof TimeoutError){
                    Log.e("response", "TimeOutError");
                }// 네트워크 연결이 모두 끊어진 경우

                else if (error instanceof NoConnectionError){
                    Log.e("response", "NoConnectionError");
                }

                else if (error instanceof AuthFailureError){
                    Log.e("response", "AuthFailureError");
                }// 서버에러, URL에 해당 자료가 없어도 이곳이 불린다.

                else if (error instanceof ServerError){
                    Log.e("response", "ServerError");
                }

                else if (error instanceof NetworkError){
                    Log.e("response", "NetworkError");
                }

                else if (error instanceof ParseError){
                    Log.e("response", "ParseError");
                }
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("os", "android");
                params.put("version", "21");
                params.put("brand", "samsung");
                params.put("page", "1");
                params.put("count", "50");
                return params;
            }
        };// 요청에 대한 태그 지정, 취소할 때 태그를 이용하면 된다.

        stringRequest1.setTag("testTAG");// 3) 생성한 StringRequest를 RequestQueue에 추가, 순차적으로 진행된다.
        VolleySingleton.getInstance(mContext).getRequestQueue().add(stringRequest1);
    }
}
