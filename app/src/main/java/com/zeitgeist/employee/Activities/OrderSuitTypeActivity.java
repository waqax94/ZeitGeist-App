package com.zeitgeist.employee.Activities;

import android.content.Intent;
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

public class OrderSuitTypeActivity extends AppCompatActivity {

    String customerId,orderCondition;
    ImageButton backButton;
    TextView title;
    Button threePieceSuitBtn,twoPieceSuitBtn;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_suit_type);
        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");
        orderCondition = getIntent().getExtras().getString("orderCondition");
        customerId = getIntent().getExtras().getString("customerId");

        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setText("SUIT TYPE");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        backButton = (ImageButton) findViewById(R.id.back_btn);
        threePieceSuitBtn = (Button) findViewById(R.id.three_piece_suit_btn);
        twoPieceSuitBtn = (Button) findViewById(R.id.two_piece_suit_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        threePieceSuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderTakingActivity.class);
                intent.putExtra("orderType", "3 piece suit");
                intent.putExtra("orderCondition", "" + orderCondition);
                intent.putExtra("customerId", "" + customerId);
                startActivity(intent);
            }
        });

        twoPieceSuitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderTakingTwoPiece.class);
                intent.putExtra("orderType", "2 piece suit");
                intent.putExtra("orderCondition", "" + orderCondition);
                intent.putExtra("customerId", "" + customerId);
                startActivity(intent);
            }
        });
    }

}
