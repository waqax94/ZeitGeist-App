package com.zeitgeist.employee.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.Adapters.OrderListViewPagerAdapter;
import com.zeitgeist.employee.Fragments.AllOrderFragment;
import com.zeitgeist.employee.Fragments.CancelledFragment;
import com.zeitgeist.employee.Fragments.CustomerInfoFragment;
import com.zeitgeist.employee.Fragments.MeasurementInfoFragment;
import com.zeitgeist.employee.Fragments.OrderInfoFragment;
import com.zeitgeist.employee.Fragments.PendingReviewFragment;
import com.zeitgeist.employee.Fragments.PreProductionFragment;
import com.zeitgeist.employee.Fragments.StylinngInfoFragment;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.OrderDetail;
import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.R;
import com.zeitgeist.employee.Services.APIService;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class OrderDisplayActivity extends AppCompatActivity {

    TextView title;
    ImageButton backButton;
    Toolbar toolbar;
    Typeface typeface;
    TabLayout tabLayout;
    ViewPager viewPager;
    String customerId,orderId;
    IpClass ipClass = new IpClass();
    CustomerInfoFragment customerInfoFragment;
    OrderInfoFragment orderInfoFragment;
    MeasurementInfoFragment measurementInfoFragment;
    StylinngInfoFragment stylinngInfoFragment;
    OrderListViewPagerAdapter orderDetailsViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_display);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Light.ttf");
        ReplaceFont.replaceFont(this,"DEFAULT","fonts/Raleway-Light.ttf");

        customerId = getIntent().getExtras().getString("customerId");
        orderId = getIntent().getExtras().getString("orderId");

        backButton = (ImageButton) findViewById(R.id.back_btn);
        title = (TextView) findViewById(R.id.toolbar_heading);
        title.setTypeface(typeface);
        title.setText("ORDER DISPLAY");
        toolbar = (Toolbar) findViewById(R.id.c_toolbar);
        setSupportActionBar(toolbar);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.order_display_tabView);
        viewPager = (ViewPager) findViewById(R.id.order_details_view_pager);

        customerInfoFragment = new CustomerInfoFragment();
        customerInfoFragment.customerId = customerId;
        customerInfoFragment.orderId = orderId;

        orderInfoFragment = new OrderInfoFragment();
        orderInfoFragment.customerId = customerId;
        orderInfoFragment.orderId = orderId;

        measurementInfoFragment = new MeasurementInfoFragment();
        measurementInfoFragment.customerId = customerId;
        measurementInfoFragment.orderId = orderId;

        stylinngInfoFragment = new StylinngInfoFragment();
        stylinngInfoFragment.customerId = customerId;
        stylinngInfoFragment.orderId = orderId;


        orderDetailsViewPagerAdapter = new OrderListViewPagerAdapter(getSupportFragmentManager());
        orderDetailsViewPagerAdapter.addFragments(customerInfoFragment,"Customer Info");
        orderDetailsViewPagerAdapter.addFragments(orderInfoFragment,"Order Info");
        orderDetailsViewPagerAdapter.addFragments(measurementInfoFragment,"Measurement Info");
        orderDetailsViewPagerAdapter.addFragments(stylinngInfoFragment,"Styling Info");

        viewPager.setAdapter(orderDetailsViewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        final APIService service = retrofit.create(APIService.class);

        Call<OrderDetail> orderInfo = service.ProcessOrderDetail(orderId);

    }

}
