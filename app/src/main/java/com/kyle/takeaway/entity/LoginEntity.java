package com.kyle.takeaway.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Create by kyle on 2019/2/23
 * Function :
 */
public class LoginEntity {

    /**
     * user_id : 20001
     */

    @SerializedName("user_id")
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
