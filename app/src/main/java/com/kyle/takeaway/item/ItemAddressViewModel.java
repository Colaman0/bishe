package com.kyle.takeaway.item;

import android.widget.CheckBox;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by kyle on 2019/2/19
 * Function :
 */
public class ItemAddressViewModel extends RecyclerViewModel {

    @BindView(R.id.checkbox)
    CheckBox checkbox;
    @BindView(R.id.tv_address)
    TextView tvAddress;

    private boolean isSelect = false;

    @Override
    public int getLayoutRes() {
        return R.layout.item_address;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        ButterKnife.bind(this, holder.getConvertView());
        holder.setText(R.id.tv_address, "地址");
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }

    @OnClick(R.id.checkbox)
    public void onViewClicked() {
        checkbox.setChecked(isSelect = !isSelect);
    }

    public boolean getIsSelect() {
        return isSelect;
    }
}
