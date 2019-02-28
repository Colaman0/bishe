package com.kyle.takeaway.entity;

import java.util.List;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/28
 *     desc   :
 * </pre>
 */
public class SellHistoryEntity {
    /**
     * food_name : 香蕉船
     * cover_url : http://xmy1996.oicp.io:42418/upload/temp/2.png
     * price : 100.0
     * num : 2
     * total_fee : 200.0
     * description : 香蕉船香蕉船香蕉船香蕉船香蕉船香蕉船
     * pic_urls : ["http://xmy1996.oicp.io:42418/upload/temp/2.png"]
     * food_id : 5
     */

    private String food_name;
    private String cover_url;
    private double price;
    private int num;
    private double total_fee;
    private String description;
    private int food_id;
    private List<String> pic_urls;

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(double total_fee) {
        this.total_fee = total_fee;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public List<String> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<String> pic_urls) {
        this.pic_urls = pic_urls;
    }
}
