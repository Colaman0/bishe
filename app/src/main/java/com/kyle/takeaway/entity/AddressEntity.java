package com.kyle.takeaway.entity;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/27
 *     desc   :
 * </pre>
 */
public class AddressEntity {
    /**
     * user_id : 2
     * address_id : 3
     * content : 123486
     */

    private int user_id;
    private int address_id;
    private String content;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
