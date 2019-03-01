package com.kyle.takeaway.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create by kyle on 2019/2/28
 * Function :
 */
public class PreCommitOrderEntity {
    /**
     * store_id : 1
     * shipping_fee : 2
     * food_list : [{"store_id":1,"food_name":"雷姆龙","cover_url":"xmy1996.oicp.io:42418/upload/temp/2.png","delete_time":0,"num":1,"pic_urls":["xmy1996.oicp.io:42418/upload/temp/2.png"],"food_id":1,"shipping_fee":2,"update_time":1550763026,"user_id":1,"price":10000,"store_name":"龙会","add_time":1550763026,"order_id":0},{"store_id":1,"food_name":"霸王龙","cover_url":"xmy1996.oicp.io:42418/upload/temp/2.png","delete_time":0,"num":2,"pic_urls":["xmy1996.oicp.io:42418/upload/temp/2.png"],"food_id":2,"shipping_fee":2,"update_time":1550763026,"user_id":1,"price":8000,"store_name":"龙会","add_time":1550763026,"order_id":0}]
     * user_id : 1
     * store_name : 龙会
     * total_pay_fee : 26002
     * add_time : 1550763026
     */

    @SerializedName("store_id")
    private int storeId;
    @SerializedName("shipping_fee")
    private int shippingFee;
    @SerializedName("user_id")
    private int userId;
    @SerializedName("store_name")
    private String storeName;
    @SerializedName("total_pay_fee")
    private int totalPayFee;
    @SerializedName("add_time")
    private int addTime;
    @SerializedName("food_list")
    private List<FoodListBean> foodList;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getTotalPayFee() {
        return totalPayFee;
    }

    public void setTotalPayFee(int totalPayFee) {
        this.totalPayFee = totalPayFee;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
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
         * food_name : 雷姆龙
         * cover_url : xmy1996.oicp.io:42418/upload/temp/2.png
         * delete_time : 0
         * num : 1
         * pic_urls : ["xmy1996.oicp.io:42418/upload/temp/2.png"]
         * food_id : 1
         * shipping_fee : 2
         * update_time : 1550763026
         * user_id : 1
         * price : 10000
         * store_name : 龙会
         * add_time : 1550763026
         * order_id : 0
         */

        @SerializedName("store_id")
        private int storeId;
        @SerializedName("food_name")
        private String foodName;
        @SerializedName("cover_url")
        private String coverUrl;
        @SerializedName("delete_time")
        private int deleteTime;
        @SerializedName("num")
        private int num;
        @SerializedName("food_id")
        private int foodId;
        @SerializedName("shipping_fee")
        private int shippingFee;
        @SerializedName("update_time")
        private int updateTime;
        @SerializedName("user_id")
        private int userId;
        @SerializedName("price")
        private int price;
        @SerializedName("store_name")
        private String storeName;
        @SerializedName("add_time")
        private int addTime;
        @SerializedName("order_id")
        private int orderId;
        @SerializedName("pic_urls")
        private List<String> picUrls;

        public int getStoreId() {
            return storeId;
        }

        public void setStoreId(int storeId) {
            this.storeId = storeId;
        }

        public String getFoodName() {
            return foodName;
        }

        public void setFoodName(String foodName) {
            this.foodName = foodName;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public int getDeleteTime() {
            return deleteTime;
        }

        public void setDeleteTime(int deleteTime) {
            this.deleteTime = deleteTime;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getFoodId() {
            return foodId;
        }

        public void setFoodId(int foodId) {
            this.foodId = foodId;
        }

        public int getShippingFee() {
            return shippingFee;
        }

        public void setShippingFee(int shippingFee) {
            this.shippingFee = shippingFee;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
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

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public int getAddTime() {
            return addTime;
        }

        public void setAddTime(int addTime) {
            this.addTime = addTime;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public List<String> getPicUrls() {
            return picUrls;
        }

        public void setPicUrls(List<String> picUrls) {
            this.picUrls = picUrls;
        }
    }
}
