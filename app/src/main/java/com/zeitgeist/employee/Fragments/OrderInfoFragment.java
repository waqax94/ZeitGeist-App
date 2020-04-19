package com.zeitgeist.employee.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.OrderDetail;
import com.zeitgeist.employee.R;
import com.zeitgeist.employee.Services.APIService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderInfoFragment extends Fragment {

    public String customerId;
    public String orderId;
    IpClass ipClass = new IpClass();
    ProgressDialog pDialog;
    TextView oId,oType,oPattern,oSize,oDate,oDeliveryDate,oItem,oStatus,oPrice;

    public OrderInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_order_info, container, false);

        final SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat newFormat = new SimpleDateFormat("dd MM yyyy");

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

        oId = (TextView) rootView.findViewById(R.id.order_id_value);
        oType = (TextView) rootView.findViewById(R.id.order_type_value);
        oPattern = (TextView) rootView.findViewById(R.id.basepattern_value);
        oSize = (TextView) rootView.findViewById(R.id.basesize_value);
        oDate = (TextView) rootView.findViewById(R.id.order_date_value);
        oDeliveryDate = (TextView) rootView.findViewById(R.id.delivery_date_value);
        oItem = (TextView) rootView.findViewById(R.id.item_type_value);
        oStatus = (TextView) rootView.findViewById(R.id.order_status_value);
        oPrice = (TextView) rootView.findViewById(R.id.order_price_value);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        final APIService service = retrofit.create(APIService.class);

        showpDialog();

        Call<OrderDetail> orderInfo = service.ProcessOrderDetail(orderId);

        orderInfo.enqueue(new Callback<OrderDetail>() {
            @Override
            public void onResponse(Response<OrderDetail> response, Retrofit retrofit) {
                hidepDialog();

                OrderDetail orderDetail = response.body();

                if(response.body() != null){
                    oId.setText(orderDetail.getOrderMainId());
                    oType.setText(orderDetail.getOrderType());
                    oPattern.setText(orderDetail.getBasePattern());
                    oSize.setText(orderDetail.getBaseSize());
                    try {
                        oDate.setText(newFormat.format(oldFormat.parse(orderDetail.getOrderDate())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        oDeliveryDate.setText(newFormat.format(oldFormat.parse(orderDetail.getDeliveryDate())));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    oItem.setText(orderDetail.getItemType());
                    oStatus.setText(orderDetail.getOrderStatus());
                    oPrice.setText("Rs. " + orderDetail.getOrderPrice());

                }
            }

            @Override
            public void onFailure(Throwable t) {
                hidepDialog();
                Toast.makeText(getActivity().getApplicationContext(),"Connection Error",Toast.LENGTH_LONG).show();
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
