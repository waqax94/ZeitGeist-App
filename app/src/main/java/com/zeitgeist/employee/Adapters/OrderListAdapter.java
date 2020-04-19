package com.zeitgeist.employee.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zeitgeist.employee.Activities.OrderTakingActivity;
import com.zeitgeist.employee.Activities.OrderTakingJacket;
import com.zeitgeist.employee.Activities.OrderTakingPant;
import com.zeitgeist.employee.Activities.OrderTakingShirt;
import com.zeitgeist.employee.Activities.OrderTakingTwoPiece;
import com.zeitgeist.employee.Activities.OrderTakingWaistCoat;
import com.zeitgeist.employee.Models.FabricLiningItem;
import com.zeitgeist.employee.Models.OrderDetail;
import com.zeitgeist.employee.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 9/26/2017.
 */

public class OrderListAdapter extends ArrayAdapter {
    SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat newFormat = new SimpleDateFormat("dd MM yyyy");

    List orderDetails = new ArrayList();
    Context context;
    String customerId;

    public OrderListAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
    }

    static class Handler{
        TextView orderId;
        TextView orderPrice;
        TextView orderPlacedOnDate;
        TextView deliveryDate;
        TextView orderStatus;
        TextView itemType;
        TextView orderType;
        Button asNewOrderBtn;
        Button alterOrderBtn;
    }

    @Override
    public void add(Object object) {
        super.add(object);
    }

    public void addOrderList(List<OrderDetail> obj,String customerId){
        orderDetails = obj;
        this.customerId = customerId;
    }

    @Override
    public int getCount() {
        return this.orderDetails.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.orderDetails.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list;
        list = convertView;
        final OrderListAdapter.Handler handler;


        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list = inflater.inflate(R.layout.custom_order_list,parent,false);
            handler = new OrderListAdapter.Handler();
            handler.orderId = (TextView) list.findViewById(R.id.order_id_data);
            handler.orderPrice = (TextView) list.findViewById(R.id.order_price_data);
            handler.orderPlacedOnDate = (TextView) list.findViewById(R.id.placed_on_date_data);
            handler.deliveryDate = (TextView) list.findViewById(R.id.delivered_on_date_data);
            handler.itemType = (TextView) list.findViewById(R.id.item_type_data);
            handler.orderType = (TextView) list.findViewById(R.id.order_type_list_data);
            handler.orderStatus = (TextView) list.findViewById(R.id.status_data);
            handler.asNewOrderBtn = (Button) list.findViewById(R.id.as_new_order_btn);
            handler.alterOrderBtn = (Button) list.findViewById(R.id.alter_order_btn);

            list.setTag(handler);
        }
        else {
            handler = (OrderListAdapter.Handler)list.getTag();
        }

        final OrderDetail orderDetail;
        orderDetail = (OrderDetail) this.getItem(position);


        handler.orderId.setText(orderDetail.getOrderMainId());
        handler.orderPrice.setText(orderDetail.getOrderPrice());
        try {
            handler.orderPlacedOnDate.setText(newFormat.format(oldFormat.parse(orderDetail.getOrderDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            handler.deliveryDate.setText(newFormat.format(oldFormat.parse(orderDetail.getDeliveryDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        handler.itemType.setText(orderDetail.getItemType());
        handler.orderType.setText(orderDetail.getOrderType());
        handler.orderStatus.setText(orderDetail.getOrderStatus());

        if(!orderDetail.getOrderStatus().equals("Pending Review") && !orderDetail.getOrderStatus().equals("Pre-production")){
            handler.alterOrderBtn.setVisibility(View.GONE);
        }

        handler.asNewOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderDetail.getItemType().equals("3 piece suit")){
                    Intent intent = new Intent(getContext(), OrderTakingActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "As new order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
                else if(orderDetail.getItemType().equals("2 piece suit")){
                    Intent intent = new Intent(getContext(),OrderTakingTwoPiece.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "As new order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
                else if(orderDetail.getItemType().equals("Jacket")){
                    Intent intent = new Intent(getContext(),OrderTakingJacket.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "As new order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
                else if(orderDetail.getItemType().equals("Pant")){
                    Intent intent = new Intent(getContext(),OrderTakingPant.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "As new order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
                else if(orderDetail.getItemType().equals("Shirt")){
                    Intent intent = new Intent(getContext(),OrderTakingShirt.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "As new order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
                else if(orderDetail.getItemType().equals("Waist Coat")){
                    Intent intent = new Intent(getContext(),OrderTakingWaistCoat.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "As new order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
            }
        });

        handler.alterOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderDetail.getItemType().equals("3 piece suit")){
                    Intent intent = new Intent(getContext(),OrderTakingActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "Alter order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
                else if(orderDetail.getItemType().equals("2 piece suit")){
                    Intent intent = new Intent(getContext(),OrderTakingTwoPiece.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "Alter order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
                else if(orderDetail.getItemType().equals("Jacket")){
                    Intent intent = new Intent(getContext(),OrderTakingJacket.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "Alter order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
                else if(orderDetail.getItemType().equals("Pant")){
                    Intent intent = new Intent(getContext(),OrderTakingPant.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "Alter order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
                else if(orderDetail.getItemType().equals("Shirt")){
                    Intent intent = new Intent(getContext(),OrderTakingShirt.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "Alter order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
                else if(orderDetail.getItemType().equals("Waist Coat")){
                    Intent intent = new Intent(getContext(),OrderTakingWaistCoat.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("orderCondition", "Alter order");
                    intent.putExtra("customerId", "" + customerId);
                    intent.putExtra("orderId", "" + orderDetail.getOrderId());
                    intent.putExtra("orderType", "" + orderDetail.getOrderType());
                    context.startActivity(intent);
                }
            }
        });

        return list;

    }
}
