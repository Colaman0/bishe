package com.kyle.takeaway.entity;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/27
 *     desc   :
 * </pre>
 */
public class StoreEntity {
    /**
     * store_id : 1
     * shipping_fee : 1.0
     * mobile_phone : 13790099900
     * store_name : 测试店铺
     * avatar : http://xmy1996.oicp.io:42418/upload/temp/2.png
     * sales : 12
     */

    private int store_id;
    private double shipping_fee;
    private String mobile_phone;
    private String store_name;
    private String avatar;
    private int sales;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}
