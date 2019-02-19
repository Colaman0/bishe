package com.kyle.takeaway.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.item.ItemCommitOrderViewModel;
import com.kyle.takeaway.util.DialogUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by kyle on 2019/2/19
 * Function :
 */
public class CommitOrderActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_commit_order;
    }

    @Override
    protected void initView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        FeaturesAdapter adapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview);
        recyclerview.setAdapter(adapter);
        adapter.add(new ItemCommitOrderViewModel());
        adapter.add(new ItemCommitOrderViewModel());
        adapter.add(new ItemCommitOrderViewModel());

    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        DialogUtil.getSureTipsDialog(getContext(), "确认提交订单?", (dialog, which) -> commit()).show();
    }

    private void commit() {

    }
}
