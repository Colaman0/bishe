package com.kyle.takeaway.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/19
 *     desc   :
 * </pre>
 */
public class ItemCartProductViewModel extends RecyclerViewModel {
    public int num;
    public int account;
    public int singlePrice;
    @BindView(R.id.tv_add)
    AppCompatButton tvAdd;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_less)
    AppCompatButton tvLess;
    private Action mAction;
    private Consumer<ItemCartProductViewModel> mDeleteAction;

    public ItemCartProductViewModel(int num, int singlePrice) {
        this.num = num;
        this.singlePrice = singlePrice;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_cart_product;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        ButterKnife.bind(this, holder.getConvertView());
        tvAccount.setText(String.valueOf(num));
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }

    public int getNum() {
        return num;
    }

    @OnClick({R.id.tv_add, R.id.tv_less})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_add:
                num++;
                break;
            case R.id.tv_less:
                if (num > 1) {
                    num--;
                } else if (num == 1) {
                    DialogUtil.getSureTipsDialog(getContext(), "确认删除该菜品？", new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            num = 0;
                            try {
                                mAction.run();
                                tvAccount.setText(String.valueOf(num));
                                mDeleteAction.accept(ItemCartProductViewModel.this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).show();
                }
                break;
        }
        try {
            mAction.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tvAccount.setText(String.valueOf(num));
    }

    public int getAccount() {
        return num * singlePrice;
    }

    public ItemCartProductViewModel setAction(Action action) {
        mAction = action;
        return this;
    }

    public ItemCartProductViewModel setDeleteAction(Consumer<ItemCartProductViewModel> action) {
        mDeleteAction = action;
        return this;
    }
}
