package com.zeitgeist.employee.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.R;

public class InformationActivity extends AppCompatActivity {

    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");

        backButton = (ImageButton) findViewById(R.id.info_back_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
