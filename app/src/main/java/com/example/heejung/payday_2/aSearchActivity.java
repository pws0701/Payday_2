package com.example.heejung.payday_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.support.v7.widget.Toolbar;

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

public class aSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    final String urlMarket = "https://thepayday.co.kr/app/query/query.market.list.php";

    Context mContext = this;
    String str;
    public ArrayList<Market> arrMarket = new ArrayList<Market>();

    private ListView myList;
    private SearchView searchView;
    private SearchHelper mDbHelper;
    private aSearchAdapter defaultAdapter;
    private ArrayList<String> nameList;

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
                                if(jsonObject.has("minSellPrice")){
                                    market.setMinSellPrice(jsonObject.getString("minSellPrice"));
                                }
                                arrMarket.add(market);
                            }
                            for(int i=0; i<arrMarket.size(); i++) {
                                nameList.add(arrMarket.get(i).getTitle().toString());
                            }
                            myList = (ListView) findViewById(R.id.list);

                            defaultAdapter = new aSearchAdapter(aSearchActivity.this, nameList);
                            myList.setAdapter(defaultAdapter);

                            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent book = new Intent(aSearchActivity.this, book_detail_page.class);
                                    book.putExtra("BookName", arrMarket.get(position).getTitle().toString());
                                    Toast.makeText(aSearchActivity.this,nameList.get(position),Toast.LENGTH_SHORT).show();
                                    book.putExtra("Writer", arrMarket.get(position).getAuthor().toString());
                                    book.putExtra("Publisher", arrMarket.get(position).getPublisher().toString());
                                    book.putExtra("ISBN", arrMarket.get(position).getBookId().toString());
                                    book.putExtra("Price", arrMarket.get(position).getPrice().toString());

                                    startActivity(book);

                                    //  startActivity(new Intent( BookDetailPage.class));
                                }
                            });

                            for (String name : nameList) {
                                mDbHelper.createList(name);
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
                params.put("page", "1");
                params.put("count", "200");

                return params;
            }
        };// 요청에 대한 태그 지정, 취소할 때 태그를 이용하면 된다.
        stringRequest1.setTag("testTAG");// 3) 생성한 StringRequest를 RequestQueue에 추가, 순차적으로 진행된다.
        VolleySingleton.getInstance(mContext).getRequestQueue().add(stringRequest1);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a_search);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arrMarket = new ArrayList<Market>();
        nameList = new ArrayList<String>();
        getHttp();
        myList = (ListView) findViewById(R.id.list);

        //relate the listView from java to the one created in xml
        myList = (ListView) findViewById(R.id.list);

        //show the ListView on the screen
        // The adapter MyCustomAdapter is responsible for maintaining the data backing this nameList and for producing
        // a view to represent an item in that data set.
        defaultAdapter = new aSearchAdapter(this, nameList); //수정
        myList.setAdapter(defaultAdapter);

        //prepare the SearchView
        searchView = (SearchView) findViewById(R.id.asearch);

        //Sets the default or resting state of the search field. If true, a single search icon is shown by default and
        // expands to show the text field and other buttons when pressed. Also, if the default state is iconified, then it
        // collapses to that state when the close button is pressed. Changes to this property will take effect immediately.
        //The default value is true.
        searchView.setIconifiedByDefault(false);

        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        mDbHelper = new SearchHelper(this);
        mDbHelper.open();

        //Clear all names
        mDbHelper.deleteAllNames();

        // Create the list of names which will be displayed on search
        for (String name : nameList) {
            mDbHelper.createList(name);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mDbHelper  != null) {
            mDbHelper.close();
        }
    }

    @Override
    public boolean onClose() {
        myList.setAdapter(defaultAdapter);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        displayResults(query + "*");
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!newText.isEmpty()){
            displayResults(newText + "*");
        } else {
            myList.setAdapter(defaultAdapter);
        }

        return false;
    }

    /**
     * Method used for performing the search and displaying the results. This method is called every time a letter
     * is introduced in the search field.
     *
     * @param query Query used for performing the search
     */
    private void displayResults(String query) {

        Cursor cursor = mDbHelper.searchByInputText((query != null ? query : "@@@@"));

        if (cursor != null) {

            String[] from = new String[] {SearchHelper.COLUMN_NAME};

            // Specify the view where we want the results to go
            int[] to = new int[] {R.id.search_result_text_view};

            // Create a simple cursor adapter to keep the search data
            SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.result_search_item, cursor, from, to);
            myList.setAdapter(cursorAdapter);

            // Click listener for the searched item that was selected
            myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // Get the cursor, positioned to the corresponding row in the result set
                    Cursor cursor = (Cursor) myList.getItemAtPosition(position);

                    // Get the state's capital from this row in the database.
                    String selectedName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    Toast.makeText(aSearchActivity.this, selectedName, Toast.LENGTH_SHORT).show();

                    // Set the default adapter
                    //myList.setAdapter(defaultAdapter);

                    // Find the position for the original list by the selected name from search
                    for (int pos = 0; pos < nameList.size(); pos++) {
                        if (nameList.get(pos).equals(selectedName)) {
                            position = pos;
                            break;
                        }
                    }

                    Intent book = new Intent(aSearchActivity.this, book_detail_page.class);
                    book.putExtra("BookName", arrMarket.get(position).getTitle().toString());
                  //  Toast.makeText(aSearchActivity.this,nameList.get(position),Toast.LENGTH_SHORT).show();
                    book.putExtra("Writer", arrMarket.get(position).getAuthor().toString());
                    book.putExtra("Publisher", arrMarket.get(position).getPublisher().toString());
                    book.putExtra("ISBN", arrMarket.get(position).getBookId().toString());
                    book.putExtra("Price", arrMarket.get(position).getPrice().toString());

                    startActivity(book);

                    // Create a handler. This is necessary because the adapter has just been set on the list again and
                    // the list might not be finished setting the adapter by the time we perform setSelection.
                    /**Handler handler = new Handler();
                    final int finalPosition = position;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            myList.setSelection(finalPosition);
                        }
                    });**/

                   // searchView.setQuery("", true);
                }
            });

        }
    }
}
