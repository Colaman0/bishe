package com.kyle.takeaway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseActivity;

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
public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_change)
    TextView tvChange;
    @BindView(R.id.tv_reset)
    TextView tvReset;


    @Override
    protected int initLayoutRes() {
        return R.layout.include_user_info;
    }

    @Override
    protected void initView() {
        tvName.setText("用户名 ：" + getIntent().getStringExtra("name"));
    }

    @OnClick({R.id.tv_change, R.id.tv_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_change:
                goToAcitivty(ChangePswActivity.class);
                break;
            case R.id.tv_reset:
                goToAcitivty(ResetPswActivity.class);
                break;
        }
    }
}
