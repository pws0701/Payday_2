package com.example.heejung.payday_2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Join extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
         spin1.setOnItemSelectedListener(this);
         Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
         spin2.setOnItemSelectedListener(this);
         Spinner spin3 = (Spinner) findViewById(R.id.spinner3);
         spin3.setOnItemSelectedListener(this);

         ArrayAdapter<String> adapter = new ArrayAdapter<String>(
         this, android.R.layout.simple_spinner_item, items);
         adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

         spin1.setAdapter(adapter);
         **/

    }

}
