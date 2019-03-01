package com.kyle.takeaway.entity;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/3/1
 *     desc   :
 * </pre>
 */
public class CommitOrderParam {
    public CommitOrderParam(int car_food_id, int food_id, int num) {
        this.car_food_id = car_food_id;
        this.food_id = food_id;
        this.num = num;
    }

    /**
     * car_food_id : 1
     * food_id : 1
     * num : 1
     */

    private int car_food_id;
    private int food_id;
    private int num;

    public int getCar_food_id() {
        return car_food_id;
    }

    public void setCar_food_id(int car_food_id) {
        this.car_food_id = car_food_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
