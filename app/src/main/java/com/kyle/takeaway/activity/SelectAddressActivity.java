package com.kyle.takeaway.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.item.ItemAddressViewModel;
import com.kyle.takeaway.item.ItemSelectAddressViewModel;
import com.kyle.takeaway.util.DialogUtil;
import com.kyle.takeaway.view.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

/**
 * Create by kyle on 2019/2/19
 * Function :
 */
public class SelectAddressActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private boolean isSelect = false;

    private FeaturesAdapter mFeaturesAdapter;
    private List<Integer> ids = new ArrayList<>();
    private List<RecyclerViewModel> mDeleteViewModels = new ArrayList<>();


    @Override
    protected int initLayoutRes() {
        return R.layout.activity_select_address;
    }

    @Override
    protected void initView() {
        isSelect = getIntent().getBooleanExtra(Constants.DATA, false);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeaturesAdapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview);
        recyclerview.setAdapter(mFeaturesAdapter);
        getData();
    }

    private void getData() {
        RetrofitManager.getInstance().getAddressList()
                .doOnNext(addressEntityOptional -> {
                    mFeaturesAdapter.clear();
                    for (int i = 0; i < addressEntityOptional.size(); i++) {
                        mFeaturesAdapter.add(new ItemSelectAddressViewModel(addressEntityOptional.get(i), new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Intent intent = new Intent();
                                intent.putExtra(Constants.DATA, integer);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        }));
                    }
                    mFeaturesAdapter.notifyDataSetChanged();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }
}
