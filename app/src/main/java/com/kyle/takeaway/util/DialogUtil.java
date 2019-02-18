package com.kyle.takeaway.util;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kyle.takeaway.R;

public class DialogUtil {

    public static MaterialDialog.Builder getBaseDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .contentColorRes(R.color.black)
                .titleColorRes(R.color.black)
                .backgroundColorRes(R.color.white)
                .cancelable(false);
    }

    /**
     * 获取一个取消按钮为绿色的dialog
     *
     * @param context
     * @param content
     * @param callback
     * @return
     */
    public static MaterialDialog.Builder getCancelTipsDialog(Context context, String content, MaterialDialog.SingleButtonCallback callback) {
        return getBaseDialog(context)
                .onPositive(callback)
                .negativeText("取消")
                .positiveText("确认")
                .content(content)
                .negativeColorRes(R.color.colorPrimary)
                .positiveColorRes(R.color.colorPrimary);
    }

    /**
     * 获取一个确定按钮为绿色的dialog
     *
     * @param context
     * @param content
     * @param callback
     * @return
     */
    public static MaterialDialog.Builder getSureTipsDialog(Context context, String content, MaterialDialog.SingleButtonCallback callback) {
        return getBaseDialog(context)
                .onPositive(callback)
                .negativeText("取消")
                .positiveText("确认")
                .content(content)
                .negativeColorRes(R.color.colorPrimary)
                .positiveColorRes(R.color.colorPrimary);
    }

    /**
     * 获取一个退出登录的dialog
     *
     * @param context
     * @param callback
     * @return
     */
    public static MaterialDialog.Builder getLogoutTipsDialog(Context context, MaterialDialog.SingleButtonCallback callback) {
        return getBaseDialog(context)
                .onPositive(callback)
                .negativeText("取消")
                .positiveText("确认")
                .title("退出登录")
                .content("确认退出登录?")
                .negativeColorRes(R.color.colorPrimary)
                .positiveColorRes(R.color.colorPrimary);
    }
}
