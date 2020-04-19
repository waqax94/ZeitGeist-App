package com.zeitgeist.employee.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.R;

public class HomeActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView title,shopTitle;
    Button newOrder,orderManagement;
    Toolbar toolbar;
    Typeface typeface;
    String shopAddress;
    FloatingActionButton infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences loginData = getSharedPreferences("shopInfo", Context.MODE_PRIVATE);
        shopAddress = loginData.getString("shopAddress","");

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");

        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setTypeface(typeface);
        title.setText("HOME");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        backButton = (ImageButton) findViewById(R.id.back_btn);
        shopTitle = (TextView) findViewById(R.id.shop_address);
        newOrder = (Button) findViewById(R.id.new_order_btn);
        orderManagement = (Button) findViewById(R.id.order_management_btn);
        infoButton = (FloatingActionButton) findViewById(R.id.app_info_btn);

        shopTitle.setTypeface(typeface);
        shopTitle.setText(shopAddress);
        newOrder.setTypeface(typeface);
        orderManagement.setTypeface(typeface);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure to logout");
                builder.setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences loginData = getSharedPreferences("shopInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = loginData.edit();
                        editor.putString("shopId", "");
                        editor.putString("shopAddress", "");
                        editor.apply();
                        Toast.makeText(getApplicationContext(),"Logged Out!",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                builder.setNegativeButton("CANCEL",null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NewOrderActivity.class);
                startActivity(intent);
            }
        });

        orderManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderManagementActivity.class);
                startActivity(intent);
            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InformationActivity.class);
                startActivity(intent);
            }
        });

    }

}
