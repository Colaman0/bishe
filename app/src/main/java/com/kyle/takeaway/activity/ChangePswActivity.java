package com.kyle.takeaway.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.util.UserHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/18
 *     desc   :
 * </pre>
 */
public class ChangePswActivity extends BaseActivity {
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.btn_commit)
    AppCompatButton btnCommit;
    @BindView(R.id.tv_reset_psw)
    TextView tvResetPsw;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_change_psw;
    }

    @Override
    protected void initView() {


    }


    @OnClick({R.id.btn_commit, R.id.tv_reset_psw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                commit();
                break;
            case R.id.tv_reset_psw:
                goToAcitivty(ResetPswActivity.class);
                break;
        }
    }

    private void commit() {
        if (edtCode.getText().toString().length() < 1 || edtPhone.getText().toString().length() < 1) {
            ToastUtils.showShort("请完善密码");
        } else {
            RetrofitManager.getInstance().changePsw(edtPhone.getText().toString(), edtCode.getText().toString());
        }
    }
}
