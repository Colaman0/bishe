package com.kyle.takeaway.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.item.ItemAddressViewModel;
import com.kyle.takeaway.util.DialogUtil;
import com.kyle.takeaway.view.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by kyle on 2019/2/19
 * Function :
 */
public class AddressActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    private FeaturesAdapter mFeaturesAdapter;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView() {
        titleBar.addViewToRight(TitleBar.getTextView(getContext(), "添加", v -> goToAcitivty(AddAddressActivity.class)));

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeaturesAdapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview);
        recyclerview.setAdapter(mFeaturesAdapter);
        mFeaturesAdapter.add(new ItemAddressViewModel());
        mFeaturesAdapter.add(new ItemAddressViewModel());
        mFeaturesAdapter.add(new ItemAddressViewModel());
        mFeaturesAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        DialogUtil.getSureTipsDialog(getContext(), "确认删除?", (dialog, which) -> delete()).show();
    }

    private void delete() {

    }

}
