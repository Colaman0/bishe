package com.kyle.takeaway.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.entity.ProductEntity;
import com.kyle.takeaway.utils.GlideImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/3/7
 *     desc   :
 * </pre>
 */
public class ProductDetailActivity extends BaseActivity {
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    ProductEntity mProductEntity;


    @Override
    protected int initLayoutRes() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void initView() {
        mProductEntity = (ProductEntity) getIntent().getSerializableExtra(Constants.DATA);
        GlideImageLoader.getInstance().loadImage(getContext(), mProductEntity.getCoverUrl(), ivIcon);
        tvName.setText("菜品名 ： " + mProductEntity.getFoodName());
        tvDesc.setText("菜品详情 ： " + mProductEntity.getDescription());
        tvPrice.setText("价格 ： " + String.valueOf(mProductEntity.getPrice()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
