package com.kyle.takeaway.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.BaseViewModel;
import com.kyle.takeaway.base.CommonDiffCallBack;
import com.kyle.takeaway.base.IImageLoad;
import com.kyle.takeaway.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by kyle on 2019/1/8
 * Function : 带有diffutil刷新的adapter
 */
public class FeaturesAdapter extends ListAdapter<FeaturesAdapter> {
    private IImageLoad mIImageLoad;
    private List<BaseViewModel> oldDatas = new ArrayList<>();
    // 是否开始loadmore的标记
    private boolean mDisableLoadmore = false;

    public FeaturesAdapter(Context context) {
        super(context);
    }

    @Override
    protected BaseViewHolder getHolder(Context context, ViewGroup viewGroup, int itemType) {
        return getDefaultBuilder(context, viewGroup, itemType)
                .build();
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        return super.onCreateViewHolder(viewGroup, itemType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
    }

    @Override
    public int getItemCount() {
        return getDatas().size();
    }

    @SuppressLint("CheckResult")
    public void diffNotifydatasetchanged() {
        // TODO: 2019/1/8 加入生命周期的监听 autodisposable
        Observable.just("")
                .observeOn(AndroidSchedulers.mainThread())
                .map(s -> DiffUtil.calculateDiff(getDiffCallback(), false))
                .doOnNext(diffResult -> getRecyclerView().post(() -> diffResult.dispatchUpdatesTo(FeaturesAdapter.this)))
                .doOnComplete(() -> {
                    oldDatas.clear();
                    oldDatas.addAll(getDatas());
                })
                .subscribe(Functions.emptyConsumer(), Functions.emptyConsumer());
    }

    private DiffUtil.Callback getDiffCallback() {
        return new CommonDiffCallBack(oldDatas, getDatas());
    }

    /**
     * insert/remove掉loadmoreviewmodel
     */
    private void switchLoadMore() {
        if (mDisableLoadmore) {
            notifyItemInserted(getItemCount());
        } else {
            notifyItemRemoved(getItemCount());
        }
    }

    /**
     * 设置图片加载类
     *
     * @param imageLoader
     * @return
     */
    public FeaturesAdapter setImageLoader(IImageLoad imageLoader) {
        mIImageLoad = imageLoader == null ? getDefaultImageLoader() : imageLoader;
        return this;
    }

    public IImageLoad getIImageLoad() {
        return mIImageLoad;
    }

    /**
     * 默认用Glide加载图片
     *
     * @return
     */
    protected IImageLoad getDefaultImageLoader() {
        return GlideImageLoader.getInstance();
    }


    /**
     * 是否开启loadmore
     *
     * @param disableLoadmore 是否允许loadmore
     */
    public void canLoadMore(boolean disableLoadmore) {
        if (disableLoadmore != mDisableLoadmore) {
            mDisableLoadmore = disableLoadmore;
            // 避免异步处理数据的时候抛出异常
            getRecyclerView().post(this::switchLoadMore);
        }
    }

    /**
     * 判断loadmore的item是否显示
     *
     * @param position
     * @return
     */
    public boolean isLoadmoreVisible(int position) {
        return position == getItemCount() - 1 && mDisableLoadmore;
    }
}
