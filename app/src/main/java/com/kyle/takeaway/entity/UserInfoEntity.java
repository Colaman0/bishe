package com.kyle.takeaway.entity;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/27
 *     desc   :
 * </pre>
 */
public class UserInfoEntity {

    /**
     * user_id : 2
     * mobile_phone : 18565257571
     * sex : 1
     * nickname : 123
     * avatar :
     */

    private int user_id;
    private String mobile_phone;
    private int sex;
    private String nickname;
    private String avatar;

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
