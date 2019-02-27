package com.kyle.takeaway.adapter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.common.imp.OnItemClickListener;
import com.kyle.takeaway.common.param.BaseViewHolderBuilder;
import com.kyle.takeaway.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;


/**
 * Create by kyle on 2018/12/24
 * Function :
 */
public class BaseAdapter<T extends BaseAdapter> extends RecyclerView.Adapter {
    private Lifecycle mLifeCycle;
    private List<RecyclerViewModel> mDatas = new ArrayList<>();
    private Context mContext;
    private RecyclerView mRecyclerView;
    private SparseArray<OnItemClickListener> mClickListeners;
    private Consumer<BaseViewHolder> mItemClickConsumer;


    public BaseAdapter(Context context) {
        this(Collections.<RecyclerViewModel>emptyList(), context);
    }

    public BaseAdapter(List<RecyclerViewModel> datas, Context context) {
        mDatas.addAll(datas);
        mContext = context;
        if (mContext instanceof LifecycleOwner) {
            mLifeCycle = ((LifecycleOwner) mContext).getLifecycle();
        }
    }

    public T getThis() {
        return (T) this;
    }

    private void initConfig() {
        handleRecyclerViewScroll();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemType) {
        return getHolder(mContext, viewGroup, itemType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof BaseViewHolder) {
            // 在这里绑定view以及生命周期
            RecyclerViewModel recyclerViewModel = mDatas.get(position);
            recyclerViewModel.bindView((BaseViewHolder) viewHolder);
            recyclerViewModel.bindLife(mLifeCycle);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getLayoutRes();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 获取viewholder
     *
     * @param context
     * @param viewGroup
     * @param itemType  这里把item的layoutRes当作itemType，所以直接传itemType进去
     * @return
     */
    protected BaseViewHolder getHolder(Context context, ViewGroup viewGroup, int itemType) {
        return getDefaultBuilder(context, viewGroup, itemType).build();
    }

    protected BaseViewHolderBuilder getDefaultBuilder(Context context, ViewGroup viewGroup, int itemType) {
        return new BaseViewHolderBuilder(context, viewGroup, itemType)
                .setImageLoader(GlideImageLoader.getInstance())
                .setItemClickConsumer(getClickConsumer());
    }

    /**
     * 获取item点击的回调，所有viewholder共用一个
     *
     * @return
     */
    private Consumer<BaseViewHolder> getClickConsumer() {
        if (mItemClickConsumer == null) {
            mItemClickConsumer = baseViewHolder -> {
                if (baseViewHolder == null || baseViewHolder.getAdapterPosition() >= getDatas().size()) {
                    return;
                }
                // 触发对应viewmodel的onItemClick方法
                getDatas().get(baseViewHolder.getAdapterPosition()).onItemClick();
                int size = mClickListeners.size();
                for (int i = 0; i < size; i++) {
                    OnItemClickListener onItemClickListener = mClickListeners.valueAt(i);
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(baseViewHolder.getAdapterPosition(), baseViewHolder.getConvertView());
                    }
                }
            };
        }
        return mItemClickConsumer;
    }

    public List<RecyclerViewModel> getDatas() {
        return mDatas;
    }

    public void add(RecyclerViewModel viewModel) {
        mDatas.add(viewModel);
    }

    public void remove(RecyclerViewModel viewModel) {
        mDatas.remove(viewModel);
    }

    public void remove(List<RecyclerViewModel> viewModels) {
        mDatas.removeAll(viewModels);
    }

    public Lifecycle getLifeCycle() {
        return mLifeCycle;
    }

    public T bindRecyclerView(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        initConfig();
        return getThis();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    /**
     * 处理recyclerview滑动相关
     */
    private void handleRecyclerViewScroll() {
        /**
         * 这里处理的是view的可见/不可见的回调，通过把recycleViewModel作为一个tag附着到view上，因为view是通用复用的
         * 所以通过bindView的时候，把recycleviewmodel作为tag set在viewholder上，然后再回调方法里，getTag
         * 就可以知道这个view对应哪个viewmodel
         */
        if (getRecyclerView() != null) {
            getRecyclerView().addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(@NonNull View view) {
                    if (view.getTag() instanceof RecyclerViewModel) {
                        ((RecyclerViewModel) view.getTag()).onViewAttached();
                    }
                }

                @Override
                public void onChildViewDetachedFromWindow(@NonNull View view) {
                    if (view.getTag() instanceof RecyclerViewModel) {
                        ((RecyclerViewModel) view.getTag()).onViewDetached();
                    }
                }
            });
        }
    }

    /**
     * 添加点击事件回调，整个itemclick的处理流程是通过一个consumer回调，来处理所有viewholder的根view点击回调，
     * 当有一个viewholder的view被点击之后，viewholder触发回调，把本身作为参数传递过来，然后再遍历整个listenerarray
     * 把view和position回调出去
     */
    public T addItemClickListener(OnItemClickListener listener) {
        if (listener == null) {
            return getThis();
        }
        if (mClickListeners == null) {
            mClickListeners = new SparseArray<>();
        }
        mClickListeners.put(mClickListeners.size(), listener);
        return getThis();
    }


}
