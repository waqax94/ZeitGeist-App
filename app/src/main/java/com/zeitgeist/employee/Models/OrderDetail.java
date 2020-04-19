package com.zeitgeist.employee.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 9/26/2017.
 */

public class OrderDetail {

    @SerializedName("id")
    @Expose
    private String orderMainId;
    @SerializedName("o_id")
    @Expose
    private String orderId;
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
    @SerializedName("order_type")
    @Expose
    private String orderType;
    @SerializedName("base_pattern")
    @Expose
    private String basePattern;
    @SerializedName("base_size")
    @Expose
    private String baseSize;

    public OrderDetail() {
    }

    public OrderDetail(String orderMainId, String orderId, String orderDate, String itemType, String orderStatus, String orderPrice, String deliveryDate, String orderType, String basePattern, String baseSize) {
        this.orderMainId = orderMainId;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.itemType = itemType;
        this.orderStatus = orderStatus;
        this.orderPrice = orderPrice;
        this.deliveryDate = deliveryDate;
        this.orderType = orderType;
        this.basePattern = basePattern;
        this.baseSize = baseSize;
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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getBasePattern() {
        return basePattern;
    }

    public void setBasePattern(String basePattern) {
        this.basePattern = basePattern;
    }

    public String getBaseSize() {
        return baseSize;
    }

    public void setBaseSize(String baseSize) {
        this.baseSize = baseSize;
    }
}
