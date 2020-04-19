package com.zeitgeist.employee.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.R;
import com.zeitgeist.employee.Services.APIService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class FeedbackActivity extends AppCompatActivity {

    ImageButton backButton;
    TextView title;
    Toolbar toolbar;
    ProgressDialog pDialog;
    IpClass ipClass = new IpClass();
    Button submit;
    TextView customerIdTextView,orderIdTextView,customerNameTextView;
    RadioGroup answer1rg,answer2rg,answer3rg,answer4rg,answer5rg,answer6rg,answer7rg,answer8rg;
    String shopId,shopAddress,customerId,orderId,customerName,answer1 = "Good",answer2 = "Good",answer3 = "Good",answer4 = "Good",answer5 = "Good",answer6 = "Good",answer7 = "Good",answer8 = "Good";
    String score1 = "3",score2 = "3",score3 = "3",score4 = "3",score5 = "3",score6 = "3",score7 = "3",score8 = "3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setText("CUSTOMER FEEDBACK");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        backButton = (ImageButton) findViewById(R.id.back_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        SharedPreferences loginData = getSharedPreferences("shopInfo", Context.MODE_PRIVATE);
        shopId = loginData.getString("shopId","");

        customerId = getIntent().getExtras().getString("customerId");
        orderId = getIntent().getExtras().getString("orderId");
        customerName = getIntent().getExtras().getString("customerName");

        customerIdTextView = (TextView) findViewById(R.id.customer_id_num);
        orderIdTextView = (TextView) findViewById(R.id.order_id_num);
        customerNameTextView = (TextView) findViewById(R.id.customer_name_text);
        answer1rg = (RadioGroup) findViewById(R.id.answere_1);
        answer2rg = (RadioGroup) findViewById(R.id.answere_2);
        answer3rg = (RadioGroup) findViewById(R.id.answere_3);
        answer4rg = (RadioGroup) findViewById(R.id.answere_4);
        answer5rg = (RadioGroup) findViewById(R.id.answere_5);
        answer6rg = (RadioGroup) findViewById(R.id.answere_6);
        answer7rg = (RadioGroup) findViewById(R.id.answere_7);
        answer8rg = (RadioGroup) findViewById(R.id.answere_8);

        submit = (Button) findViewById(R.id.submit_feedback);

        customerIdTextView.setText(customerId);
        orderIdTextView.setText(orderId);
        customerNameTextView.setText(customerName);

        answer1rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.excellent_ans_1){
                    answer1 = "Excellent";
                    score1 = "4";
                }
                else if(checkedId == R.id.good_ans_1){
                    answer1 = "Good";
                    score1 = "3";
                }
                else if(checkedId == R.id.average_ans_1){
                    answer1 = "Average";
                    score1 = "2";
                }
                else if(checkedId == R.id.poor_ans_1){
                    answer1 = "Poor";
                    score1 = "1";
                }
            }
        });

        answer2rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.excellent_ans_2){
                    answer2 = "Excellent";
                    score2 = "4";
                }
                else if(checkedId == R.id.good_ans_2){
                    answer2 = "Good";
                    score2 = "3";
                }
                else if(checkedId == R.id.average_ans_2){
                    answer2 = "Average";
                    score2 = "2";
                }
                else if(checkedId == R.id.poor_ans_2){
                    answer2 = "Poor";
                    score2 = "1";
                }
            }
        });

        answer3rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.excellent_ans_3){
                    answer3 = "Excellent";
                    score3 = "4";
                }
                else if(checkedId == R.id.good_ans_3){
                    answer3 = "Good";
                    score3 = "3";
                }
                else if(checkedId == R.id.average_ans_3){
                    answer3 = "Average";
                    score3 = "2";
                }
                else if(checkedId == R.id.poor_ans_3){
                    answer3 = "Poor";
                    score3 = "1";
                }
            }
        });

        answer4rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.excellent_ans_4){
                    answer4 = "Excellent";
                    score4 = "4";
                }
                else if(checkedId == R.id.good_ans_4){
                    answer4 = "Good";
                    score4 = "3";
                }
                else if(checkedId == R.id.average_ans_4){
                    answer4 = "Average";
                    score4 = "2";
                }
                else if(checkedId == R.id.poor_ans_4){
                    answer4 = "Poor";
                    score4 = "1";
                }
            }
        });

        answer5rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.excellent_ans_5){
                    answer5 = "Excellent";
                    score5 = "4";
                }
                else if(checkedId == R.id.good_ans_5){
                    answer5 = "Good";
                    score5 = "3";
                }
                else if(checkedId == R.id.average_ans_5){
                    answer5 = "Average";
                    score5 = "2";
                }
                else if(checkedId == R.id.poor_ans_5){
                    answer5 = "Poor";
                    score5 = "1";
                }
            }
        });

        answer6rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.excellent_ans_6){
                    answer6 = "Excellent";
                    score6 = "4";
                }
                else if(checkedId == R.id.good_ans_6){
                    answer6 = "Good";
                    score6 = "3";
                }
                else if(checkedId == R.id.average_ans_6){
                    answer6 = "Average";
                    score6 = "2";
                }
                else if(checkedId == R.id.poor_ans_6){
                    answer6 = "Poor";
                    score6 = "1";
                }
            }
        });

        answer7rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.excellent_ans_7){
                    answer7 = "Excellent";
                    score7 = "4";
                }
                else if(checkedId == R.id.good_ans_7){
                    answer7 = "Good";
                    score7 = "3";
                }
                else if(checkedId == R.id.average_ans_7){
                    answer7 = "Average";
                    score7 = "2";
                }
                else if(checkedId == R.id.poor_ans_7){
                    answer7 = "Poor";
                    score7 = "1";
                }
            }
        });

        answer8rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.excellent_ans_8){
                    answer8 = "Excellent";
                    score8 = "4";
                }
                else if(checkedId == R.id.good_ans_8){
                    answer8 = "Good";
                    score8 = "3";
                }
                else if(checkedId == R.id.average_ans_8){
                    answer8 = "Average";
                    score8 = "2";
                }
                else if(checkedId == R.id.poor_ans_8){
                    answer8 = "Poor";
                    score8 = "1";
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ipClass.ipAddress).
                                addConverterFactory(GsonConverterFactory.create())
                        .build();
                final APIService service = retrofit.create(APIService.class);


                showpDialog();
                Call<String> setStatus = service.ProcessSellBtn(orderId);

                setStatus.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {

                        Call<String> feedback = service.ProcessFeedback(customerId,orderId,customerName,answer1,answer2,answer3,answer4,answer5,answer6,answer7,answer8);

                        feedback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Response<String> response, Retrofit retrofit) {

                                Call<String> timeline = service.ProcessStatusTimeline(orderId,"Sold");

                                timeline.enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Response<String> response, Retrofit retrofit) {
                                        hidepDialog();


                                        Call<String> feedbackScore = service.ProcessFeedbackScore(shopId,score1,score2,score3,score4,score5,score6,score7,score8);

                                        feedbackScore.enqueue(new Callback<String>() {
                                            @Override
                                            public void onResponse(Response<String> response, Retrofit retrofit) {
                                                Toast.makeText(getApplicationContext(),"Thankyou for your Feedback!",Toast.LENGTH_LONG).show();

                                            }

                                            @Override
                                            public void onFailure(Throwable t) {

                                            }
                                        });

                                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {

                                    }
                                });
                            }

                            @Override
                            public void onFailure(Throwable t) {
                                hidepDialog();
                                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        hidepDialog();
                        Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
                    }
                });
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