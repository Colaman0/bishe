package com.kyle.takeaway.item;

import android.content.Intent;

import com.kyle.takeaway.R;
import com.kyle.takeaway.activity.EditProdcutActivity;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.entity.ProductEntity;

import io.reactivex.functions.Consumer;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/21
 *     desc   :
 * </pre>
 */
public class ItemMerchantProductViewModel extends RecyclerViewModel {

    private final ProductEntity mProductEntity;

    public ItemMerchantProductViewModel(ProductEntity productEntity) {
        mProductEntity = productEntity;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_merchant_product;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        holder.setText(R.id.tv_name, mProductEntity.getFood_name());
        holder.setText(R.id.tv_detail, mProductEntity.getDescription());
        holder.setText(R.id.tv_price, String.valueOf(mProductEntity.getPrice()));
        holder.loadImage(R.id.iv_headicon, mProductEntity.getCover_url());
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }

    @Override
    public void onItemClick() {
        super.onItemClick();
        Intent intent = new Intent(getContext(), EditProdcutActivity.class);
        intent.putExtra("isEdit", true);
        intent.putExtra("product", mProductEntity);
        getContext().startActivity(intent);
    }
}
