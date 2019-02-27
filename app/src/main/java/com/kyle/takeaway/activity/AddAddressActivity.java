package com.kyle.takeaway.activity;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;

import com.google.common.base.Optional;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.entity.AddressEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

public class AddAddressActivity extends BaseActivity {

    @BindView(R.id.edt_address)
    EditText edtAddress;
    @BindView(R.id.btn_save)
    AppCompatButton btnSave;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_add_address;
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        RetrofitManager.getInstance().addAddress(edtAddress.getText().toString())
                .doOnNext(listOptional -> {
                    setResult(RESULT_OK);
                    finish();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }
}
