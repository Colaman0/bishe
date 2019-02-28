package com.kyle.takeaway.item;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.SellHistoryEntity;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/28
 *     desc   :
 * </pre>
 */
public class ItemListViewModel extends RecyclerViewModel {

    private final SellHistoryEntity mSellHistoryEntity;

    public ItemListViewModel(SellHistoryEntity sellHistoryEntity) {
        mSellHistoryEntity = sellHistoryEntity;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_list;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        holder.loadImage(R.id.iv_icon, mSellHistoryEntity.getCover_url());
        holder.setText(R.id.tv_sell_account,"总共售出:"+ String.valueOf(mSellHistoryEntity.getNum()));
        holder.setText(R.id.tv_total,"总金额:￥ "+ String.valueOf(mSellHistoryEntity.getTotal_fee()));
        holder.setText(R.id.tv_name, mSellHistoryEntity.getFood_name());
        holder.setText(R.id.tv_desc, mSellHistoryEntity.getDescription());
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }
}
