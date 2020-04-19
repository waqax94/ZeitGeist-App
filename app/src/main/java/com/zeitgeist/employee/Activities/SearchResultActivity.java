package com.zeitgeist.employee.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.Adapters.OrderManagementListAdapter;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.OrderListData;
import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.R;
import com.zeitgeist.employee.Services.APIService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SearchResultActivity extends AppCompatActivity {

    SimpleDateFormat oldFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");

    TextView title,searchBy,searchOn,searchKeyword,colon;
    ImageButton backButton;
    Toolbar toolbar;
    String searchByData,searchOnData,searchKeywordData;

    ListView orderList;
    OrderManagementListAdapter orderManagementListAdapter;
    IpClass ipClass = new IpClass();
    TextView noData;
    ProgressDialog pDialog;
    LinearLayout orderListHeader;
    TextView orderIdHeading,fabricNumberHeading,customerNameHeading,itemTypeHeading,dateHeading,deliveryDateHeading,priceHeading,statusHeading;
    boolean sortName = true;

    String customerId = "",customerName = "",customerPhone = "",customerEmail = "",orderId = "",itemType = "",orderType = "",placedDate = "",
            deliveryDate = "",shopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");
        Typeface typefaceB = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Bold.ttf");

        SharedPreferences loginData = getSharedPreferences("shopInfo", Context.MODE_PRIVATE);
        shopId = loginData.getString("shopId","");

        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setText("SEARCH RESULTS");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        searchByData = getIntent().getExtras().getString("searchBy");
        searchOnData = getIntent().getExtras().getString("searchOn");
        searchKeywordData = getIntent().getExtras().getString("searchData");

        if(searchByData.equals("Customer")){
            if(searchOnData.equals("ID")){
                customerId = searchKeywordData;
            }
            else if(searchOnData.equals("Name")){
                customerName = searchKeywordData;
            }
            else if(searchOnData.equals("Phone")){
                customerPhone = searchKeywordData;
            }
            else if(searchOnData.equals("Email")){
                customerEmail = searchKeywordData;
            }

        }
        else if(searchByData.equals("Order")){
            if(searchOnData.equals("ID")){
                orderId = searchKeywordData;
            }
            else if(searchOnData.equals("Item Type")){
                itemType = searchKeywordData;
            }
            else if(searchOnData.equals("Type")){
                orderType = searchKeywordData;
            }
            else if(searchOnData.equals("Placed Date")){
                try {
                    placedDate = newFormat.format(oldFormat.parse(searchKeywordData));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            else if(searchOnData.equals("Delivery Date")){
                try {
                    deliveryDate = newFormat.format(oldFormat.parse(searchKeywordData));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        backButton = (ImageButton) findViewById(R.id.back_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchBy = (TextView) findViewById(R.id.search_by_data);
        searchBy.setTypeface(typefaceB);
        searchOn = (TextView) findViewById(R.id.search_on_data);
        searchOn.setTypeface(typefaceB);
        searchKeyword = (TextView) findViewById(R.id.search_keyword_data);
        searchKeyword.setTypeface(typefaceB);
        colon = (TextView) findViewById(R.id.colon_data);
        colon.setTypeface(typefaceB);

        searchBy.setText(searchByData);
        searchOn.setText(searchOnData);
        searchKeyword.setText(searchKeywordData);

        orderIdHeading = (TextView)findViewById(R.id.order_id_heading);
        orderIdHeading.setTypeface(typefaceB);
        fabricNumberHeading = (TextView)findViewById(R.id.fabric_number_heading);
        fabricNumberHeading.setTypeface(typefaceB);
        customerNameHeading = (TextView)findViewById(R.id.customer_name_heading);
        customerNameHeading.setTypeface(typefaceB);
        itemTypeHeading = (TextView)findViewById(R.id.item_type_heading);
        itemTypeHeading.setTypeface(typefaceB);
        dateHeading = (TextView)findViewById(R.id.placed_date_heading);
        dateHeading.setTypeface(typefaceB);
        deliveryDateHeading = (TextView)findViewById(R.id.delivery_date_heading);
        deliveryDateHeading.setTypeface(typefaceB);
        priceHeading = (TextView)findViewById(R.id.price_heading);
        priceHeading.setTypeface(typefaceB);
        statusHeading = (TextView)findViewById(R.id.status_heading);
        statusHeading.setTypeface(typefaceB);

        orderListHeader = (LinearLayout)findViewById(R.id.order_list_header);
        orderList = (ListView)findViewById(R.id.order_list);
        noData = (TextView)findViewById(R.id.no_data_text);


        orderManagementListAdapter = new OrderManagementListAdapter(getApplicationContext(),R.layout.custom_order_list_management);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        showpDialog();

        Call<List<OrderListData>> orderListData = service.ProcessOrderSearch(customerId,customerName,customerPhone,customerEmail,orderId,itemType,orderType,placedDate,deliveryDate,shopId);

        orderListData.enqueue(new Callback<List<OrderListData>>() {
            @Override
            public void onResponse(Response<List<OrderListData>> response, Retrofit retrofit) {
                hidepDialog();
                final List<OrderListData> orderDataList = response.body();
                if(response.body() != null){
                    noData.setVisibility(View.GONE);
                    orderListHeader.setVisibility(View.VISIBLE);
                    orderList.setVisibility(View.VISIBLE);
                    orderManagementListAdapter.addOrderList(orderDataList);
                    orderList.setAdapter(orderManagementListAdapter);

                    orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getApplicationContext(),OrderDisplayActivity.class);
                            intent.putExtra("customerId", "" + orderDataList.get(position).getCustomerId());
                            intent.putExtra("orderId", "" + orderDataList.get(position).getOrderId());
                            startActivity(intent);
                        }
                    });
                }
                else {
                    orderList.setVisibility(View.GONE);
                    noData.setVisibility(View.VISIBLE);
                    orderListHeader.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                hidepDialog();
                Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
            }
        });


        customerNameHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliveryDateHeading.setBackgroundColor(Color.TRANSPARENT);
                dateHeading.setBackgroundColor(Color.TRANSPARENT);
                customerNameHeading.setBackgroundColor(Color.parseColor("#e7e3e2"));
                if(sortName){
                    sortName = false;
                    orderManagementListAdapter.sortByCustomerName();
                }
                else {
                    sortName = true;
                    orderManagementListAdapter.sortByCustomerNameReverse();
                }
            }
        });

        deliveryDateHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customerNameHeading.setBackgroundColor(Color.TRANSPARENT);
                dateHeading.setBackgroundColor(Color.TRANSPARENT);
                deliveryDateHeading.setBackgroundColor(Color.parseColor("#e7e3e2"));
                orderManagementListAdapter.sortByDeliveryDate();
            }
        });

        dateHeading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliveryDateHeading.setBackgroundColor(Color.TRANSPARENT);
                customerNameHeading.setBackgroundColor(Color.TRANSPARENT);
                dateHeading.setBackgroundColor(Color.parseColor("#e7e3e2"));
                orderManagementListAdapter.sortByOrderDate();
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
