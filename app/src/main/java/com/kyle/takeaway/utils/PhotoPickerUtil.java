package com.kyle.takeaway.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.kyle.takeaway.BuildConfig;
import com.kyle.takeaway.base.MyGlideEngine;
import com.yanzhenjie.permission.Permission;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.Set;

import io.reactivex.functions.Action;


/**
 * function: 基于知乎matisse的图片选择调用
 * <p>
 * create by kyle on 2018/4/3
 */
public class PhotoPickerUtil {

    public static void pickPhoto(final Activity activity, final int maxNum, final Set<MimeType> photoType, final boolean canCapture, final int requestCode) {
        // 检查权限
        checkPermission(activity, new Action() {
            @Override
            public void run() throws Exception {
                // 调用matisse，传入参数
                showPhotoPicker(activity, maxNum, photoType, canCapture, requestCode);
            }
        });
    }

    /**
     * 检查图片选择所需要的权限如：读写，摄像头
     */
    private static void checkPermission(final Context context, final Action success) {
        PermissionUtil.getInstance().requestPermission(context, new PermissionUtil.RequestPermissionListener() {
            @Override
            public void requestSuccess() {
                try {
                    success.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void requestFail() {
                showWithoutPermissionTips(context);
            }
        }, Permission.Group.CAMERA, Permission.Group.STORAGE);
    }


    /**
     * show matisse
     *
     * @param activity    上下文
     * @param maxNum      最多选择几张图片
     * @param photoType   筛选出可选的图片类型
     * @param canCapture  是否支持拍照
     * @param requestCode 需要传入一个code，在activityresult里通过code去取选中的图片的路径
     */
    public static void showPhotoPicker(Activity activity, int maxNum, Set<MimeType> photoType, boolean canCapture, int requestCode) {
        Matisse.from(activity)
                .choose(photoType)
                .capture(canCapture)
                .captureStrategy(new CaptureStrategy(true, BuildConfig.APPLICATION_ID + ".fileprovider"))
                .maxSelectable(maxNum)
                .imageEngine(new MyGlideEngine())
                .forResult(requestCode);
    }

    private static void showWithoutPermissionTips(Context context) {
        Toast.makeText(context, " we can't user the camera without permission", Toast.LENGTH_SHORT).show();
    }
}
