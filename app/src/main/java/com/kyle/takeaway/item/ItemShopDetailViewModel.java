package com.kyle.takeaway.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.ProductEntity;
import com.kyle.takeaway.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/20
 *     desc   :
 * </pre>
 */
public class ItemShopDetailViewModel extends RecyclerViewModel {
    private final ProductEntity mProductEntity;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_detail)
    TextView tvDetail;
    @BindView(R.id.tv_add)
    AppCompatButton tvAdd;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_less)
    AppCompatButton tvLess;

    private int num;

    public ItemShopDetailViewModel(ProductEntity productEntity) {
        mProductEntity = productEntity;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_shop_detail;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        ButterKnife.bind(this, holder.getConvertView());
        holder.loadImage(R.id.iv_pic, mProductEntity.getCover_url());
        holder.setText(R.id.tv_name, mProductEntity.getFood_name());
        holder.setText(R.id.tv_detail, mProductEntity.getDescription());
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }

    @OnClick({R.id.tv_add, R.id.tv_less})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                num++;
                break;
            case R.id.tv_less:
                if (num >= 1) {
                    num--;
                }
                tvAccount.setText(String.valueOf(num));
                break;
        }

        tvAccount.setText(String.valueOf(num));
    }
}
