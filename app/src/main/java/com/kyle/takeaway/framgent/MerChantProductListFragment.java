package com.kyle.takeaway.framgent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.activity.EditProdcutActivity;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.bus.RxBus;
import com.kyle.takeaway.item.ItemMerchantProductViewModel;
import com.kyle.takeaway.util.UserHelper;
import com.kyle.takeaway.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.internal.functions.Functions;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/21
 *     desc   :
 * </pre>
 */
public class MerChantProductListFragment extends SupportFragment {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private FeaturesAdapter mFeaturesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_merchant_product_list, container, false);
        ButterKnife.bind(this, view);
        titleBar.addViewToRight(TitleBar.getTextView(getContext(), "添加", v -> gotoAdd()));
        titleBar.setBackIconVisible(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeaturesAdapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview);
        recyclerview.setAdapter(mFeaturesAdapter);
        mFeaturesAdapter.notifyDataSetChanged();
        getData();
        RxBus.getDefault().receiveEvent(Boolean.class, "refresh")
                .doOnNext(aBoolean -> getData())
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
        return view;
    }

    private void getData() {
        mFeaturesAdapter.clear();
        RetrofitManager.getInstance().getStoreDetail(UserHelper.getmStoreId())
                .doOnNext(productEntities -> {
                    for (int i = 0; i < productEntities.size(); i++) {
                        mFeaturesAdapter.add(new ItemMerchantProductViewModel(productEntities.get(i)));
                    }
                    mFeaturesAdapter.notifyDataSetChanged();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }

    private void gotoAdd() {
        Intent intent = new Intent(getContext(), EditProdcutActivity.class);
        intent.putExtra("isEdit", false);
        getContext().startActivity(intent);
    }

}
