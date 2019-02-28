package com.kyle.takeaway.framgent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.activity.ChangePswActivity;
import com.kyle.takeaway.activity.MerchantInfoActivity;
import com.kyle.takeaway.activity.ResetPswActivity;
import com.kyle.takeaway.activity.SellHistoryActivity;
import com.kyle.takeaway.entity.StoreInfoEntity;
import com.kyle.takeaway.util.UserHelper;
import com.kyle.takeaway.utils.GlideImageLoader;
import com.kyle.takeaway.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/21
 *     desc   :
 * </pre>
 */
public class MerchantMineFragment extends SupportFragment {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_change)
    TextView tvChange;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.title_bar)
    TitleBar mTitleBar;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_merchant_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        mTitleBar.setBackIconVisible(false);

        RetrofitManager.getInstance().getStoreInfo()
                .doOnNext(storeInfoEntity -> UserHelper.setmStoreInfo(storeInfoEntity))
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_name, R.id.tv_change, R.id.tv_reset, R.id.tv_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                getContext().startActivity(new Intent(getActivity(), MerchantInfoActivity.class));
                break;
            case R.id.tv_change:
                getContext().startActivity(new Intent(getActivity(), ChangePswActivity.class));
                break;
            case R.id.tv_reset:
                getContext().startActivity(new Intent(getActivity(), ResetPswActivity.class));
                break;
            case R.id.tv_list:
                getContext().startActivity(new Intent(getActivity(), SellHistoryActivity.class));
                break;
        }
    }

}
