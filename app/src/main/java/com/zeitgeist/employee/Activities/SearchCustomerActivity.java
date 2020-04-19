package com.zeitgeist.employee.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.zeitgeist.employee.Adapters.SearchTypeAdapter;
import com.zeitgeist.employee.R;

import java.util.ArrayList;
import java.util.List;

public class SearchCustomerActivity extends AppCompatActivity {

    ImageButton backButton,searchBtn;
    TextView title,searchTypeText,searchTypeList;
    EditText searchData;
    Spinner searchType;
    Toolbar toolbar;
    Typeface typeface;
    List searchTypes = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        searchTypes.add("ID");
        searchTypes.add("Email");
        searchTypes.add("Phone");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");

        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setTypeface(typeface);
        title.setText("SEARCH CUSTOMER");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        backButton = (ImageButton) findViewById(R.id.back_btn);
        searchBtn = (ImageButton) findViewById(R.id.search_btn);
        searchTypeText = (TextView) findViewById(R.id.search_text);
        searchTypeList = (TextView) findViewById(R.id.search_type_list);
        searchData = (EditText) findViewById(R.id.search_data);
        searchType = (Spinner) findViewById(R.id.search_type);

        searchTypeText.setTypeface(typeface);
        searchData.setTypeface(typeface);

        SearchTypeAdapter searchTypeAdapter = new SearchTypeAdapter(getApplicationContext(),searchTypes,typeface);
        searchType.setAdapter(searchTypeAdapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderListActivity.class);
                intent.putExtra("searchType", "" + searchTypes.get(Integer.parseInt(searchType.getSelectedItem().toString())));
                intent.putExtra("identification", "" + searchData.getText().toString());
                startActivity(intent);
            }
        });

        searchData.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    Intent intent = new Intent(getApplicationContext(),OrderListActivity.class);
                    intent.putExtra("searchType", "" + searchTypes.get(Integer.parseInt(searchType.getSelectedItem().toString())));
                    intent.putExtra("identification", "" + searchData.getText().toString());
                    startActivity(intent);
                }
                return false;
            }
        });

    }

}
