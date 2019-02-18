package com.kyle.takeaway.activity;

import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by kyle on 2019/2/17
 * Function :
 */
public class SetUserInfoActivity extends BaseActivity {


    @BindView(R.id.iv_headicon)
    ImageView ivHeadicon;
    @BindView(R.id.edt_psw)
    EditText edtPsw;
    @BindView(R.id.btn_commit)
    AppCompatButton btnCommit;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_set_userinfo;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.iv_headicon, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_headicon:
                break;
            case R.id.btn_commit:
                commit();
                break;
        }
    }

    private void commit() {
        goToAcitivty(LoginActivity.class);
        finish();
    }
}
