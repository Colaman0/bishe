package com.kyle.takeaway.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create by kyle on 2019/2/28
 * Function :
 */
public class CartItemEntity {


    /**
     * store_id : 1
     * food_list : [{"store_id":1,"shipping_fee":2,"food_name":"雷姆龙","update_time":1,"delete_time":0,"user_id":1,"price":10000,"num":1,"store_name":"龙会","food_id":1,"add_time":1,"car_food_id":1},{"store_id":1,"shipping_fee":2,"food_name":"霸王龙","update_time":1,"delete_time":0,"user_id":1,"price":8000,"num":2,"store_name":"龙会","food_id":2,"add_time":1,"car_food_id":2}]
     * store_name : 龙会
     */

    @SerializedName("store_id")
    private int storeId;
    @SerializedName("store_name")
    private String storeName;
    @SerializedName("food_list")
    private List<FoodListBean> foodList;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<FoodListBean> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodListBean> foodList) {
        this.foodList = foodList;
    }

    public static class FoodListBean {
        /**
         * store_id : 1
         * shipping_fee : 2
         * food_name : 雷姆龙
         * update_time : 1
         * delete_time : 0
         * user_id : 1
         * price : 10000
         * num : 1
         * store_name : 龙会
         * food_id : 1
         * add_time : 1
         * car_food_id : 1
         */

        @SerializedName("store_id")
        private int storeId;
        @SerializedName("shipping_fee")
        private int shippingFee;
        @SerializedName("food_name")
        private String foodName;
        @SerializedName("update_time")
        private int updateTime;
        @SerializedName("delete_time")
        private int deleteTime;
        @SerializedName("user_id")
        private int userId;
        @SerializedName("price")
        private int price;
        @SerializedName("num")
        private int num;
        @SerializedName("store_name")
        private String storeName;
        @SerializedName("food_id")
        private int foodId;
        @SerializedName("add_time")
        private int addTime;
        @SerializedName("car_food_id")
        private int carFoodId;

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public int getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(int shippingFee) {
            this.shippingFee = shippingFee;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
        }

        public int getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(int deleteTime) {
            this.deleteTime = deleteTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getFoodId() {
            return foodId;
        }

        public void setFoodId(int foodId) {
            this.foodId = foodId;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getCarFoodId() {
            return carFoodId;
        }

        public void setCarFoodId(int carFoodId) {
            this.carFoodId = carFoodId;
        }
    }
}
