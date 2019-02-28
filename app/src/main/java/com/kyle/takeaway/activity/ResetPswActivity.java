package com.kyle.takeaway.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.util.UserHelper;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.internal.functions.Functions;

/**
 * Create by kyle on 2019/2/17
 * Function :
 */
public class ResetPswActivity extends BaseActivity {
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.btn_send_code)
    AppCompatButton btnSendCode;
    @BindView(R.id.edt_psw)
    EditText edtPsw;
    @BindView(R.id.btn_commit)
    AppCompatButton btnCommit;
    @BindView(R.id.checkbox)
    CheckBox checkbox;

    private int countdownTime = 60;
    private int accountType;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_reset_psw;
    }

    @Override
    protected void initView() {
        checkbox.setVisibility(UserHelper.isInit() ? View.GONE : View.VISIBLE);
    }

    @SuppressLint("CheckResult")
    @OnClick({R.id.btn_send_code, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_code:
                if (edtPhone.getText().toString().length() < 1) {
                    ToastUtils.showShort("请填写手机号");
                } else {
                    RetrofitManager.getInstance().getResetCode(edtPhone.getText().toString(),
                            UserHelper.isInit() ? UserHelper.getAccountType() : checkbox.isChecked() ? 2 : 1)
                            .doOnNext(o -> countDown())
                            .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
                }
                break;
            case R.id.btn_commit:
                commit();
                break;
        }
    }

    /**
     * 开始倒计时60s
     */
    private void countDown() {
        btnSendCode.setText(countdownTime + "s");
        btnSendCode.setClickable(false);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    countdownTime--;
                    btnSendCode.setText(countdownTime + "s");
                    if (countdownTime == 0) {
                        timer.cancel();
                        btnSendCode.setText("发送验证码");
                        btnSendCode.setClickable(true);
                        countdownTime = 60;
                    }
                });
            }
        }, 0, 1000);
    }

    private void commit() {
        if (edtPhone.getText().toString().length() < 1 || edtPsw.getText().toString().length() < 1 || edtCode.getText().toString().length() < 1) {
            ToastUtils.showShort("请完善信息");
            return;
        }
        RetrofitManager.getInstance().resetPsw(edtPhone.getText().toString(), edtPsw.getText().toString(), edtCode.getText().toString(),
                UserHelper.isInit() ? UserHelper.getAccountType() : checkbox.isChecked() ? 2 : 1)
                .doOnNext(o -> {
                    goToAcitivty(LoginActivity.class);
                    finish();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
