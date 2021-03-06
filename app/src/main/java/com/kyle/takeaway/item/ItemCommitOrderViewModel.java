package com.kyle.takeaway.item;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.OrderItemEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Action;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/19
 *     desc   :
 * </pre>
 */
public class ItemCommitOrderViewModel extends RecyclerViewModel {

    private List<RecyclerViewModel> datas = new ArrayList<>();
    private FeaturesAdapter adapter;
    private OrderItemEntity mOrderItemEntity;

    public ItemCommitOrderViewModel(OrderItemEntity orderItemEntity) {
        mOrderItemEntity = orderItemEntity;
        for (int i = 0; i < mOrderItemEntity.getFood_list().size(); i++) {
            datas.add(new ItemCommitOrderProductViewModel(mOrderItemEntity.getFood_list().get(i).getFood_name(),
                    mOrderItemEntity.getFood_list().get(i).getNum()));
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.include_commit_order;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        holder.setText(R.id.tv_name, mOrderItemEntity.getStore_name());
        RecyclerView recyclerView = holder.getView(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(holder.getContext()));
        adapter = new FeaturesAdapter(holder.getContext());
        recyclerView.setAdapter(adapter);
        adapter.addAll(datas);
    }


    @Override
    public boolean isSameData(Object o) {
        return false;
    }
}
