package com.kyle.takeaway.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/27
 *     desc   :
 * </pre>
 */
public class ProductEntity implements Serializable {

    /**
     * store_id : 1
     * food_name : 雷姆龙龙
     * cover_url : http://xmy1996.oicp.io:42418/upload/temp/2.png
     * price : 10000.0
     * is_recommend : 0
     * description : 龙
     * pic_urls : ["http://xmy1996.oicp.io:42418/upload/temp/2.png"]
     * food_id : 1
     */

    @SerializedName("store_id")
    private int storeId;
    @SerializedName("food_name")
    private String foodName;
    @SerializedName("cover_url")
    private String coverUrl;
    @SerializedName("price")
    private double price;
    @SerializedName("is_recommend")
    private int isRecommend;
    @SerializedName("description")
    private String description;
    @SerializedName("food_id")
    private int foodId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public List<String> getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(List<String> picUrls) {
        this.picUrls = picUrls;
    }
}
