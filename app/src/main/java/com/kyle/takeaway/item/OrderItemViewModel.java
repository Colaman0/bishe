package com.kyle.takeaway.item;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.activity.AddCommentActivity;
import com.kyle.takeaway.activity.AddReturnActivity;
import com.kyle.takeaway.activity.ShopDetailActivity;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.entity.OrderItemEntity;

import java.util.ArrayList;
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
public class OrderItemViewModel extends RecyclerViewModel {
    private final OrderItemEntity mOrderItemEntity;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    @BindView(R.id.tv_return)
    TextView tvReturn;

    private int mOrderId;

    public OrderItemViewModel(OrderItemEntity orderItemEntity) {
        mOrderItemEntity = orderItemEntity;
        mOrderId = mOrderItemEntity.getOrder_id();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_order;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        ButterKnife.bind(this, holder.getConvertView());
        holder.setText(R.id.tv_name, mOrderItemEntity.getStore_name());
        holder.setText(R.id.tv_status, mOrderItemEntity.getStatus_name());
        holder.setVisibility(R.id.tv_comment, mOrderItemEntity.getIs_evaluation() == 0 && mOrderItemEntity.getStatus() > 1);
        RecyclerView recyclerView = holder.getView(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(holder.getContext()));
        FeaturesAdapter adapter = new FeaturesAdapter(holder.getContext());
        recyclerView.setAdapter(adapter);
        adapter.addAll(getViewModels());
    }

    private List<RecyclerViewModel> getViewModels() {
        List<RecyclerViewModel> viewModels = new ArrayList<>();
        for (int i = 0; i < mOrderItemEntity.getFood_list().size(); i++) {
            viewModels.add(new ItemProductViewModel(
                    String.valueOf(mOrderItemEntity.getFood_list().get(i).getPrice()),
                    String.valueOf(mOrderItemEntity.getFood_list().get(i).getFood_name())));
        }
        return viewModels;
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }

    @OnClick({R.id.tv_comment, R.id.tv_return})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_comment:
                Intent intentComment = new Intent(getContext(), AddCommentActivity.class);
                intentComment.putExtra(Constants.DATA, mOrderId);
                getContext().startActivity(intentComment);
                break;
            case R.id.tv_return:
                Intent intentReturn = new Intent(getContext(), AddReturnActivity.class);
                intentReturn.putExtra(Constants.DATA, mOrderId);
                getContext().startActivity(intentReturn);
                break;
        }
    }

    @Override
    public void onItemClick() {
        super.onItemClick();
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        intent.putExtra(Constants.DATA, mOrderItemEntity.getStore_id());
        getContext().startActivity(intent);
    }
}
