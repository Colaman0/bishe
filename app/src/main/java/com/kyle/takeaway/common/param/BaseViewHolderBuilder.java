package com.kyle.takeaway.common.param;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.IImageLoad;

import java.util.function.Consumer;


/**
 * Create by kyle on 2019/1/6
 * Function : 构建baseviewholder所需要的参数
 */
public class BaseViewHolderBuilder {
    private Context mContext;
    private ViewGroup mViewGroup;
    private int mLayoutId;
    private View mItemView;
    private Consumer<BaseViewHolder> mItemClickConsumer;
    private IImageLoad mImageLoader;


    public BaseViewHolderBuilder(Context context, ViewGroup viewGroup, int layoutId) {
        mContext = context;
        mViewGroup = viewGroup;
        mLayoutId = layoutId;
    }

    public Context getContext() {
        return mContext;
    }

    public BaseViewHolderBuilder setContext(Context context) {
        mContext = context;
        return this;
    }

    public ViewGroup getViewGroup() {
        return mViewGroup;
    }

    public BaseViewHolderBuilder setViewGroup(ViewGroup viewGroup) {
        mViewGroup = viewGroup;
        return this;
    }

    public int getLayoutId() {
        return mLayoutId;
    }

    public BaseViewHolderBuilder setLayoutId(int layoutId) {
        mLayoutId = layoutId;
        return this;
    }

    public View getItemView() {
        return mItemView;
    }

    public BaseViewHolderBuilder setItemView(View itemView) {
        mItemView = itemView;
        return this;
    }

    public BaseViewHolder build() {
        if (getContext() != null) {
            setItemView(LayoutInflater.from(getContext()).inflate(getLayoutId(), getViewGroup(), false));
        }
        return new BaseViewHolder(this);
    }

    public Consumer<BaseViewHolder> getItemClickConsumer() {
        return mItemClickConsumer;
    }

    public BaseViewHolderBuilder setItemClickConsumer(Consumer<BaseViewHolder> itemClickConsumer) {
        mItemClickConsumer = itemClickConsumer;
        return this;
    }

    public IImageLoad getImageLoader() {
        return mImageLoader;
    }

    public BaseViewHolderBuilder setImageLoader(IImageLoad imageLoader) {
        mImageLoader = imageLoader;
        return this;
    }
}
