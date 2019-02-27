package com.kyle.takeaway.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.entity.ProductEntity;
import com.kyle.takeaway.item.ItemShopDetailViewModel;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

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
    private int id;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_shop_detail;
    }

    @Override
    protected void initView() {
        id = getIntent().getIntExtra(Constants.DATA,0);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeaturesAdapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview);
        recyclerview.setAdapter(mFeaturesAdapter);
        getData();
    }

    private void getData() {
        mFeaturesAdapter.clear();
        RetrofitManager.getInstance().getStoreDetail(id)
                .doOnNext(productEntities -> {
                    for (int i = 0; i < productEntities.size(); i++) {
                        mFeaturesAdapter.add(new ItemShopDetailViewModel(productEntities.get(i)));
                    }
                    mFeaturesAdapter.notifyDataSetChanged();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }
}
