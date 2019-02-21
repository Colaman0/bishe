package com.kyle.takeaway.item;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kyle.takeaway.R;
import com.kyle.takeaway.activity.AddCommentActivity;
import com.kyle.takeaway.activity.AddReturnActivity;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.util.DialogUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/19
 *     desc   :
 * </pre>
 */
public class MerchantOrderItemViewModel extends RecyclerViewModel {
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;


    @Override
    public int getLayoutRes() {
        return R.layout.item_merchant_order;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        ButterKnife.bind(this, holder.getConvertView());
        holder.setText(R.id.tv_address, "地址");
        RecyclerView recyclerView = holder.getView(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(holder.getContext()));
        FeaturesAdapter adapter = new FeaturesAdapter(holder.getContext());
        recyclerView.setAdapter(adapter);
        adapter.addAll(getViewModels());
    }

    private List<RecyclerViewModel> getViewModels() {
        return Arrays.asList(new ItemProductViewModel("15", "红烧鱼"), new ItemProductViewModel("16", "红烧茄子"));
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }

    @OnClick(R.id.tv_ship)
    public void onViewClicked(View view) {
        DialogUtil.getSureTipsDialog(getContext(), "确认订单已送出?", new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).show();
    }
}
