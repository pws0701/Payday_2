package com.example.heejung.payday_2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/* +버튼을 누르면 책 등록을 할 수 있도록 하는 액티비티 */
public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*책 등록시 판매정보 지역, 학교, 전공 List를 보여주는 스피너 */
        Spinner s_city = (Spinner) findViewById(R.id.city_spinner);
        Spinner s_univ = (Spinner) findViewById(R.id.univ_spinner);
        Spinner s_major = (Spinner) findViewById(R.id.major_spinner);

        ArrayAdapter adapter_city = ArrayAdapter.createFromResource(this,R.array.city,android.R.layout.simple_spinner_dropdown_item);
        s_city.setAdapter(adapter_city);

        ArrayAdapter adapter_univ = ArrayAdapter.createFromResource(this,R.array.univ,android.R.layout.simple_spinner_dropdown_item);
        s_univ.setAdapter(adapter_univ);

        ArrayAdapter adapter_major = ArrayAdapter.createFromResource(this,R.array.major,android.R.layout.simple_spinner_dropdown_item);
        s_major.setAdapter(adapter_major);

        EditText isbn = (EditText) findViewById(R.id.edit_isbn);
        EditText bookname = (EditText) findViewById(R.id.edit_bookname);
        EditText author = (EditText) findViewById(R.id.edit_author);
        EditText datepub = (EditText) findViewById(R.id.edit_datepub);
        EditText price = (EditText) findViewById(R.id.edit_price);
        EditText publisher = (EditText) findViewById(R.id.edit_publisher);
        EditText sellprice = (EditText) findViewById(R.id.edit_sellprice);

        Button regist_button = (Button) findViewById(R.id.sellregist);
        regist_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        /* 툴바에 있는 아이콘이 선택되었을 때, 액션을 지정해 준다. */
        int id = item.getItemId();

        switch (id)
        {
            case R.id.alarm:
                return true;

            case R.id.chat:
                return true;

            case R.id.search:
                return true;

            case android.R.id.home:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
