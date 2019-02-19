package com.kyle.takeaway.item;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;

import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/19
 *     desc   :
 * </pre>
 */
public class OrderItemViewModel extends RecyclerViewModel {
    @Override
    public int getLayoutRes() {
        return R.layout.item_order;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        holder.setText(R.id.tv_name, "店铺");
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
}
