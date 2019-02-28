package com.kyle.takeaway.entity;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/28
 *     desc   :
 * </pre>
 */
public class StoreInfoEntity {
    /**
     * store_id : 6
     * shipping_fee : 0.0
     * address :
     * mobile_phone : 18565257557
     * store_name : 123
     * avatar :
     */

    private int store_id;
    private double shipping_fee;
    private String address;
    private String mobile_phone;
    private String store_name;
    private String avatar;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
