package com.kyle.takeaway.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Create by kyle on 2018/9/25
 * Function :
 */
public class ImageLoader {
    private RequestOptions mRequestOptions;

    private ImageLoader() {
        mRequestOptions = initDefaultOption();
    }

    public static ImageLoader getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final ImageLoader instance = new ImageLoader();
    }

    /**
     * init一个默认的requestOption
     *
     * @return
     */
    private RequestOptions initDefaultOption() {
        return new RequestOptions().centerInside();
    }

    /**
     * 普通加载图片
     *
     * @param context
     * @param imageView
     * @param source
     */
    public void loadImg(Context context, ImageView imageView, String source) {
        Glide.with(context).load(source).apply(getDefaultOptions()).into(imageView);
    }

    /**
     * 普通加载图片(附带监听)
     *
     * @param context
     * @param imageView
     * @param source
     * @param listener
     */
    public void loadImgWithListener(Context context, ImageView imageView, String source, RequestListener listener) {
        Glide.with(context).load(source).apply(getDefaultOptions()).listener(listener).into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param imageView
     * @param source
     */
    public void loadCircleImg(Context context, ImageView imageView, String source) {
        Glide.with(context).load(source).apply(getDefaultOptions().circleCrop()).into(imageView);
    }



    public void loadCornerImg(Context context, ImageView imageView, String source, int radius) {
        Glide.with(context)
                .load(source)
                .apply(getNewOption().transform(new RoundedCornersTransformation(radius, 0, RoundedCornersTransformation.CornerType.ALL)))
                .into(imageView);
    }

    /**
     * 下载一个bitmap
     *
     * @param context
     * @param source
     * @return
     */
    public Observable<Bitmap> downLoadBitmap(final Context context, final String source) {
        return Observable.just(source)
                .subscribeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, FutureTarget<Bitmap>>() {
                    @Override
                    public FutureTarget<Bitmap> apply(String s) throws Exception {
                        return Glide.with(context).asBitmap().load(source).apply(getDefaultOptions()).submit();
                    }
                })
                .observeOn(Schedulers.newThread())
                .map(new Function<FutureTarget<Bitmap>, Bitmap>() {
                    @Override
                    public Bitmap apply(FutureTarget<Bitmap> bitmapFutureTarget) throws Exception {
                        return bitmapFutureTarget.get();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }


    private RequestOptions getDefaultOptions() {
        return mRequestOptions;
    }

    private RequestOptions getNewOption() {
        return initDefaultOption();
    }
}
