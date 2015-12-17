package com.example.heejung.payday_2;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AlarmActivity extends AppCompatActivity {
    private ArrayList<com.example.heejung.payday_2.Item> arr;
    private com.example.heejung.payday_2.adapter adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Payday");//어플이름
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//홈아이콘
        List();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.chat) {
            Toast.makeText(this, "채팅", Toast.LENGTH_SHORT).show();
            Intent ch = new Intent(getApplicationContext(), ChatActivity.class);
            startActivity(ch);
            return true;
        }


        if (id == R.id.search) {
            Toast.makeText(this, "검색", Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == android.R.id.home){
            Toast.makeText(this, "홈아이콘 이벤트", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void List() {
        arr = new ArrayList<com.example.heejung.payday_2.Item>();
        ListView list = (ListView) findViewById(R.id.view2);
        arr.add(new com.example.heejung.payday_2.Item("자료구조 구매 신청하였습니다.", "박희정"));
        arr.add(new com.example.heejung.payday_2.Item("그래픽스 배송되었습니다.", "김현지"));
        arr.add(new com.example.heejung.payday_2.Item("컴퓨터구조 거래완료되었습니다.","이형준"));
        arr.add(new com.example.heejung.payday_2.Item("안드로이드 거래취소 되었습니다.", "박원상"));
        arr.add(new com.example.heejung.payday_2.Item("프로젝트 책이 등록되었습니다.","학생1"));
        arr.add(new com.example.heejung.payday_2.Item("1", "111111111"));
        arr.add(new com.example.heejung.payday_2.Item("2", "222222222"));
        arr.add(new com.example.heejung.payday_2.Item("3", "3333333333"));
        arr.add(new com.example.heejung.payday_2.Item("4", "4444444444"));
        arr.add(new com.example.heejung.payday_2.Item("5", "5555555555,"));
        adap = new com.example.heejung.payday_2.adapter(AlarmActivity.this, arr);
        list.setAdapter(adap);
        list.setDivider(null); //구분선을 없에고 싶으면 null 값을 set합니다.

    }

}
