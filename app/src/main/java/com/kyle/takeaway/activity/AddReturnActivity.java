package com.kyle.takeaway.activity;

import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/20
 *     desc   :
 * </pre>
 */
public class AddReturnActivity extends BaseActivity {
    @BindView(R.id.edt_address)
    EditText edt;
    @BindView(R.id.btn_save)
    AppCompatButton btnSave;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_add_return;
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
    }
}
