package com.kyle.takeaway.item;

import android.content.Intent;

import com.kyle.takeaway.R;
import com.kyle.takeaway.activity.EditProdcutActivity;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/21
 *     desc   :
 * </pre>
 */
public class ItemMerchantProductViewModel extends RecyclerViewModel {
    @Override
    public int getLayoutRes() {
        return R.layout.item_merchant_product;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        holder.setText(R.id.tv_name, "菜品");
        holder.setText(R.id.tv_detail, "好吃");
        holder.setText(R.id.tv_price, "25");
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }

    @Override
    public void onItemClick() {
        super.onItemClick();
        Intent intent = new Intent(getContext(), EditProdcutActivity.class);
        getContext().startActivity(intent);
    }
}
