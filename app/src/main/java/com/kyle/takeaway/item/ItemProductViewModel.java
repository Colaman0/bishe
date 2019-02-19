package com.kyle.takeaway.item;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.utils.Strings;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/19
 *     desc   :
 * </pre>
 */
public class ItemProductViewModel extends RecyclerViewModel {
    public String price;
    public String name;

    public ItemProductViewModel(String price, String name) {
        this.price = price;
        this.name = name;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_product;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        holder.setText(R.id.tv_name, name);
        holder.setText(R.id.tv_price, Strings.toPrice(price));
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }
}
