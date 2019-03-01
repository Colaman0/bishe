package com.kyle.takeaway.entity;

import java.util.List;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/3/1
 *     desc   :
 * </pre>
 */
public class CommentEntity {

    /**
     * store_id : 6
     * evaluation_id : 3
     * food_list : [{"food_name":"香蕉船","cover_url":"http://xmy1996.oicp.io:42418/upload/temp/2.png","price":100,"num":1}]
     * user_id : 2
     * level_num : 0
     * store_name : 123321
     * pic_urls : [""]
     * avatar : http://xmy1996.oicp.io:42418/upload/temp/JPEG_20181026_145717.jpg
     * add_time : 1551421700
     * order_id : 13
     * content : 溜了溜了
     */

    private int store_id;
    private int evaluation_id;
    private int user_id;
    private int level_num;
    private String store_name;
    private String avatar;
    private int add_time;
    private int order_id;
    private String content;
    private List<FoodListBean> food_list;
    private List<String> pic_urls;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(int evaluation_id) {
        this.evaluation_id = evaluation_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLevel_num() {
        return level_num;
    }

    public void setLevel_num(int level_num) {
        this.level_num = level_num;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<FoodListBean> getFood_list() {
        return food_list;
    }

    public void setFood_list(List<FoodListBean> food_list) {
        this.food_list = food_list;
    }

    public List<String> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<String> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public static class FoodListBean {
        /**
         * food_name : 香蕉船
         * cover_url : http://xmy1996.oicp.io:42418/upload/temp/2.png
         * price : 100.0
         * num : 1
         */

        private String food_name;
        private String cover_url;
        private double price;
        private int num;

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
    }
}
