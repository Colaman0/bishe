package com.kyle.takeaway.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.item.ItemShopDetailViewModel;

import butterknife.BindView;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/20
 *     desc   :
 * </pre>
 */
public class ShopDetailActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private FeaturesAdapter mFeaturesAdapter;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeaturesAdapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview);
        recyclerview.setAdapter(mFeaturesAdapter);
        mFeaturesAdapter.add(new ItemShopDetailViewModel());
        mFeaturesAdapter.add(new ItemShopDetailViewModel());
        mFeaturesAdapter.add(new ItemShopDetailViewModel());
        mFeaturesAdapter.add(new ItemShopDetailViewModel());
        mFeaturesAdapter.add(new ItemShopDetailViewModel());
        mFeaturesAdapter.add(new ItemShopDetailViewModel());

    }
}
