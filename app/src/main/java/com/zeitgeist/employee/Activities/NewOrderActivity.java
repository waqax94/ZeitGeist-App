package com.zeitgeist.employee.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zeitgeist.employee.R;

public class NewOrderActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView title;
    Button newCustomer,existingCustomer;
    Toolbar toolbar;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");

        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setTypeface(typeface);
        title.setText("NEW ORDER");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        backButton = (ImageButton) findViewById(R.id.back_btn);
        newCustomer = (Button) findViewById(R.id.new_customer_btn);
        existingCustomer = (Button) findViewById(R.id.existing_customer_btn);

        newCustomer.setTypeface(typeface);
        existingCustomer.setTypeface(typeface);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        newCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderTypeActivity.class);
                intent.putExtra("orderCondition", "New Customer");
                startActivity(intent);
            }
        });

        existingCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchCustomerActivity.class);
                startActivity(intent);
            }
        });
    }

}
