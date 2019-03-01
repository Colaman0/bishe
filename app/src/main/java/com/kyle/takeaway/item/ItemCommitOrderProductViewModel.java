package com.kyle.takeaway.item;

import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/19
 *     desc   :
 * </pre>
 */
public class ItemCommitOrderProductViewModel extends RecyclerViewModel {
    private final String mName;
    public int num;
    @BindView(R.id.tv_account)
    TextView tvAccount;

    public ItemCommitOrderProductViewModel(String name, int num) {
        this.num = num;
        mName = name;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_commit_order_product;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        ButterKnife.bind(this, holder.getConvertView());
        tvAccount.setText(String.valueOf(num));
        holder.setText(R.id.tv_name, mName);
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }

}
