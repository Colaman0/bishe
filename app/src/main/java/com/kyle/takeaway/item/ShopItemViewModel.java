package com.kyle.takeaway.item;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;

/**
 * Create by kyle on 2019/2/18
 * Function :
 */
public class ShopItemViewModel extends RecyclerViewModel {

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

    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }
}
