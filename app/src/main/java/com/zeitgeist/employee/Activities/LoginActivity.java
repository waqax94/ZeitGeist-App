package com.zeitgeist.employee.Activities;

import android.app.ProgressDialog;
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

public class LoginActivity extends AppCompatActivity {

    TextView shopIdText,passwordText;
    Button login;
    EditText shopId,password;
    Typeface typeface;
    ProgressDialog pDialog;
    String sId,shopPw,shopAddress;
    IpClass ipClass = new IpClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        shopIdText = (TextView) findViewById(R.id.shop_id_text_l);
        passwordText = (TextView) findViewById(R.id.shop_pw_text_l);
        login = (Button) findViewById(R.id.login_btn_l);
        shopId = (EditText) findViewById(R.id.shop_id_l);
        password = (EditText) findViewById(R.id.shop_pw_l);

        shopIdText.setTypeface(typeface);
        passwordText.setTypeface(typeface);
        login.setTypeface(typeface);
        shopId.setTypeface(typeface);
        password.setTypeface(typeface);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sId = shopId.getText().toString();
                shopPw = password.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ipClass.ipAddress)
                        .addConverterFactory(GsonConverterFactory.create()).build();

                APIService service = retrofit.create(APIService.class);

                if(!sId.equals("") && !shopPw.equals("")) {
                    showpDialog();
                    Call<String> sData = service.ProcessLogin(sId, shopPw);
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
