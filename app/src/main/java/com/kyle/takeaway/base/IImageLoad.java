package com.kyle.takeaway.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.widget.ImageView;


/**
 * Create by kyle on 2019/1/7
 * Function : 图片加载框架的接口规范
 */
public interface IImageLoad {
    void loadImage(@NonNull Context context, String url, @NonNull ImageView imageView);

    void loadImage(@NonNull Context context, String url, @NonNull ImageView imageView, @DrawableRes int loadingRes, @DrawableRes int errorRes);

    void loadCircleImage(@NonNull Context context, String url, @NonNull ImageView imageView);



    void loadDrawable(@NonNull Context context, @NonNull ImageView imageView, @DrawableRes int drawableRes);

    void loadDrawable(@NonNull Context context, @NonNull ImageView imageView, @NonNull Drawable drawable);
}
