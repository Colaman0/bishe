package com.kyle.takeaway.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.base.Functions;
import com.kyle.takeaway.util.UserHelper;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by kyle on 2019/2/17
 * Function :
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.btn_send_code)
    AppCompatButton btnSendCode;
    int countdownTime = 60;
    @BindView(R.id.btn_register)
    AppCompatButton btnLogin;
    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.edt_psw)
    EditText edtPsw;
    @BindView(R.id.edt_nick_name)
    EditText edtNickName;

    private String photo;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    /**
     * 开始倒计时60s
     */
    private void countDown() {
        btnSendCode.setClickable(false);
        btnSendCode.setText(countdownTime + "s");
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        countdownTime--;
                        btnSendCode.setText(countdownTime + "s");
                        if (countdownTime == 0) {
                            timer.cancel();
                            btnSendCode.setText("发送验证码");
                            countdownTime = 60;
                            btnSendCode.setClickable(true);
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    @OnClick({R.id.btn_send_code, R.id.btn_register, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_code:
                if (edtPhone.getText().toString().length() > 0) {
                    RetrofitManager.getInstance().getRegisterCode(edtPhone.getText().toString(), checkbox.isChecked() ? 0 : 1)
                            .doOnNext(o -> countDown())
                            .subscribe(Functions.empty(), Functions.throwables());
                } else {
                    ToastUtils.showShort("电话号码不能为空");
                }
                break;
            case R.id.btn_register:
                register();
                break;
            case R.id.tv_login:
                goToAcitivty(LoginActivity.class);
                finish();
                break;
        }
    }

    /**
     * 开始注册操作
     */
    private void register() {
        if (edtNickName.getText().toString().length() == 0 ||
                edtPhone.getText().toString().length() == 0 ||
                edtPsw.getText().toString().length() == 0 ||
                edtCode.getText().toString().length() == 0) {
            ToastUtils.showShort("请完善信息");
            return;
        }
        RetrofitManager.getInstance().register(edtNickName.getText().toString(),
                edtPhone.getText().toString(),
                edtPsw.getText().toString(),
                edtCode.getText().toString(), checkbox.isChecked() ? 2 : 1)
                .doOnNext(o -> {
                    goToAcitivty(LoginActivity.class);
                    finish();
                })
                .subscribe(Functions.empty(), Functions.throwables());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
