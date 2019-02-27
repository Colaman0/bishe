package com.kyle.takeaway.item;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.StoreEntity;

/**
 * Create by kyle on 2019/2/18
 * Function :
 */
public class ShopItemViewModel extends RecyclerViewModel {

    private final StoreEntity mStoreEntity;

    public ShopItemViewModel(StoreEntity storeEntity) {
        mStoreEntity = storeEntity;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_shop_list;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
    }

    @Override
    public void bindView(BaseViewHolder holder) {
        super.bindView(holder);
        holder.setText(R.id.tv_name, mStoreEntity.getStore_name());
        holder.setText(R.id.tv_fee, "配送费:￥" + mStoreEntity.getShipping_fee());
        holder.loadImage(R.id.iv_icon, mStoreEntity.getAvatar());

    }

    public int getId() {
        return mStoreEntity.getStore_id();
    }

    @Override
    public boolean isSameData(Object o) {
        return true;
    }
}
