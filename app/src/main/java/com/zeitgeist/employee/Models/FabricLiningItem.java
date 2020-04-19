package com.zeitgeist.employee.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 9/22/2017.
 */

public class FabricLiningItem {

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
    @SerializedName("f_l_mtm_3_suit")
    @Expose
    private String itemMtm3suitPrice;
    @SerializedName("f_l_mtm_2_suit")
    @Expose
    private String itemMtm2suitPrice;
    @SerializedName("f_l_mtm_jacket")
    @Expose
    private String itemMtmJacketPrice;
    @SerializedName("f_l_mtm_waist_coat")
    @Expose
    private String itemMtmWaistCoatPrice;
    @SerializedName("f_l_mtm_trouser")
    @Expose
    private String itemMtmTrouserPrice;
    @SerializedName("f_l_mtm_shirt")
    @Expose
    private String itemMtmShirtPrice;
    @SerializedName("f_l_bs_3_suit")
    @Expose
    private String itemBs3suitPrice;
    @SerializedName("f_l_bs_2_suit")
    @Expose
    private String itemBs2suitPrice;
    @SerializedName("f_l_bs_jacket")
    @Expose
    private String itemBsJacketPrice;
    @SerializedName("f_l_bs_waist_coat")
    @Expose
    private String itemBsWaistCoatPrice;
    @SerializedName("f_l_bs_trouser")
    @Expose
    private String itemBsTrouserPrice;
    @SerializedName("f_l_bs_shirt")
    @Expose
    private String itemBsShirtPrice;


    public FabricLiningItem() {
    }

    public FabricLiningItem(String itemId, String itemNumber, String itemImageSource, String stock, String itemMtm3suitPrice, String itemMtm2suitPrice, String itemMtmJacketPrice, String itemMtmWaistCoatPrice, String itemMtmTrouserPrice, String itemMtmShirtPrice, String itemBs3suitPrice, String itemBs2suitPrice, String itemBsJacketPrice, String itemBsWaistCoatPrice, String itemBsTrouserPrice, String itemBsShirtPrice) {
        this.itemId = itemId;
        this.itemNumber = itemNumber;
        this.itemImageSource = itemImageSource;
        this.stock = stock;
        this.itemMtm3suitPrice = itemMtm3suitPrice;
        this.itemMtm2suitPrice = itemMtm2suitPrice;
        this.itemMtmJacketPrice = itemMtmJacketPrice;
        this.itemMtmWaistCoatPrice = itemMtmWaistCoatPrice;
        this.itemMtmTrouserPrice = itemMtmTrouserPrice;
        this.itemMtmShirtPrice = itemMtmShirtPrice;
        this.itemBs3suitPrice = itemBs3suitPrice;
        this.itemBs2suitPrice = itemBs2suitPrice;
        this.itemBsJacketPrice = itemBsJacketPrice;
        this.itemBsWaistCoatPrice = itemBsWaistCoatPrice;
        this.itemBsTrouserPrice = itemBsTrouserPrice;
        this.itemBsShirtPrice = itemBsShirtPrice;
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

    public String getItemMtm3suitPrice() {
        return itemMtm3suitPrice;
    }

    public void setItemMtm3suitPrice(String itemMtm3suitPrice) {
        this.itemMtm3suitPrice = itemMtm3suitPrice;
    }

    public String getItemMtm2suitPrice() {
        return itemMtm2suitPrice;
    }

    public void setItemMtm2suitPrice(String itemMtm2suitPrice) {
        this.itemMtm2suitPrice = itemMtm2suitPrice;
    }

    public String getItemMtmJacketPrice() {
        return itemMtmJacketPrice;
    }

    public void setItemMtmJacketPrice(String itemMtmJacketPrice) {
        this.itemMtmJacketPrice = itemMtmJacketPrice;
    }

    public String getItemMtmWaistCoatPrice() {
        return itemMtmWaistCoatPrice;
    }

    public void setItemMtmWaistCoatPrice(String itemMtmWaistCoatPrice) {
        this.itemMtmWaistCoatPrice = itemMtmWaistCoatPrice;
    }

    public String getItemMtmTrouserPrice() {
        return itemMtmTrouserPrice;
    }

    public void setItemMtmTrouserPrice(String itemMtmTrouserPrice) {
        this.itemMtmTrouserPrice = itemMtmTrouserPrice;
    }

    public String getItemMtmShirtPrice() {
        return itemMtmShirtPrice;
    }

    public void setItemMtmShirtPrice(String itemMtmShirtPrice) {
        this.itemMtmShirtPrice = itemMtmShirtPrice;
    }

    public String getItemBs3suitPrice() {
        return itemBs3suitPrice;
    }

    public void setItemBs3suitPrice(String itemBs3suitPrice) {
        this.itemBs3suitPrice = itemBs3suitPrice;
    }

    public String getItemBs2suitPrice() {
        return itemBs2suitPrice;
    }

    public void setItemBs2suitPrice(String itemBs2suitPrice) {
        this.itemBs2suitPrice = itemBs2suitPrice;
    }

    public String getItemBsJacketPrice() {
        return itemBsJacketPrice;
    }

    public void setItemBsJacketPrice(String itemBsJacketPrice) {
        this.itemBsJacketPrice = itemBsJacketPrice;
    }

    public String getItemBsWaistCoatPrice() {
        return itemBsWaistCoatPrice;
    }

    public void setItemBsWaistCoatPrice(String itemBsWaistCoatPrice) {
        this.itemBsWaistCoatPrice = itemBsWaistCoatPrice;
    }

    public String getItemBsTrouserPrice() {
        return itemBsTrouserPrice;
    }

    public void setItemBsTrouserPrice(String itemBsTrouserPrice) {
        this.itemBsTrouserPrice = itemBsTrouserPrice;
    }

    public String getItemBsShirtPrice() {
        return itemBsShirtPrice;
    }

    public void setItemBsShirtPrice(String itemBsShirtPrice) {
        this.itemBsShirtPrice = itemBsShirtPrice;
    }
}
