package com.kyle.takeaway.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Create by kyle on 2019/2/28
 * Function :
 */
public class CartProductParam {
    /**
     * food_id : 1
     * num : 1
     * is_add : 0
     */

    @SerializedName("food_id")
    private int foodId;

    @SerializedName("num")
    private int num;
    @SerializedName("is_add")
    private int isAdd;

    public CartProductParam(int foodId, int num, int isAdd) {
        this.foodId = foodId;
        this.num = num;
        this.isAdd = isAdd;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(int isAdd) {
        this.isAdd = isAdd;
    }
}
