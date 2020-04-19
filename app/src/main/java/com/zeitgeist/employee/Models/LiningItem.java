package com.zeitgeist.employee.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 11/15/2017.
 */

public class LiningItem {

    @SerializedName("itm_id")
    @Expose
    private String itemId;
    @SerializedName("itm_num")
    @Expose
    private String itemNumber;
    @SerializedName("itm_img_src")
    @Expose
    private String itemImageSource;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("l_l_3_suit")
    @Expose
    private String item3suitPrice;
    @SerializedName("l_l_2_suit")
    @Expose
    private String item2suitPrice;
    @SerializedName("l_l_jacket")
    @Expose
    private String itemJacketPrice;
    @SerializedName("l_l_waist_coat")
    @Expose
    private String itemWaistCoatPrice;

    public LiningItem() {
    }

    public LiningItem(String itemId, String itemNumber, String itemImageSource, String stock, String item3suitPrice, String item2suitPrice, String itemJacketPrice, String itemWaistCoatPrice) {
        this.itemId = itemId;
        this.itemNumber = itemNumber;
        this.itemImageSource = itemImageSource;
        this.stock = stock;
        this.item3suitPrice = item3suitPrice;
        this.item2suitPrice = item2suitPrice;
        this.itemJacketPrice = itemJacketPrice;
        this.itemWaistCoatPrice = itemWaistCoatPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemImageSource() {
        return itemImageSource;
    }

    public void setItemImageSource(String itemImageSource) {
        this.itemImageSource = itemImageSource;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getItem3suitPrice() {
        return item3suitPrice;
    }

    public void setItem3suitPrice(String item3suitPrice) {
        this.item3suitPrice = item3suitPrice;
    }

    public String getItem2suitPrice() {
        return item2suitPrice;
    }

    public void setItem2suitPrice(String item2suitPrice) {
        this.item2suitPrice = item2suitPrice;
    }

    public String getItemJacketPrice() {
        return itemJacketPrice;
    }

    public void setItemJacketPrice(String itemJacketPrice) {
        this.itemJacketPrice = itemJacketPrice;
    }

    public String getItemWaistCoatPrice() {
        return itemWaistCoatPrice;
    }

    public void setItemWaistCoatPrice(String itemWaistCoatPrice) {
        this.itemWaistCoatPrice = itemWaistCoatPrice;
    }
}
