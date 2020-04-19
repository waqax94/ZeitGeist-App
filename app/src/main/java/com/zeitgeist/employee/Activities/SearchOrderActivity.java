package com.zeitgeist.employee.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.zeitgeist.employee.Adapters.SearchTypeAdapter;
import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchOrderActivity extends AppCompatActivity {

    TextView title;
    ImageButton backButton,searchByCustomerBtn,searchByOrderBtn;
    EditText searchByCustomerData,searchByOrderData;
    Button searchByOrderDateData;
    Spinner searchByCustomerType,searchByOrderType;
    Toolbar toolbar;
    Typeface typeface;
    List searchByCustomerTypes = new ArrayList<String>();
    List searchByOrderTypes = new ArrayList<String>();
    SearchTypeAdapter customerAdapter,orderAdapter;
    int mday, mmonth, myear;
    int dayFinal, monthFinal , yearFinal;
    String dateValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_order);
        setupUI(findViewById(R.id.content_search_order));
        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");
        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");

        searchByCustomerTypes.add("ID");
        searchByCustomerTypes.add("Name");
        searchByCustomerTypes.add("Phone");
        searchByCustomerTypes.add("Email");

        searchByOrderTypes.add("ID");
        searchByOrderTypes.add("Item Type");
        searchByOrderTypes.add("Type");
        searchByOrderTypes.add("Placed Date");
        searchByOrderTypes.add("Delivery Date");

        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setText("SEARCH ORDER");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        backButton = (ImageButton) findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchByCustomerType = (Spinner) findViewById(R.id.search_by_customer_type);
        searchByOrderType = (Spinner) findViewById(R.id.search_by_order_type);

        searchByCustomerData = (EditText) findViewById(R.id.search_by_customer_data);
        searchByOrderData = (EditText) findViewById(R.id.search_by_order_data);
        searchByOrderDateData = (Button) findViewById(R.id.search_by_order_data_date);

        searchByCustomerBtn = (ImageButton) findViewById(R.id.search_by_customer_btn);
        searchByOrderBtn = (ImageButton) findViewById(R.id.search_by_order_btn);


        customerAdapter = new SearchTypeAdapter(getApplicationContext(),searchByCustomerTypes,typeface);
        searchByCustomerType.setAdapter(customerAdapter);

        orderAdapter = new SearchTypeAdapter(getApplicationContext(),searchByOrderTypes,typeface);
        searchByOrderType.setAdapter(orderAdapter);

        searchByOrderType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(searchByOrderTypes.get(position).equals("Placed Date") || searchByOrderTypes.get(position).equals("Delivery Date")){
                    searchByOrderData.setVisibility(View.GONE);
                    searchByOrderDateData.setVisibility(View.VISIBLE);
                }
                else {
                    searchByOrderData.setVisibility(View.VISIBLE);
                    searchByOrderDateData.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchByOrderDateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                myear = c.get(Calendar.YEAR);
                mmonth = c.get(Calendar.MONTH);
                mday = c.get(Calendar.DAY_OF_MONTH);



                DatePickerDialog datePickerDialog = new DatePickerDialog(SearchOrderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        yearFinal = year;
                        monthFinal = month + 1;
                        dayFinal = dayOfMonth;


                        searchByOrderData.setText(dayFinal+"/"+monthFinal+"/"+yearFinal);
                        searchByOrderDateData.setText(dayFinal+"/"+monthFinal+"/"+yearFinal);
                        dateValue = yearFinal + "-" + monthFinal + "-" + dayFinal;
                    }
                },myear,mmonth,mday){

                };
                datePickerDialog.show();
            }
        });

        searchByCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchType = searchByCustomerTypes.get(Integer.parseInt(searchByCustomerType.getSelectedItem().toString())).toString();
                String searchData = searchByCustomerData.getText().toString();
                Intent intent = new Intent(getApplicationContext(),SearchResultActivity.class);
                intent.putExtra("searchBy", "Customer");
                intent.putExtra("searchOn", ""+searchType);
                intent.putExtra("searchData", ""+searchData);
                startActivity(intent);
            }
        });

        searchByOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchType = searchByOrderTypes.get(Integer.parseInt(searchByOrderType.getSelectedItem().toString())).toString();
                String searchData = searchByOrderData.getText().toString();
                Intent intent = new Intent(getApplicationContext(),SearchResultActivity.class);
                intent.putExtra("searchBy", "Order");
                intent.putExtra("searchOn", ""+searchType);
                intent.putExtra("searchData", ""+searchData);
                startActivity(intent);
            }
        });

    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(SearchOrderActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        try {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
        catch (NullPointerException e){
        }
    }

}
