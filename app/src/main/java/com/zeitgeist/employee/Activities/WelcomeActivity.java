package com.zeitgeist.employee.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.R;

public class WelcomeActivity extends AppCompatActivity {

    Animation rotate;
    Typeface typeface;
    String shopId,shopAddress;
    TextView welcomeText;
    ImageView loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        shopId = getIntent().getExtras().getString("shopId");
        shopAddress = getIntent().getExtras().getString("shopAddress");

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");
        welcomeText = (TextView) findViewById(R.id.welcome_text_l);
        welcomeText.setTypeface(typeface);
        loading = (ImageView) findViewById(R.id.loading_l);


        SharedPreferences loginData = getSharedPreferences("shopInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = loginData.edit();
        editor.putString("shopId", shopId);
        editor.putString("shopAddress", shopAddress);
        editor.apply();

        rotate = AnimationUtils.loadAnimation(this,R.anim.rotate);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        loading.startAnimation(rotate);

    }

}
