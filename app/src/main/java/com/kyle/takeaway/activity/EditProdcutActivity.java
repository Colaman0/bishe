package com.kyle.takeaway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.framgent.UserMineFragment;
import com.kyle.takeaway.util.DialogUtil;
import com.kyle.takeaway.utils.GlideImageLoader;
import com.kyle.takeaway.utils.PhotoPickerUtil;
import com.kyle.takeaway.view.TitleBar;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.util.HashMap;

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

    private String photo;
    private boolean isEdit;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_edit_product;
    }

    @Override
    protected void initView() {
        titleBar.addViewToRight(TitleBar.getTextView(getContext(), isEdit ? "删除" : "添加", v -> {
            if (isEdit) {
                save();
            } else {
                delete();
            }
        }));

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
                save();
                break;
        }
    }

    private void delete() {
        DialogUtil.getSureTipsDialog(getContext(), "确认删除该菜品?", new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

            }
        }).show();
    }

    private void save() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            photo = Matisse.obtainPathResult(data).get(0);
            GlideImageLoader.getInstance().loadImage(getContext(), photo, ivHeadicon);
        }
    }
}
