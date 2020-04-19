package com.zeitgeist.employee.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 8/26/2017.
 */

public class GridListItem {

    @SerializedName("itm_id")
    @Expose
    private String itemId;
    @SerializedName("lst_id")
    @Expose
    private String listId;
    @SerializedName("itm_name")
    @Expose
    private String itemName;
    @SerializedName("lst_name")
    @Expose
    private String listName;
    @SerializedName("itm_img_src")
    @Expose
    private String itemImageSource;
    @SerializedName("itm_price")
    @Expose
    private String itemPrice;

    public GridListItem() {
    }

    public GridListItem(String itemId, String listId, String itemName, String listName, String itemImageSource, String itemPrice) {
        this.itemId = itemId;
        this.listId = listId;
        this.itemName = itemName;
        this.listName = listName;
        this.itemImageSource = itemImageSource;
        this.itemPrice = itemPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getItemImageSource() {
        return itemImageSource;
    }

    public void setItemImageSource(String itemImageSource) {
        this.itemImageSource = itemImageSource;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
}
