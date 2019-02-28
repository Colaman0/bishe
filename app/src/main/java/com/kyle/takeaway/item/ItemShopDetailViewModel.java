package com.kyle.takeaway.item;

import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.Functions;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.base.bus.RxBus;
import com.kyle.takeaway.entity.ProductEntity;

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

    @OnClick({R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                num++;
                RetrofitManager.getInstance().addToCart(mProductEntity.getFood_id())
                        .doOnNext(o -> {
                            ToastUtils.showShort("加入购物车成功");
                            RxBus.getDefault().send(true, "cart");
                        })
                        .subscribe(Functions.empty(), Functions.throwables());
                break;
        }
    }
}
