package com.kyle.takeaway.item;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.activity.ShopDetailActivity;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.CartItemEntity;
import com.kyle.takeaway.entity.CommitOrderParam;
import com.kyle.takeaway.entity.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private final CartItemEntity mCartItemEntity;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
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

    public ItemCartViewModel(CartItemEntity cartItemEntity, Action action) {
        mCartItemEntity = cartItemEntity;
        mAction = action;
        List<CartItemEntity.FoodListBean> foodList = mCartItemEntity.getFoodList();
        for (int i = 0; i < foodList.size(); i++) {
            CartItemEntity.FoodListBean foodListBean = foodList.get(i);
            datas.add(new ItemCartProductViewModel(foodListBean.getFoodId(), foodListBean.getNum(), foodListBean.getPrice(), foodListBean.getFoodName()).setAction(mAction).setDeleteAction(mDeleteAction));
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_cart;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        ButterKnife.bind(this, holder.getConvertView());
        holder.setText(R.id.tv_name, mCartItemEntity.getStoreName());
        RecyclerView recyclerView = holder.getView(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(holder.getContext()));
        adapter = new FeaturesAdapter(holder.getContext());
        recyclerView.setAdapter(adapter);
        adapter.addAll(datas);
        ((CheckBox) holder.getView(R.id.checkbox)).setChecked(select);
        holder.getView(R.id.checkbox).setOnClickListener(v -> {
            select = ((CheckBox) holder.getView(R.id.checkbox)).isChecked();
            try {
                mAction.run();
            } catch (Exception e) {
                e.printStackTrace();
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
        return account == 0 ? 0 : account + mCartItemEntity.getFoodList().get(0).getShippingFee();
    }

    public ItemCartViewModel setDeleteAction(Consumer<ItemCartViewModel> consumer) {
        mConsumer = consumer;
        return this;
    }

    public boolean isItemSelect() {
        return checkbox.isChecked();
    }

    public List<CommitOrderParam> getParams() {
        List<CommitOrderParam> params = new ArrayList<>();
        for (int i = 0; i < mCartItemEntity.getFoodList().size(); i++) {
            params.add(new CommitOrderParam(mCartItemEntity.getFoodList().get(i).getCarFoodId(),
                    mCartItemEntity.getFoodList().get(i).getFoodId(),
                    mCartItemEntity.getFoodList().get(i).getNum()));
        }
        return params;
    }

    @Override
    public void onItemClick() {
        super.onItemClick();
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        intent.putExtra(Constants.DATA, mCartItemEntity.getStoreId());
        getContext().startActivity(intent);
    }
}
