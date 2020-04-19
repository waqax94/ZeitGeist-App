package com.zeitgeist.employee.Activities;

import android.app.ProgressDialog;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.Adapters.OrderListAdapter;
import com.zeitgeist.employee.Models.Customer;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.OrderDetail;
import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.R;
import com.zeitgeist.employee.Services.APIService;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class OrderListActivity extends AppCompatActivity {

    IpClass ipClass = new IpClass();
    ProgressDialog pDialog;
    TextView title;
    ImageButton backButton;
    Toolbar toolbar;
    Typeface typeface;
    String searchType,identification,customerId,orderId;
    RelativeLayout customerExist,customerNotExist;
    TextView customerName,customerIdTextView,customerPhone,customerEmail;
    Button newOrder;
    ListView orderList;
    OrderListAdapter orderListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");
        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");

        backButton = (ImageButton) findViewById(R.id.back_btn);
        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setTypeface(typeface);
        title.setText("ORDER LIST");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        searchType = getIntent().getExtras().getString("searchType");
        identification = getIntent().getExtras().getString("identification");

        customerExist = (RelativeLayout) findViewById(R.id.customer_order_list_layout);
        customerNotExist = (RelativeLayout) findViewById(R.id.no_customer_layout);

        customerName = (TextView) findViewById(R.id.customer_name);
        customerIdTextView = (TextView) findViewById(R.id.customer_id_data);
        customerPhone = (TextView) findViewById(R.id.customer_phone_num_data);
        customerEmail = (TextView) findViewById(R.id.customer_email_data);

        newOrder = (Button) findViewById(R.id.new_customer_order_btn);

        orderList = (ListView) findViewById(R.id.customer_order_list);
        orderListAdapter = new OrderListAdapter(getApplicationContext(),R.layout.custom_order_list);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        final APIService service = retrofit.create(APIService.class);

        showpDialog();

        if(searchType.equals("ID")){

            Call<Customer> customerData = service.SearchCustomerById(identification);

            customerData.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Response<Customer> response, Retrofit retrofit) {
                    final Customer customer = response.body();

                    if(response.body() != null){
                        customerNotExist.setVisibility(View.GONE);
                        customerExist.setVisibility(View.VISIBLE);
                        customerName.setText(customer.getCustomerName());
                        customerIdTextView.setText(customer.getCustomerId());
                        customerPhone.setText(customer.getCustomerPrimaryPhone());
                        customerEmail.setText(customer.getCustomerEmail());

                        customerId = customer.getCustomerId();

                        Call<List<OrderDetail>> orderData = service.ProcessOrders(customerId);

                        orderData.enqueue(new Callback<List<OrderDetail>>() {
                            @Override
                            public void onResponse(Response<List<OrderDetail>> response, Retrofit retrofit) {
                                hidepDialog();
                                if (response.body() != null){
                                    final  List<OrderDetail> orderDetails = response.body();

                                    orderListAdapter.addOrderList(orderDetails,customerId);
                                    orderList.setAdapter(orderListAdapter);
                                }
                                else {
                                    hidepDialog();
                                    Toast.makeText(getApplicationContext(),"No Orders Found",Toast.LENGTH_SHORT).show();
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
                        hidepDialog();
                        customerNotExist.setVisibility(View.VISIBLE);
                        customerExist.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    hidepDialog();
                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                }
            });
        }

        else if(searchType.equals("Email")){

            Call<Customer> customerData = service.SearchCustomerByEmail(identification);

            customerData.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Response<Customer> response, Retrofit retrofit) {
                    final Customer customer = response.body();

                    if(response.body() != null){
                        customerNotExist.setVisibility(View.GONE);
                        customerExist.setVisibility(View.VISIBLE);
                        customerName.setText(customer.getCustomerName());
                        customerIdTextView.setText(customer.getCustomerId());
                        customerPhone.setText(customer.getCustomerPrimaryPhone());
                        customerEmail.setText(customer.getCustomerEmail());

                        customerId = customer.getCustomerId();

                        Call<List<OrderDetail>> orderData = service.ProcessOrders(customerId);

                        orderData.enqueue(new Callback<List<OrderDetail>>() {
                            @Override
                            public void onResponse(Response<List<OrderDetail>> response, Retrofit retrofit) {
                                hidepDialog();
                                if (response.body() != null){
                                    final  List<OrderDetail> orderDetails = response.body();

                                    orderListAdapter.addOrderList(orderDetails,customerId);
                                    orderList.setAdapter(orderListAdapter);
                                }
                                else {
                                    hidepDialog();
                                    Toast.makeText(getApplicationContext(),"No Orders Found",Toast.LENGTH_SHORT).show();
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
                        hidepDialog();
                        customerNotExist.setVisibility(View.VISIBLE);
                        customerExist.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    hidepDialog();
                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                }
            });

        }

        else if(searchType.equals("Phone")){

            Call<Customer> customerData = service.SearchCustomerByPhone(identification);

            customerData.enqueue(new Callback<Customer>() {
                @Override
                public void onResponse(Response<Customer> response, Retrofit retrofit) {
                    final Customer customer = response.body();

                    if(response.body() != null){
                        customerNotExist.setVisibility(View.GONE);
                        customerExist.setVisibility(View.VISIBLE);
                        customerName.setText(customer.getCustomerName());
                        customerIdTextView.setText(customer.getCustomerId());
                        customerPhone.setText(customer.getCustomerPrimaryPhone());
                        customerEmail.setText(customer.getCustomerEmail());

                        customerId = customer.getCustomerId();

                        Call<List<OrderDetail>> orderData = service.ProcessOrders(customerId);

                        orderData.enqueue(new Callback<List<OrderDetail>>() {
                            @Override
                            public void onResponse(Response<List<OrderDetail>> response, Retrofit retrofit) {
                                hidepDialog();
                                if (response.body() != null){
                                    final  List<OrderDetail> orderDetails = response.body();

                                    orderListAdapter.addOrderList(orderDetails,customerId);
                                    orderList.setAdapter(orderListAdapter);
                                }
                                else {
                                    hidepDialog();
                                    Toast.makeText(getApplicationContext(),"No Orders Found",Toast.LENGTH_SHORT).show();
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
                        hidepDialog();
                        customerNotExist.setVisibility(View.VISIBLE);
                        customerExist.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    hidepDialog();
                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                }
            });

        }

        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),OrderTypeActivity.class);
                intent.putExtra("orderCondition", "Existing Customer");
                intent.putExtra("customerId", "" + customerId);
                startActivity(intent);
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