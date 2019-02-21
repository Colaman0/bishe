package com.kyle.takeaway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.utils.GlideImageLoader;
import com.kyle.takeaway.view.TitleBar;
import com.zhihu.matisse.Matisse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/21
 *     desc   :
 * </pre>
 */
public class MerchantInfoActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.edt_shop_name)
    EditText edtShopName;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.iv_headicon)
    ImageView ivHeadicon;
    @BindView(R.id.btn_save)
    AppCompatButton btnSave;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_merchant_info;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.edt_shop_name, R.id.edt_phone, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edt_shop_name:
                break;
            case R.id.edt_phone:
                break;
            case R.id.btn_save:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String photo = Matisse.obtainPathResult(data).get(0);
            GlideImageLoader.getInstance().loadImage(getContext(), photo, ivHeadicon);
        }
    }
}
