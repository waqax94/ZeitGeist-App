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

import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.R;

public class OrderTypeActivity extends AppCompatActivity {

    String customerId,orderCondition;
    ImageButton backButton;
    TextView title;
    Button suitBtn,jacketBtn,pantsBtn,waistCoatBtn,shirtBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_type);
        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");

        orderCondition = getIntent().getExtras().getString("orderCondition");
        customerId = getIntent().getExtras().getString("customerId");

        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setText("ORDER TYPE");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        backButton = (ImageButton) findViewById(R.id.back_btn);
        suitBtn = (Button) findViewById(R.id.suit_btn);
        jacketBtn = (Button) findViewById(R.id.jacket_btn);
        pantsBtn = (Button) findViewById(R.id.pants_btn);
        waistCoatBtn = (Button) findViewById(R.id.waist_coat_btn);
        shirtBtn = (Button) findViewById(R.id.shirt_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        suitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderSuitTypeActivity.class);
                intent.putExtra("orderType", "suit");
                intent.putExtra("orderCondition", "" + orderCondition);
                intent.putExtra("customerId", "" + customerId);
                startActivity(intent);
            }
        });

        jacketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderTakingJacket.class);
                intent.putExtra("orderType", "Jacket");
                intent.putExtra("orderCondition", "" + orderCondition);
                intent.putExtra("customerId", "" + customerId);
                startActivity(intent);
            }
        });

        pantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderTakingPant.class);
                intent.putExtra("orderType", "Pant");
                intent.putExtra("orderCondition", "" + orderCondition);
                intent.putExtra("customerId", "" + customerId);
                startActivity(intent);
            }
        });

        waistCoatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderTakingWaistCoat.class);
                intent.putExtra("orderType","Waist Coat");
                intent.putExtra("orderCondition", "" + orderCondition);
                intent.putExtra("customerId", "" + customerId);
                startActivity(intent);
            }
        });

        shirtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderTakingShirt.class);
                intent.putExtra("orderType","Shirt");
                intent.putExtra("orderCondition", "" + orderCondition);
                intent.putExtra("customerId", "" + customerId);
                startActivity(intent);
            }
        });


    }

}
