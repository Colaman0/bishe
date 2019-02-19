package com.kyle.takeaway.activity;

import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
    }
}
