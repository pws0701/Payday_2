package com.example.heejung.payday_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

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

public class book_detail_page extends AppCompatActivity {

    final String urlSeller = "https://thepayday.co.kr/app/query/query.market.php";
    Context mContext = this;
    public ArrayList<Seller> arrSeller = new ArrayList<Seller>();

    private TextView BookName;
    private TextView Writer;
    private TextView Publisher;
    private TextView ISBN;
    private TextView Price;

    private TextView DATEADD;
    private TextView UID;
    private TextView UNIVERSITY;
    private TextView SELLPRICE;
    private TextView TRADEMETHOD;

    String Dataadd;

    String Bookname;
    String writer;
    String publisher;
    String iSBN;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BookName = (TextView)findViewById(R.id.BookName);
        Writer = (TextView)findViewById(R.id.Writer);
        Publisher = (TextView)findViewById(R.id.Publisher);
        ISBN = (TextView)findViewById(R.id.ISBN);
        Price = (TextView)findViewById(R.id.Price);
        DATEADD =(TextView)findViewById(R.id.DATEADD);

        Intent get = getIntent();
        Bookname= get.getStringExtra("BookName");
        writer= get.getStringExtra("Writer");
        publisher= get.getStringExtra("Publisher");
        iSBN= get.getStringExtra("ISBN");
        price= get.getStringExtra("Price");

        BookName.setText(Bookname);
        Writer.setText(writer);
        Publisher.setText(publisher);
        ISBN.setText(iSBN);
        Price.setText(price);

        getHttp();

        DATEADD.setText(Dataadd);

        DATEADD = (TextView) findViewById(R.id.DATEADD);
        UID = (TextView) findViewById(R.id.UID);
        UNIVERSITY = (TextView) findViewById(R.id.UNIVERSITY);
        SELLPRICE = (TextView) findViewById(R.id.SELLPRICE);
        TRADEMETHOD = (TextView) findViewById(R.id.TRADEMETHOD);
    }

    public void getHttp(){
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, urlSeller,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        try {
                            JSONObject getObject = new JSONObject(response);
                            JSONArray jsonArray = getObject.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Seller seller = new Seller();
                                if (jsonObject.has("mBookId")) {
                                    seller.setmBookId(jsonObject.getString("mBookId"));
                                }
                                if (jsonObject.has("mMarketId")) {
                                    seller.setmMarketId(jsonObject.getString("mMarketId"));
                                }
                                if (jsonObject.has("mMarketStatus")) {
                                    seller.setmMarketStatus(jsonObject.getString("mMarketStatus"));
                                }
                                if (jsonObject.has("mSoldout")) {
                                    seller.setmSoldout(jsonObject.getString("mSoldout"));
                                }
                                if (jsonObject.has("mUid")) {
                                    seller.setmUid(jsonObject.getString("mUid"));
                                }
                                if (jsonObject.has("mUniversityId")) {
                                    seller.setmUniversityId(jsonObject.getString("mUniversityId"));
                                }
                                if (jsonObject.has("mQuantity")) {
                                    seller.setmQuantity(jsonObject.getString("mQuantity"));
                                }
                                if (jsonObject.has("mSellprice")) {
                                    seller.setmSellprice(jsonObject.getString("mSellprice"));
                                }
                                if (jsonObject.has("mTradeMethod")) {
                                    seller.setmTradeMethod(jsonObject.getString("mTradeMethod"));
                                }
                                if (jsonObject.has("mTradePlace")) {
                                    seller.setmTradePlace(jsonObject.getString("mTradePlace"));
                                }
                                if (jsonObject.has("mDescription")) {
                                    seller.setmDescription(jsonObject.getString("mDescription"));
                                }
                                if (jsonObject.has("mDateAdd")) {
                                    seller.setmDateAdd(jsonObject.getString("mDateAdd"));
                                }
                                arrSeller.add(seller);
                            }
                            for(int i=0; i<arrSeller.size(); i++) {

                                if(arrSeller.get(i).getmBookId().toString().equals(iSBN)){
                                    DATEADD.setText(arrSeller.get(i).getmDateAdd().toString());
                                    Dataadd=arrSeller.get(i).getmDateAdd().toString();
                                    UID.setText(arrSeller.get(i).getmUid().toString());
                                    UNIVERSITY.setText(arrSeller.get(i).getmUniversityId().toString());
                                    SELLPRICE.setText(arrSeller.get(i).getmSellprice().toString());
                                    TRADEMETHOD.setText(arrSeller.get(i).getmTradeMethod().toString());
                                }
                            }

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
                params.put("bookId", "9788998034986");

                return params;
            }
        };// 요청에 대한 태그 지정, 취소할 때 태그를 이용하면 된다.
        stringRequest1.setTag("testTAG");// 3) 생성한 StringRequest를 RequestQueue에 추가, 순차적으로 진행된다.
        VolleySingleton.getInstance(mContext).getRequestQueue().add(stringRequest1);
    }

    // 추가@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ 끝


}
