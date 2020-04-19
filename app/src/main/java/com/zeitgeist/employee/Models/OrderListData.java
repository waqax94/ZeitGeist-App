package com.zeitgeist.employee.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 10/9/2017.
 */

public class OrderListData {

    @SerializedName("id")
    @Expose
    private String orderMainId;
    @SerializedName("o_id")
    @Expose
    private String orderId;
    @SerializedName("c_id")
    @Expose
    private String customerId;
    @SerializedName("c_name")
    @Expose
    private String customerName;
    @SerializedName("o_fabric")
    @Expose
    private String fabricId;
    @SerializedName("o_date")
    @Expose
    private String orderDate;
    @SerializedName("item_type")
    @Expose
    private String itemType;
    @SerializedName("o_status")
    @Expose
    private String orderStatus;
    @SerializedName("o_price")
    @Expose
    private String orderPrice;
    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;

    public OrderListData() {
    }

    public OrderListData(String orderMainId, String orderId, String customerId, String customerName, String fabricId, String orderDate, String itemType, String orderStatus, String orderPrice, String deliveryDate) {
        this.orderMainId = orderMainId;
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.fabricId = fabricId;
        this.orderDate = orderDate;
        this.itemType = itemType;
        this.orderStatus = orderStatus;
        this.orderPrice = orderPrice;
        this.deliveryDate = deliveryDate;
    }

    public String getOrderMainId() {
        return orderMainId;
    }

    public void setOrderMainId(String orderMainId) {
        this.orderMainId = orderMainId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getFabricId() {
        return fabricId;
    }

    public void setFabricId(String fabricId) {
        this.fabricId = fabricId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
