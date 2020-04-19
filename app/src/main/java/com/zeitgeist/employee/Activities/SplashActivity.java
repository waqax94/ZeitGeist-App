package com.zeitgeist.employee.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.R;
import com.zeitgeist.employee.Services.APIService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SplashActivity extends AppCompatActivity {

    Animation fadeout,fadein,slideup,rotate,formFadein;
    ImageView mainLogo,logo,loading;
    Handler h = new Handler();
    RelativeLayout splashContent,loginForm,splashWelcome;
    TextView shopIdText,passwordText,welcomeText;
    Button login;
    EditText shopId,password;
    Typeface typeface;
    ProgressDialog pDialog;
    IpClass ipClass = new IpClass();
    String sId,shopPw,shopAddress,Id,sAd;
    boolean loginCheck = false;
    FloatingActionButton infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences loginData = getSharedPreferences("shopInfo", Context.MODE_PRIVATE);
        Id = loginData.getString("shopAddress","");
        sAd = loginData.getString("shopAddress","");
        if(!Id.equals("") && !sAd.equals("")){
            loginCheck = true;
        }

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        shopIdText = (TextView) findViewById(R.id.shop_id_text);
        passwordText = (TextView) findViewById(R.id.shop_pw_text);
        welcomeText = (TextView) findViewById(R.id.welcome_text);
        login = (Button) findViewById(R.id.login_btn);
        shopId = (EditText) findViewById(R.id.shop_id);
        password = (EditText) findViewById(R.id.shop_pw);
        infoButton = (FloatingActionButton) findViewById(R.id.app_info_btn);

        shopIdText.setTypeface(typeface);
        passwordText.setTypeface(typeface);
        welcomeText.setTypeface(typeface);
        login.setTypeface(typeface);
        shopId.setTypeface(typeface);
        password.setTypeface(typeface);


        mainLogo = (ImageView) findViewById(R.id.splash_main_logo);
        logo = (ImageView) findViewById(R.id.splash_logo);
        splashContent = (RelativeLayout) findViewById(R.id.splash_content);
        loginForm = (RelativeLayout) findViewById(R.id.splash_login_form);
        splashWelcome = (RelativeLayout) findViewById(R.id.splash_welcome_content);
        loading = (ImageView) findViewById(R.id.loading);


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

        formFadein = AnimationUtils.loadAnimation(this,R.anim.fadein);
        formFadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadein = AnimationUtils.loadAnimation(this,R.anim.fadein);
        fadein.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                loading.startAnimation(rotate);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        slideup = AnimationUtils.loadAnimation(this,R.anim.slideup);
        slideup.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.startAnimation(fadein);
                if(loginCheck){
                    splashWelcome.startAnimation(fadein);
                }
                else {
                    loginForm.startAnimation(formFadein);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fadeout = AnimationUtils.loadAnimation(this,R.anim.fadeout);
        fadeout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splashContent.startAnimation(slideup);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e){

                }
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        mainLogo.startAnimation(fadeout);
                    }
                });
            }
        }).start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sId = shopId.getText().toString();
                shopPw = password.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ipClass.ipAddress)
                        .addConverterFactory(GsonConverterFactory.create()).build();

                APIService service = retrofit.create(APIService.class);

                if(!sId.equals("") && !shopPw.equals("")){
                    showpDialog();
                    Call<String> sData = service.ProcessLogin(sId,shopPw);
                    sData.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Response<String> response, Retrofit retrofit) {
                            hidepDialog();
                            if(response.body() != null){
                                shopAddress = response.body();

                                Intent welcome = new Intent(getApplicationContext(),WelcomeActivity.class);
                                welcome.putExtra("shopId", "" + sId);
                                welcome.putExtra("shopAddress", "" + shopAddress);
                                startActivity(welcome);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Throwable t) {
                            hidepDialog();
                            Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"All fields required!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InformationActivity.class);
                startActivity(intent);
            }
        });

        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    sId = shopId.getText().toString();
                    shopPw = password.getText().toString();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ipClass.ipAddress)
                            .addConverterFactory(GsonConverterFactory.create()).build();

                    APIService service = retrofit.create(APIService.class);

                    if(!sId.equals("") && !shopPw.equals("")){
                        showpDialog();
                        Call<String> sData = service.ProcessLogin(sId,shopPw);
                        sData.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                hidepDialog();
                                if(response.body() != null){
                                    shopAddress = response.body();

                                    Intent welcome = new Intent(getApplicationContext(),WelcomeActivity.class);
                                    welcome.putExtra("shopId", "" + sId);
                                    welcome.putExtra("shopAddress", "" + shopAddress);
                                    startActivity(welcome);
                                    finish();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Throwable t) {
                                hidepDialog();
                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"All fields required!",Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });

    }


    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
