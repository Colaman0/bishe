package com.kyle.takeaway.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.ToastUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.base.bus.RxBus;
import com.kyle.takeaway.entity.OrderItemEntity;
import com.kyle.takeaway.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.internal.functions.Functions;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/19
 *     desc   :
 * </pre>
 */
public class MerchantOrderItemViewModel extends RecyclerViewModel {
    private final OrderItemEntity mOrderItemEntity;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_ship)
    TextView tvShip;
    private int id;

    public MerchantOrderItemViewModel(OrderItemEntity orderItemEntity) {
        mOrderItemEntity = orderItemEntity;
        id = orderItemEntity.getOrder_id();
    }


    @Override
    public int getLayoutRes() {
        return R.layout.item_merchant_order;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        ButterKnife.bind(this, holder.getConvertView());
        holder.setText(R.id.tv_address, mOrderItemEntity.getAddress());
        RecyclerView recyclerView = holder.getView(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(holder.getContext()));
        FeaturesAdapter adapter = new FeaturesAdapter(holder.getContext());
        recyclerView.setAdapter(adapter);
        adapter.addAll(getViewModels());
        tvShip.setVisibility(mOrderItemEntity.getStatus() == 1 ? View.VISIBLE : View.GONE);
    }

    private List<RecyclerViewModel> getViewModels() {
        List<RecyclerViewModel> recyclerViewModels = new ArrayList<>();
        for (int i = 0; i < mOrderItemEntity.getFood_list().size(); i++) {
            recyclerViewModels.add(new ItemProductViewModel(String.valueOf(mOrderItemEntity.getFood_list().get(i).getPrice()),
                    mOrderItemEntity.getFood_list().get(i).getFood_name()));
        }
        return recyclerViewModels;
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
                RetrofitManager.getInstance().sendOrder(id)
                        .doOnNext(o -> {
                            ToastUtils.showShort("操作成功");
                            RxBus.getDefault().send(true, "order_refresh");
                        })
                        .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
            }
        }).show();
    }

}
