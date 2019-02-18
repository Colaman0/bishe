package com.kyle.takeaway.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.kyle.takeaway.base.IImageLoad;

import java.util.concurrent.Future;


/**
 * Create by kyle on 2019/1/7
 * Function : Glide 图片加载辅助类
 */
public class GlideImageLoader implements IImageLoad {
    // Glide的配置
    private RequestOptions mRequestOptions;

    private GlideImageLoader() {

    }

    public static GlideImageLoader getInstance() {
        return Holder.instance;
    }


    private static class Holder {
        private static final GlideImageLoader instance = new GlideImageLoader();
    }


    /**
     * 获取默认的配置
     *
     * @return
     */
    public RequestOptions getDefaultOptions() {
        if (mRequestOptions == null) {
            mRequestOptions = initDefaultOption();
        }
        return mRequestOptions;
    }

    /**
     * init一个默认的requestOption
     *
     * @return
     */
    private RequestOptions initDefaultOption() {
        return new RequestOptions()
                .centerInside();
    }

    /**
     * 获取一个新的配置
     *
     * @return
     */
    protected RequestOptions getNewOption() {
        return initDefaultOption();
    }

    /**
     * 加载图片,使用全局默认的配置
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, getDefaultOptions());
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param loadingRes 加载中的占位图
     * @param errorRes   错误的占位图
     */
    @Override
    public void loadImage(Context context, String url, ImageView imageView, int loadingRes, int errorRes) {
        loadImage(context, url, imageView, getNewOption().placeholder(loadingRes).error(errorRes));
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Override
    public void loadCircleImage(Context context, String url, ImageView imageView) {
        loadImage(context, url, imageView, getDefaultOptions().circleCrop());
    }


    @Override
    public void loadDrawable(Context context, ImageView imageView, int drawableRes) {
        if (imageView == null || context == null) {
            return;
        }
        Glide.with(context).load(drawableRes).apply(getDefaultOptions()).into(imageView);
    }

    /**
     * 加载drawable
     *
     * @param context
     * @param imageView
     * @param drawable
     */
    @Override
    public void loadDrawable(@NonNull Context context, @NonNull ImageView imageView, @NonNull Drawable drawable) {
        Glide.with(context).load(drawable).apply(getDefaultOptions()).into(imageView);
    }

    /**
     * 加载图片，传入自定义的Glide的配置
     *
     * @param context
     * @param url
     * @param imageView
     * @param options   Glide 配置
     */
    public void loadImage(Context context, String url, ImageView imageView, RequestOptions options) {
        if (imageView == null || context == null) {
            return;
        }
        if (options == null) {
            options = getDefaultOptions();
        }
        Glide.with(context).load(url).apply(options).into(imageView);
    }


}
