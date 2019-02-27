package com.kyle.takeaway.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.base.Functions;
import com.kyle.takeaway.util.UserHelper;
import com.kyle.takeaway.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by kyle on 2019/2/17
 * Function :
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_code)
    EditText edtCode;

    int countdownTime = 60;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_reset_psw)
    TextView tvResetPsw;
    @BindView(R.id.title_bar)
    TitleBar titleBar;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }


    @OnClick({R.id.btn_login, R.id.tv_reset_psw,R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (checkbox.isChecked()) {
                    storeLogin();
                } else {
                    userLogin();
                }
                break;
            case R.id.tv_reset_psw:
                goToAcitivty(ResetPswActivity.class);
                break;
            case R.id.tv_register:
                goToAcitivty(RegisterActivity.class);
                break;
        }
    }

    /**
     * 开始登录操作
     */
    private void userLogin() {
        RetrofitManager.getInstance().userLogin(edtPhone.getText().toString(), edtCode.getText().toString())
                .doOnNext(s -> {
                    UserHelper.setUserInfo(true,s.get().getUserId());
                    if (checkbox.isChecked()) {
                        goToAcitivty(MerchantHomeActivity.class);
                    } else {
                        goToAcitivty(UserHomeActivity.class);
                    }
                })
                .subscribe(Functions.empty(), Functions.throwables());
    }

    private void storeLogin() {
        RetrofitManager.getInstance().storeLogin(edtPhone.getText().toString(), edtCode.getText().toString())
                .doOnNext(s -> {
                    UserHelper.setUserInfo(false,s.get().getUserId());
                    if (checkbox.isChecked()) {
                        goToAcitivty(MerchantHomeActivity.class);
                    } else {
                        goToAcitivty(UserHomeActivity.class);
                    }
                })
                .subscribe(Functions.empty(), Functions.throwables());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        titleBar.setBackIconVisible(false);
    }

    @OnClick(R.id.tv_reset_psw)
    public void onViewClicked() {
        goToAcitivty(ResetPswActivity.class        );
    }
}
