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
import com.kyle.takeaway.base.bus.RxBus;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.item.ItemCommitOrderViewModel;
import com.kyle.takeaway.util.DialogUtil;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.internal.functions.Functions;

/**
 * Create by kyle on 2019/2/19
 * Function :
 */
public class CommitOrderActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    FeaturesAdapter adapter;
    String json;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_commit_order;
    }

    @Override
    protected void initView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview);
        recyclerview.setAdapter(adapter);
        json = getIntent().getStringExtra(Constants.DATA);
        RetrofitManager.getInstance().preCommitOrder(json)
                .doOnNext(orderItemEntities -> {
                    for (int i = 0; i < orderItemEntities.size(); i++) {
                        adapter.add(new ItemCommitOrderViewModel(orderItemEntities.get(i)));
                    }
                    adapter.notifyDataSetChanged();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());

    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        DialogUtil.getSureTipsDialog(getContext(), "确认提交订单?", (dialog, which) -> commit()).show();
    }

    private void commit() {
        Intent intent = getDefaultIntent(SelectAddressActivity.class);
        intent.putExtra(Constants.DATA, true);
        getActivity().startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            int id = data.getIntExtra(Constants.DATA, 0);
            RetrofitManager.getInstance().commitOrder(json, id)
                    .doOnNext(orderItemEntities -> {
                        RxBus.getDefault().send(true, "cart");
                        RxBus.getDefault().send(true, "order");
                        ToastUtils.showShort("提交成功");
                        finish();
                    })
                    .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
        }
    }
}
