package com.kyle.takeaway.util;

import com.kyle.takeaway.entity.UserInfoEntity;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/26
 *     desc   :
 * </pre>
 */
public class UserHelper {
    private static boolean isInit = false;
    private static boolean mIsUser;
    private static int mId;
    private static UserInfoEntity mEntity;

    public static void setUserInfo(boolean isUser, int id) {
        isInit = true;
        mIsUser = isUser;
        mId = id;
    }

    public static int getAccountType() {
        return mIsUser ? 1 : 2;
    }

    public static void logOut() {
        isInit = false;
        mId = -1;
    }

    public static boolean isInit() {
        return isInit;
    }

    public static int getId() {
        return mId;
    }

    public static UserInfoEntity getUserInfo() {
        return mEntity;
    }

    public static void setUserInfoEntity(UserInfoEntity entity) {
        mEntity = entity;
        mId = entity.getUser_id();
    }
}
