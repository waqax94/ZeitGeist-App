package com.zeitgeist.employee.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.Activities.OrderDisplayActivity;
import com.zeitgeist.employee.Adapters.OrderManagementListAdapter;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.OrderListData;
import com.zeitgeist.employee.R;
import com.zeitgeist.employee.Services.APIService;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingReviewFragment extends Fragment {

    ListView orderList;
    OrderManagementListAdapter orderManagementListAdapter;
    IpClass ipClass = new IpClass();
    TextView noOrders;
    ProgressDialog pDialog;
    LinearLayout orderListHeader;
    TextView orderIdHeading,fabricNumberHeading,customerNameHeading,itemTypeHeading,dateHeading,deliveryDateHeading,priceHeading,statusHeading;
    boolean sortName = true;
    String shopId;

    public PendingReviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_pending_review, container, false);

        SharedPreferences loginData = this.getActivity().getSharedPreferences("shopInfo", Context.MODE_PRIVATE);
        shopId = loginData.getString("shopId","");

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        Typeface typefaceB = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Raleway-Bold.ttf");

        orderIdHeading = (TextView)rootView.findViewById(R.id.order_id_heading);
        orderIdHeading.setTypeface(typefaceB);
        fabricNumberHeading = (TextView)rootView.findViewById(R.id.fabric_number_heading);
        fabricNumberHeading.setTypeface(typefaceB);
        customerNameHeading = (TextView)rootView.findViewById(R.id.customer_name_heading);
        customerNameHeading.setTypeface(typefaceB);
        itemTypeHeading = (TextView)rootView.findViewById(R.id.item_type_heading);
        itemTypeHeading.setTypeface(typefaceB);
        dateHeading = (TextView)rootView.findViewById(R.id.placed_date_heading);
        dateHeading.setTypeface(typefaceB);
        deliveryDateHeading = (TextView)rootView.findViewById(R.id.delivery_date_heading);
        deliveryDateHeading.setTypeface(typefaceB);
        priceHeading = (TextView)rootView.findViewById(R.id.price_heading);
        priceHeading.setTypeface(typefaceB);
        statusHeading = (TextView)rootView.findViewById(R.id.status_heading);
        statusHeading.setTypeface(typefaceB);

        orderListHeader = (LinearLayout)rootView.findViewById(R.id.order_list_header);
        orderList = (ListView)rootView.findViewById(R.id.order_list);
        noOrders = (TextView)rootView.findViewById(R.id.no_order_text);

        orderManagementListAdapter = new OrderManagementListAdapter(getActivity().getApplicationContext(),R.layout.custom_order_list_management);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        showpDialog();

        Call<List<OrderListData>> orderListData = service.ProcessOrderListByStatus(shopId,"Pending Review");

        orderListData.enqueue(new Callback<List<OrderListData>>() {
            @Override
            public void onResponse(Response<List<OrderListData>> response, Retrofit retrofit) {
                hidepDialog();
                final List<OrderListData> orderDataList = response.body();
                if(response.body() != null){

                    noOrders.setVisibility(View.GONE);
                    orderListHeader.setVisibility(View.VISIBLE);
                    orderList.setVisibility(View.VISIBLE);
                    orderManagementListAdapter.addOrderList(orderDataList);
                    orderList.setAdapter(orderManagementListAdapter);

                    orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity().getApplicationContext(),OrderDisplayActivity.class);
                            intent.putExtra("customerId", "" + orderDataList.get(position).getCustomerId());
                            intent.putExtra("orderId", "" + orderDataList.get(position).getOrderId());
                            startActivity(intent);
                        }
                    });
                }
                else {
                    orderList.setVisibility(View.GONE);
                    noOrders.setVisibility(View.VISIBLE);
                    orderListHeader.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                hidepDialog();
                Toast.makeText(getActivity().getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
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

        return rootView;
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
