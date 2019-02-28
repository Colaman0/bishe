package com.kyle.takeaway.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.entity.SellHistoryEntity;
import com.kyle.takeaway.item.ItemListViewModel;
import com.kyle.takeaway.util.UserHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import retrofit2.Retrofit;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/28
 *     desc   :
 * </pre>
 */
public class SellHistoryActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private FeaturesAdapter mFeaturesAdapter;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_sell_history;
    }

    @Override
    protected void initView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeaturesAdapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview);
        recyclerview.setAdapter(mFeaturesAdapter);

        RetrofitManager.getInstance().getSellHistory(UserHelper.getmStoreId())
                .doOnNext(sellHistoryEntities -> {
                    for (int i = 0; i < sellHistoryEntities.size(); i++) {
                        mFeaturesAdapter.add(new ItemListViewModel(sellHistoryEntities.get(i)));
                    }
                    mFeaturesAdapter.notifyDataSetChanged();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
