package com.example.heejung.payday_2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;
import java.util.ArrayList;

/**
 * Created by Desktop on 2015-11-12.
 */
public class adapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Activity m_activity;
    private ArrayList<Item> arr;
    public adapter(Activity act, ArrayList<Item> arr_item) {
        this.m_activity = act;
        arr = arr_item;
        mInflater = (LayoutInflater)m_activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return arr.get(position);
    }
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            int res = 0;
            res = com.example.heejung.payday_2.R.layout.view;
            convertView = mInflater.inflate(res, parent, false);
        }
        TextView book_ = (TextView)convertView.findViewById(R.id.booktitle);
        TextView chat_ = (TextView)convertView.findViewById(R.id.chat_);
        LinearLayout layout = (LinearLayout)convertView.findViewById(R.id.view);
        book_.setText(arr.get(position).BookName);
        chat_.setText(arr.get(position).Chat);
        /*  버튼에 이벤트처리를 하기위해선 setTag를 이용해서 사용할 수 있습니다.
         *   Button btn 가 있다면, btn.setTag(position)을 활용해서 각 버튼들
          *   이벤트처리를 할 수 있습니다.   */

        layout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Goto(position);
            }
        });
        return convertView;
    }
    public  void Goto(int a) {
        Intent go = new Intent(m_activity, BlankActivity.class);
        //putExtra 로 선택한 아이템의 정보를 인텐트로 넘겨 줄 수 있다.
        go.putExtra("", arr.get(a).BookName);
        go.putExtra("", arr.get(a).Chat);
        m_activity.startActivity(go);
    }
}
