package com.kyle.takeaway.util;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/26
 *     desc   :
 * </pre>
 */
public class UserHelper {
    private static boolean mIsUser;
    private static int mId;

    public static void setUserInfo(boolean isUser, int id) {
        mIsUser = isUser;
        mId = id;
    }

    public static int getAccountType() {
        return mIsUser ? 1 : 2;
    }
}
