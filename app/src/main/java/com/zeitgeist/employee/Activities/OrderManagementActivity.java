package com.zeitgeist.employee.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
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

import com.zeitgeist.employee.Adapters.OrderListViewPagerAdapter;
import com.zeitgeist.employee.Fragments.AllOrderFragment;
import com.zeitgeist.employee.Fragments.CancelledFragment;
import com.zeitgeist.employee.Fragments.InProductionFragment;
import com.zeitgeist.employee.Fragments.InStoreFragment;
import com.zeitgeist.employee.Fragments.InWarehouseFragment;
import com.zeitgeist.employee.Fragments.PendingReviewFragment;
import com.zeitgeist.employee.Fragments.PreProductionFragment;
import com.zeitgeist.employee.Fragments.SoldFragment;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.ReplaceFont;
import com.zeitgeist.employee.R;

public class OrderManagementActivity extends AppCompatActivity {

    IpClass ipClass = new IpClass();
    ProgressDialog pDialog;
    TextView title;
    ImageButton backButton;
    Toolbar toolbar;
    Typeface typeface;
    TabLayout tabLayout;
    OrderListViewPagerAdapter orderListViewPagerAdapter;
    ViewPager viewPager;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);

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

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        searchBtn = (Button) findViewById(R.id.order_search_btn);
        tabLayout = (TabLayout) findViewById(R.id.order_tabView);
        viewPager = (ViewPager) findViewById(R.id.order_list_view_pager);

        orderListViewPagerAdapter = new OrderListViewPagerAdapter(getSupportFragmentManager());
        orderListViewPagerAdapter.addFragments(new AllOrderFragment(),"All");
        orderListViewPagerAdapter.addFragments(new PendingReviewFragment(),"Pending Review");
        orderListViewPagerAdapter.addFragments(new PreProductionFragment(),"Pre-production");
        orderListViewPagerAdapter.addFragments(new InProductionFragment(),"In Production");
        orderListViewPagerAdapter.addFragments(new CancelledFragment(),"Canceled");
        orderListViewPagerAdapter.addFragments(new InWarehouseFragment(),"In Warehouse");
        orderListViewPagerAdapter.addFragments(new InStoreFragment(),"In Store");
        orderListViewPagerAdapter.addFragments(new SoldFragment(),"Sold");

        viewPager.setAdapter(orderListViewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchOrderActivity.class);
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
