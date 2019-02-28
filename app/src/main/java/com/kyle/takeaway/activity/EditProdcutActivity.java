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
import com.kyle.takeaway.base.bus.RxBus;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.entity.ProductEntity;
import com.kyle.takeaway.util.DialogUtil;
import com.kyle.takeaway.util.LoadingUtil;
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
public class EditProdcutActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.iv_headicon)
    ImageView ivHeadicon;
    @BindView(R.id.edt_shop_name)
    EditText edtShopName;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.btn_save)
    AppCompatButton btnSave;
    @BindView(R.id.edt_desc)
    EditText edtDesc;

    private String photo;
    private boolean isEdit;
    private int mId;
    private ProductEntity mProductEntity;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_edit_product;
    }

    @Override
    protected void initView() {
        isEdit = getIntent().getBooleanExtra("isEdit", false);
        mId = getIntent().getIntExtra(Constants.DATA, 0);
        mProductEntity = (ProductEntity) getIntent().getSerializableExtra("product");
        if (mProductEntity != null) {
            mId = mProductEntity.getFood_id();
            photo = mProductEntity.getCover_url();
            edtPhone.setText(String.valueOf(mProductEntity.getPrice()));
            edtShopName.setText(mProductEntity.getFood_name());
            edtDesc.setText(mProductEntity.getDescription());
        }

        if (isEdit) {
            titleBar.addViewToRight(TitleBar.getTextView(getContext(), "删除", v -> {
                delete();
            }));
        }

        GlideImageLoader.getInstance().loadImage(getContext(), photo, ivHeadicon);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_headicon, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_headicon:
                PhotoPickerUtil.pickPhoto(getActivity(), 1, MimeType.ofAll(), true, 100);
                break;
            case R.id.btn_save:
                if (isEdit) {
                    save();
                } else {
                    add();
                }
                break;
        }
    }

    private void delete() {
        DialogUtil.getSureTipsDialog(getContext(), "确认删除该菜品?", (dialog, which) -> RetrofitManager.getInstance().deleteProduct(mId)
                .doOnNext(o -> {
                    RxBus.getDefault().send(true, "refresh");
                    ToastUtils.showShort("删除成功");
                    finish();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables())).show();
    }

    private void save() {
        RetrofitManager.getInstance().editProduct(mProductEntity.getFood_id(), photo, edtDesc.getText().toString(), edtPhone.getText().toString(), edtShopName.getText().toString())
                .doOnNext(o -> {
                    ToastUtils.showShort("编辑成功");
                    RxBus.getDefault().send(true, "refresh");
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }

    private void add() {
        RetrofitManager.getInstance().addProduct(mId, photo, edtDesc.getText().toString(), edtPhone.getText().toString(), edtShopName.getText().toString())
                .doOnNext(o -> {
                    ToastUtils.showShort("添加成功");
                    RxBus.getDefault().send(true, "refresh");
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            photo = Matisse.obtainPathResult(data).get(0);
            GlideImageLoader.getInstance().loadImage(getContext(), photo, ivHeadicon);
            LoadingUtil.showMaterLoading(getContext(), "上传中");
            RetrofitManager.getInstance().uploadPic(photo)
                    .doOnNext(s -> {
                        ToastUtils.showShort("上传完成");
                        LoadingUtil.hideMaterLoading();
                        photo = s;
                    })
                    .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
        }
    }
}
