package com.kyle.takeaway.item;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;

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

    public ItemCommitOrderViewModel() {
        datas.add(new ItemCommitOrderProductViewModel(3));
        datas.add(new ItemCommitOrderProductViewModel(3));
        datas.add(new ItemCommitOrderProductViewModel(3));
    }

    @Override
    public int getLayoutRes() {
        return R.layout.include_commit_order;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        holder.setText(R.id.tv_name, "店铺");
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
