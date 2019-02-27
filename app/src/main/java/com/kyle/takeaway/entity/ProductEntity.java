package com.kyle.takeaway.entity;

import java.util.List;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/27
 *     desc   :
 * </pre>
 */
public class ProductEntity {
    /**
     * store_id : 1
     * food_name : 测试
     * cover_url : http://xmy1996.oicp.io:42418/upload/temp/2.png
     * price : 23123.0
     * description : 123
     * pic_urls : ["http://xmy1996.oicp.io:42418/upload/temp/2.png","http://xmy1996.oicp.io:42418/upload/temp/2.png"]
     * food_id : 1
     */

    private int store_id;
    private String food_name;
    private String cover_url;
    private double price;
    private String description;
    private int food_id;
    private List<String> pic_urls;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

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
