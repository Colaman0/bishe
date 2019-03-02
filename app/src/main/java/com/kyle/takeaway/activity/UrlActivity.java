package com.kyle.takeaway.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.entity.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/3/2
 *     desc   :
 * </pre>
 */
public class UrlActivity extends BaseActivity {
    @BindView(R.id.edt_url)
    EditText edtUrl;
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_url;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        RetrofitManager.getInstance(edtUrl.getText().toString());
        goToAcitivty(RegisterActivity.class);
        finish();
    }
}
