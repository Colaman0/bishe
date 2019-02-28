package com.kyle.takeaway.entity;

import java.util.List;

/**
 * Create by kyle on 2019/2/28
 * Function :
 */
public class OrderItemEntity {
    /**
     * store_id : 6
     * user_avatar :
     * evaluation_id : null
     * delete_time : 0
     * address : 广东省广州市天河区龙洞牌坊
     * is_evaluation : 0
     * level_num : null
     * status_name : 待配送
     * total_pay_fee : 202.0
     * avatar : http://xmy1996.oicp.io:42418/upload/temp/JPEG_20181026_145717.jpg
     * epics : []
     * finish_time : 0
     * content : null
     * shipping_fee : 2.0
     * update_time : 0
     * food_list : [{"store_id":6,"food_name":"象拔蚌4","cover_url":"http://xmy1996.oicp.io:42418/upload/temp/2.png","delete_time":1551339670,"num":1,"description":"象拔蚌","pic_urls":["http://xmy1996.oicp.io:42418/upload/temp/2.png"],"food_id":6,"update_time":1551339663,"user_id":1,"price":200,"add_time":1,"order_id":11,"order_food_id":15}]
     * user_id : 5
     * mobile_phone : 18565257557
     * nickname : 123
     * store_name : 123321
     * order_id : 11
     * add_time : 1550669898
     * order_sn : 2019022009381836107314
     * status : 1
     */

    private int store_id;
    private String user_avatar;
    private Object evaluation_id;
    private int delete_time;
    private String address;
    private int is_evaluation;
    private Object level_num;
    private String status_name;
    private double total_pay_fee;
    private String avatar;
    private int finish_time;
    private Object content;
    private double shipping_fee;
    private int update_time;
    private int user_id;
    private String mobile_phone;
    private String nickname;
    private String store_name;
    private int order_id;
    private int add_time;
    private String order_sn;
    private int status;
    private List<?> epics;
    private List<FoodListBean> food_list;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getUser_avatar() {
        return user_avatar;
    }

    public void setUser_avatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public Object getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(Object evaluation_id) {
        this.evaluation_id = evaluation_id;
    }

    public int getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(int delete_time) {
        this.delete_time = delete_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIs_evaluation() {
        return is_evaluation;
    }

    public void setIs_evaluation(int is_evaluation) {
        this.is_evaluation = is_evaluation;
    }

    public Object getLevel_num() {
        return level_num;
    }

    public void setLevel_num(Object level_num) {
        this.level_num = level_num;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public double getTotal_pay_fee() {
        return total_pay_fee;
    }

    public void setTotal_pay_fee(double total_pay_fee) {
        this.total_pay_fee = total_pay_fee;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(int finish_time) {
        this.finish_time = finish_time;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public double getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(double shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<?> getEpics() {
        return epics;
    }

    public void setEpics(List<?> epics) {
        this.epics = epics;
    }

    public List<FoodListBean> getFood_list() {
        return food_list;
    }

    public void setFood_list(List<FoodListBean> food_list) {
        this.food_list = food_list;
    }

    public static class FoodListBean {
        /**
         * store_id : 6
         * food_name : 象拔蚌4
         * cover_url : http://xmy1996.oicp.io:42418/upload/temp/2.png
         * delete_time : 1551339670
         * num : 1
         * description : 象拔蚌
         * pic_urls : ["http://xmy1996.oicp.io:42418/upload/temp/2.png"]
         * food_id : 6
         * update_time : 1551339663
         * user_id : 1
         * price : 200.0
         * add_time : 1
         * order_id : 11
         * order_food_id : 15
         */

        private int store_id;
        private String food_name;
        private String cover_url;
        private int delete_time;
        private int num;
        private String description;
        private int food_id;
        private int update_time;
        private int user_id;
        private double price;
        private int add_time;
        private int order_id;
        private int order_food_id;
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

        public int getDelete_time() {
            return delete_time;
        }

        public void setDelete_time(int delete_time) {
            this.delete_time = delete_time;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
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

        public int getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(int update_time) {
            this.update_time = update_time;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getOrder_food_id() {
            return order_food_id;
        }

        public void setOrder_food_id(int order_food_id) {
            this.order_food_id = order_food_id;
        }

        public List<String> getPic_urls() {
            return pic_urls;
        }

        public void setPic_urls(List<String> pic_urls) {
            this.pic_urls = pic_urls;
        }
    }
}
