package com.kyle.takeaway.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create by kyle on 2019/2/28
 * Function :
 */
public class CartParam {
    @SerializedName("user_id")
    private int userId;
    @SerializedName("foods_json")
    private List<CartProductParam> products;



    public List<CartProductParam> getProducts() {
        return products;
    }

    public void setProducts(List<CartProductParam> products) {
        this.products = products;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
