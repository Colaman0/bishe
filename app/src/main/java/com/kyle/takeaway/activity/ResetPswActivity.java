package com.kyle.takeaway.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private int countdownTime = 60;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_reset_psw;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.btn_send_code, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_code:
                countDown();
                btnSendCode.setClickable(false);
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
                            btnSendCode.setClickable(true);
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    private void commit() {
        finish();
    }
}
