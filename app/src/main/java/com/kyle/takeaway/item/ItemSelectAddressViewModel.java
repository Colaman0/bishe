package com.kyle.takeaway.item;

import android.widget.CheckBox;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.AddressEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * Create by kyle on 2019/2/19
 * Function :
 */
public class ItemSelectAddressViewModel extends RecyclerViewModel {

    private final AddressEntity mAddressEntity;
    private final Consumer<Integer> mConsumer;

    @BindView(R.id.tv_address)
    TextView tvAddress;

    private boolean isSelect = false;

    public ItemSelectAddressViewModel(AddressEntity addressEntity, Consumer<Integer> consumer) {
        mConsumer = consumer;
        mAddressEntity = addressEntity;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_select_address;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        ButterKnife.bind(this, holder.getConvertView());
        holder.setText(R.id.tv_address, mAddressEntity.getContent());
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }

    @OnClick(R.id.root_view)
    public void onViewClicked() {
        try {
            mConsumer.accept(mAddressEntity.getAddress_id());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public int getId() {
        return mAddressEntity.getAddress_id();
    }
}
