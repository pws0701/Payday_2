package com.example.heejung.payday_2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class CategoryActivity extends AppCompatActivity  {

    ListView listView;
    myAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        listView = (ListView)findViewById(R.id.listView);
        adapter = new myAdapter();
        listView.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onClick_sResult(View view)
    {
        Intent sresult = new Intent(getApplicationContext(), aSearchActivity.class);
        startActivity(sresult);
    }

    public void onClick_schat(View view)
    {
        Intent schat = new Intent(getApplicationContext(), ChatActivity.class);
        startActivity(schat);
    }

    public void onClick_salarm(View view)
    {
        Intent salarm = new Intent(getApplicationContext(), AlarmActivity.class);
        startActivity(salarm);
    }




    class myAdapter extends BaseAdapter{

        String[] items = {"건국대학교", "세종대학교", "고려대학교",
                "연세대학교", "서울대학교"};
        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public Object getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = new TextView(getApplicationContext());
            view.setText(items[position]);
            view.setTextSize(40.0f);
            String strColor = "#000000";
            view.setTextColor(Color.parseColor(strColor));
            return view;
        }
    }
}
