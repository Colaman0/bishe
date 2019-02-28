package com.kyle.takeaway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.util.UserHelper;
import com.kyle.takeaway.utils.GlideImageLoader;
import com.kyle.takeaway.utils.PhotoPickerUtil;
import com.kyle.takeaway.view.TitleBar;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

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
    @BindView(R.id.edt_fee)
    EditText edtFee;
    @BindView(R.id.iv_headicon)
    ImageView ivHeadicon;
    @BindView(R.id.btn_save)
    AppCompatButton btnSave;

    private String avatar;
    private String fee;
    private String name;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_merchant_info;
    }

    @Override
    protected void initView() {
        avatar = UserHelper.getmStoreInfo().getAvatar();
        fee = String.valueOf(UserHelper.getmStoreInfo().getShipping_fee());
        name = UserHelper.getmStoreInfo().getStore_name();

        GlideImageLoader.getInstance().loadImage(getContext(), avatar, ivHeadicon);
        edtFee.setText(fee);
        edtShopName.setText(name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.edt_shop_name, R.id.edt_fee, R.id.btn_save, R.id.iv_headicon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_headicon:
                PhotoPickerUtil.pickPhoto(getActivity(), 1, MimeType.ofAll(), true, 100);
                break;
            case R.id.btn_save:
                RetrofitManager.getInstance().editStoreInfo(avatar, edtFee.getText().toString(), edtShopName.getText().toString())
                        .doOnNext(o -> {
                            UserHelper.getmStoreInfo().setAvatar(avatar);
                            UserHelper.getmStoreInfo().setShipping_fee(Double.parseDouble(edtFee.getText().toString()));
                            UserHelper.getmStoreInfo().setStore_name(edtShopName.getText().toString());
                            ToastUtils.showShort("修改成功");
                            finish();
                        })
                        .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String photo = Matisse.obtainPathResult(data).get(0);
            RetrofitManager.getInstance().uploadPic(photo)
                    .doOnNext(s -> avatar = s)
                    .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
            GlideImageLoader.getInstance().loadImage(getContext(), photo, ivHeadicon);
        }
    }
}
