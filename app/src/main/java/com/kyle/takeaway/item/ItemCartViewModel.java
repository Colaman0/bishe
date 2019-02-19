package com.kyle.takeaway.item;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.kyle.takeaway.R;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/19
 *     desc   :
 * </pre>
 */
public class ItemCartViewModel extends RecyclerViewModel {
    private final Action mAction;
    private Consumer<ItemCartProductViewModel> mDeleteAction = new Consumer<ItemCartProductViewModel>() {
        @Override
        public void accept(ItemCartProductViewModel itemCartProductViewModel) throws Exception {
            datas.remove(itemCartProductViewModel);
            adapter.remove(itemCartProductViewModel);
            adapter.notifyDataSetChanged();
            if (adapter.getItemCount() == 0) {
                mConsumer.accept(ItemCartViewModel.this);
            }
        }
    };
    private List<RecyclerViewModel> datas = new ArrayList<>();
    private boolean select = false;
    private FeaturesAdapter adapter;
    private Consumer<ItemCartViewModel> mConsumer;

    public ItemCartViewModel(Action action) {
        mAction = action;
        datas.add(new ItemCartProductViewModel(1, 10).setAction(mAction).setDeleteAction(mDeleteAction));
        datas.add(new ItemCartProductViewModel(2, 10).setAction(mAction).setDeleteAction(mDeleteAction));
        datas.add(new ItemCartProductViewModel(3, 10).setAction(mAction).setDeleteAction(mDeleteAction));
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_cart;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        holder.setText(R.id.tv_name, "店铺");
        RecyclerView recyclerView = holder.getView(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(holder.getContext()));
        adapter = new FeaturesAdapter(holder.getContext());
        recyclerView.setAdapter(adapter);
        adapter.addAll(datas);
        ((CheckBox) holder.getView(R.id.checkbox)).setChecked(select);
        holder.getView(R.id.checkbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = ((CheckBox) holder.getView(R.id.checkbox)).isChecked();
                try {
                    mAction.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean isSameData(Object o) {
        return false;
    }

    public int getAccount() {
        if (!select) {
            return 0;
        }
        int account = 0;
        for (int i = 0; i < datas.size(); i++) {
            account += ((ItemCartProductViewModel) datas.get(i)).getAccount();
        }
        return account;
    }

    public ItemCartViewModel setDeleteAction(Consumer<ItemCartViewModel> consumer) {
        mConsumer = consumer;
        return this;
    }
}
