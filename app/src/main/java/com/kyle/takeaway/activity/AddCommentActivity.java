package com.kyle.takeaway.activity;

import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;

import com.blankj.utilcode.util.ToastUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.base.Functions;
import com.kyle.takeaway.base.bus.RxBus;
import com.kyle.takeaway.entity.Constants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/20
 *     desc   :
 * </pre>
 */
public class AddCommentActivity extends BaseActivity {
    @BindView(R.id.edt_address)
    EditText edtAddress;
    @BindView(R.id.btn_save)
    AppCompatButton btnSave;

    private int mOrderId;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_add_comment;
    }

    @Override
    protected void initView() {
        mOrderId = getIntent().getIntExtra(Constants.DATA, 0);
    }

    @OnClick(R.id.btn_save)
    public void onViewClicked() {
        if (edtAddress.getText().length() > 1) {
            RetrofitManager.getInstance().commitComment(mOrderId, edtAddress.getText().toString())
                    .doOnNext(o -> {
                        RxBus.getDefault().send(true,"order");
                        ToastUtils.showShort("提交成功");
                        setResult(RESULT_OK);
                        finish();
                    })
                    .subscribe(Functions.empty(), Functions.throwables());
        } else {
            ToastUtils.showShort("评论不能为空");
        }
    }
}
