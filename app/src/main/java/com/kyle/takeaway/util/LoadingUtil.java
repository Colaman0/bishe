package com.kyle.takeaway.util;

import android.content.Context;
import android.content.DialogInterface;

import com.afollestad.materialdialogs.MaterialDialog;
import com.kyle.takeaway.R;

/**
 * Create by kyle on 2019/2/17
 * Function :
 */
public class LoadingUtil {

    static MaterialDialog mMaterialDialog = null;

    /**
     * show loading
     */
    public static MaterialDialog showMaterLoading(Context context, String message) {
        return showMaterLoading(context, message, null);
    }

    /**
     * show loading
     */
    public static MaterialDialog showMaterLoading(final Context context, final String message, final DialogInterface.OnCancelListener listener) {
        if (mMaterialDialog != null && mMaterialDialog.isShowing()) {
            mMaterialDialog.setOnCancelListener(listener);
            mMaterialDialog.setContent(message);
        } else {
            mMaterialDialog = new MaterialDialog.Builder(context)
                    .progress(true, 0)
                    .widgetColorRes(R.color.colorAccent)
                    .content(message)
                    .cancelable(listener != null)
                    .cancelListener(listener)
                    .show();
        }
        return mMaterialDialog;
    }

    /**
     * hide loading
     */
    public static void hideMaterLoading() {
        if (mMaterialDialog != null && mMaterialDialog.isShowing()) {
            mMaterialDialog.dismiss();
            mMaterialDialog = null;
        }
    }

}
