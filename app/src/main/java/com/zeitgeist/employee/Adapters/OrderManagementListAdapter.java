package com.zeitgeist.employee.Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zeitgeist.employee.Activities.FeedbackActivity;
import com.zeitgeist.employee.Models.FabricLiningItem;
import com.zeitgeist.employee.Models.IpClass;
import com.zeitgeist.employee.Models.OrderListData;
import com.zeitgeist.employee.R;
import com.zeitgeist.employee.Services.APIService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by User on 10/9/2017.
 */

public class OrderManagementListAdapter extends ArrayAdapter {
    SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat newFormat = new SimpleDateFormat("dd MM yyyy");
    IpClass ipClass = new IpClass();

    List orderList = new ArrayList();
    Context context;

    public OrderManagementListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    public class Handler{
        TextView orderId;
        TextView fabricNumber;
        TextView customerName;
        TextView itemType;
        TextView placeDate;
        TextView deliveryDate;
        TextView price;
        TextView status;
        Button sellBtn;
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    public void addOrderList(List<OrderListData> obj){
        orderList = obj;
    }

    @Override
    public int getCount() {
        return this.orderList.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.orderList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list;
        list = convertView;
        final OrderManagementListAdapter.Handler handler;


        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list = inflater.inflate(R.layout.custom_order_list_management,parent,false);
            handler = new OrderManagementListAdapter.Handler();
            handler.orderId = (TextView) list.findViewById(R.id.order_id);
            handler.fabricNumber = (TextView) list.findViewById(R.id.fabric_number_data);
            handler.customerName = (TextView) list.findViewById(R.id.customer_name_data);
            handler.itemType = (TextView) list.findViewById(R.id.item_type);
            handler.placeDate = (TextView) list.findViewById(R.id.placed_date);
            handler.deliveryDate = (TextView) list.findViewById(R.id.delivery_date);
            handler.price = (TextView) list.findViewById(R.id.price);
            handler.status = (TextView) list.findViewById(R.id.status);
            handler.sellBtn = (Button) list.findViewById(R.id.sell_btn);

            list.setTag(handler);
        }
        else {
            handler = (OrderManagementListAdapter.Handler)list.getTag();
        }

        final OrderListData orderListData;
        orderListData = (OrderListData) this.getItem(position);


        if(orderListData.getFabricId().equals("0")){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ipClass.ipAddress).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIService service = retrofit.create(APIService.class);

            Call<String> selfFabricData = service.RetriveCustomFabricNumber(orderListData.getOrderId());

            selfFabricData.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Response<String> response, Retrofit retrofit) {
                    handler.fabricNumber.setText(response.body());
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
        else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ipClass.ipAddress).
                            addConverterFactory(GsonConverterFactory.create())
                    .build();
            APIService service = retrofit.create(APIService.class);

            Call<FabricLiningItem> fabricInfo = service.RetriveFabric(orderListData.getFabricId());

            fabricInfo.enqueue(new Callback<FabricLiningItem>() {
                @Override
                public void onResponse(Response<FabricLiningItem> response, Retrofit retrofit) {
                    try{
                        handler.fabricNumber.setText(response.body().getItemNumber());
                    }
                    catch (NullPointerException e){

                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }

        handler.orderId.setText(orderListData.getOrderMainId());
        handler.customerName.setText(orderListData.getCustomerName());
        handler.itemType.setText(orderListData.getItemType());
        try {
            handler.placeDate.setText(newFormat.format(oldFormat.parse(orderListData.getOrderDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            handler.deliveryDate.setText(newFormat.format(oldFormat.parse(orderListData.getDeliveryDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        handler.price.setText(orderListData.getOrderPrice());
        handler.status.setText(orderListData.getOrderStatus());

        if(!orderListData.getOrderStatus().equals("In Store")){
            handler.sellBtn.setVisibility(View.GONE);
        }

        handler.sellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedbackActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("customerId", "" + orderListData.getCustomerId());
                intent.putExtra("orderId", "" + orderListData.getOrderId());
                intent.putExtra("customerName", "" + orderListData.getCustomerName());
                context.startActivity(intent);
            }
        });

        return list;

    }

    public void sortByCustomerName(){

        Collections.sort(orderList, new Comparator<OrderListData>() {
            @Override
            public int compare(OrderListData o1, OrderListData o2) {
                return o1.getCustomerName().compareTo(o2.getCustomerName());
            }
        });

        notifyDataSetChanged();
    }

    public void sortByCustomerNameReverse(){

        Collections.sort(orderList, new Comparator<OrderListData>() {
            @Override
            public int compare(OrderListData o1, OrderListData o2) {
                return o2.getCustomerName().compareTo(o1.getCustomerName());
            }
        });

        notifyDataSetChanged();
    }

    public void sortByDeliveryDate(){

        Collections.sort(orderList, new Comparator<OrderListData>() {
            @Override
            public int compare(OrderListData o1, OrderListData o2) {
                int i = 0;
                try {
                    i = oldFormat.parse(o2.getDeliveryDate()).compareTo(oldFormat.parse(o1.getDeliveryDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return i;
            }
        });

        notifyDataSetChanged();
    }

    public void sortByOrderDate(){

        Collections.sort(orderList, new Comparator<OrderListData>() {
            @Override
            public int compare(OrderListData o1, OrderListData o2) {
                int i = 0;
                try {
                    i = oldFormat.parse(o2.getOrderDate()).compareTo(oldFormat.parse(o1.getOrderDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return i;
            }
        });

        notifyDataSetChanged();
    }

    public List<OrderListData> getUpdatedList(){
        return orderList;
    }
}
