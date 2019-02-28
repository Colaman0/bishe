package com.kyle.takeaway.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.base.bus.RxBus;
import com.kyle.takeaway.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/19
 *     desc   :
 * </pre>
 */
public class ItemCartProductViewModel extends RecyclerViewModel {
    private final String mName;
    private final int mId;
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

    public ItemCartProductViewModel(int id, int num, int singlePrice, String name) {
        mId = id;
        this.num = num;
        this.singlePrice = singlePrice;
        mName = name;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_cart_product;
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
                if (num >= 1) {
                    num--;
                }
                break;
        }
        RetrofitManager.getInstance().editCart(mId, num)
                .doOnNext(o -> RxBus.getDefault().send(true, "cart"))
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
        try {
            mAction.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
