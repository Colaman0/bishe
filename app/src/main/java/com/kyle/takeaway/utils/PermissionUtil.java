package com.kyle.takeaway.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.kyle.takeaway.R;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;
import com.yanzhenjie.permission.Setting;

import java.util.List;


/**
 * function:权限申请的简单封装，直接调用requestPermission方法去申请权限。
 * create by kyle on 2018/4/3
 */
public class PermissionUtil {
    private PermissionUtil() {
    }

    public static PermissionUtil getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private final static PermissionUtil instance = new PermissionUtil();
    }

    /**
     * 申请权限
     *
     * @param context     上下文
     * @param permissions 需要的权限
     * @param listener    结果回调
     */
    public void requestPermission(final Context context, final RequestPermissionListener listener, final String... permissions) {
        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .rationale(new DefaultRationale(listener))
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        if (listener != null) {
                            if (checkHasPermission(context, data)) {
                                listener.requestSuccess();
                            } else {
                                listener.requestSuccess();
                            }
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        // 如果是直接被拒绝并且不弹出申请框的情况下，弹出跳到设置页面的dialog让用户自行打开权限
                        deniedJudgement(data, context, listener);
                    }
                })
                .start();
    }


    /**
     * 当回调了denied方法时，再判断一下权限以及做处理
     *
     * @param data
     * @param context
     * @param listener
     */
    public void deniedJudgement(List<String> data, Context context, RequestPermissionListener listener) {
        if (checkHasPermission(context, data)) {
            if (listener != null) {
                listener.requestSuccess();
            }
            return;
        }
        if (AndPermission.hasAlwaysDeniedPermission(context, data)) {
            showSettingDialog(context, listener, data);
        } else {
            // 如果不是直接被拒绝而是手动点拒绝的申请下，直接回调失败
            if (listener != null) {
                listener.requestFail();
            }
        }
    }


    /**
     * 申请权限
     *
     * @param context     上下文
     * @param permissions 需要的权限组
     */
    public PermissionUtil requestPermission(final Context context, final RequestPermissionListener listener, final String[]... permissions) {
        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .rationale(new DefaultRationale(listener))
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        // 用户同意权限，回调成功
                        if (listener != null) {
                            if (checkHasPermission(context, data)) {
                                listener.requestSuccess();
                            } else {
                                listener.requestSuccess();
                            }
                        }
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        // 如果是直接被拒绝并且不弹出申请框的情况下，弹出跳到设置页面的dialog让用户自行打开权限
                        deniedJudgement(data, context, listener);
                    }
                })
                .start();
        return this;
    }


    /**
     * 查看权限是否被允许
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkHasPermission(Context context, String[]... permissions) {
        return AndPermission.hasPermissions(context, permissions);
    }

    /**
     * 查看权限是否被允许
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkHasPermission(Context context, String... permissions) {
        return AndPermission.hasPermissions(context, permissions);
    }

    /**
     * 查看权限是否被允许
     *
     * @param context
     * @param permissions
     * @return
     */
    public static boolean checkHasPermission(Context context, List<String> permissions) {
        String[] strings = new String[permissions.size()];
        return AndPermission.hasPermissions(context, permissions.toArray(strings));
    }

    /**
     * 当用户拒绝过一次之后，提醒用户权限的作用
     */
    static class DefaultRationale implements Rationale {
        private RequestPermissionListener mRequestPermissionListener;

        public DefaultRationale(RequestPermissionListener requestPermissionListener) {
            mRequestPermissionListener = requestPermissionListener;
        }

        @Override
        public void showRationale(Context context, Object permissions, final RequestExecutor executor) {
            List<String> permissionNames = Permission.transformText(context, (List<String>) permissions);
            String message = String.format(context.getString(R.string.rationale_dialog_msg), permissionNames);
            // 弹出一个提醒dialog
            new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setTitle(context.getString(R.string.rationale_dialog_title))
                    .setMessage(message)
                    .setPositiveButton(context.getString(R.string.rationale_dialog_positive),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // 点击提示框的允许之后重新申请一次权限
                                    if (executor != null) {
                                        executor.execute();
                                    }
                                }
                            })
                    .setNegativeButton(context.getString(R.string.rationale_dialog_negative),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // 点击拒绝后回调失败
                                    if (executor != null) {
                                        executor.cancel();
                                    }
                                    if (mRequestPermissionListener != null) {
                                        mRequestPermissionListener.requestFail();
                                    }
                                }
                            })
                    .show();
        }
    }

    /**
     * 弹出一个dialog，提醒用户去设置页打开权限
     *
     * @param context
     * @param listener
     * @param permission
     */
    private void showSettingDialog(final Context context, final RequestPermissionListener listener, final List<String> permission) {
        List<String> permissionNames = Permission.transformText(context, permission);
        String message = String.format(context.getString(R.string.setting_dialog_msg), permissionNames);
        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle(context.getString(R.string.setting_dialog_title))
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.setting_dialog_positive),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 跳转到设置页
                                gotoSetting(context, listener, permission);
                            }
                        })
                .setNegativeButton(context.getString(R.string.setting_dialog_negative),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // 用户取消跳转到设置页，直接回调失败
                                if (listener != null) {
                                    listener.requestFail();
                                }
                            }
                        }).show();
    }

    /**
     * 跳到设置页面
     *
     * @param context
     * @param listener
     * @param permission
     */
    private void gotoSetting(final Context context, final RequestPermissionListener listener, final List<String> permission) {
        AndPermission.with(context)
                .runtime()
                .setting()
                .onComeback(new Setting.Action() {
                    @Override
                    public void onAction() {
                        if (listener != null) {
                            // 用户从设置页设置完之后，重新检查一下权限是否被允许，然后回调结果
                            if (checkHasPermission(context, permission)) {
                                listener.requestSuccess();
                            } else {
                                listener.requestFail();
                            }
                        }
                    }
                })
                .start();
    }

    public interface RequestPermissionListener {
        void requestSuccess();

        void requestFail();
    }
}
