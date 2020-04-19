package com.zeitgeist.employee.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 9/26/2017.
 */

public class Customer {

    @SerializedName("c_id")
    @Expose
    private String customerId;
    @SerializedName("c_name")
    @Expose
    private String customerName;
    @SerializedName("p_phone")
    @Expose
    private String customerPrimaryPhone;
    @SerializedName("s_phone")
    @Expose
    private String customerSecondaryPhone;
    @SerializedName("c_email")
    @Expose
    private String customerEmail;
    @SerializedName("c_city")
    @Expose
    private String customerCity;
    @SerializedName("c_address")
    @Expose
    private String customerAddress;

    public Customer() {
    }

    public Customer(String customerId, String customerName, String customerPrimaryPhone, String customerSecondaryPhone, String customerEmail, String customerCity, String customerAddress) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPrimaryPhone = customerPrimaryPhone;
        this.customerSecondaryPhone = customerSecondaryPhone;
        this.customerEmail = customerEmail;
        this.customerCity = customerCity;
        this.customerAddress = customerAddress;
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

    public String getCustomerPrimaryPhone() {
        return customerPrimaryPhone;
    }

    public void setCustomerPrimaryPhone(String customerPrimaryPhone) {
        this.customerPrimaryPhone = customerPrimaryPhone;
    }

    public String getCustomerSecondaryPhone() {
        return customerSecondaryPhone;
    }

    public void setCustomerSecondaryPhone(String customerSecondaryPhone) {
        this.customerSecondaryPhone = customerSecondaryPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
