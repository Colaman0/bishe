package com.kyle.takeaway.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.common.base.Optional;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.AddressEntity;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.item.ItemAddressViewModel;
import com.kyle.takeaway.util.DialogUtil;
import com.kyle.takeaway.view.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

/**
 * Create by kyle on 2019/2/19
 * Function :
 */
public class AddressActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    private boolean isSelect = false;

    private FeaturesAdapter mFeaturesAdapter;
    private List<Integer> ids = new ArrayList<>();
    private List<RecyclerViewModel> mDeleteViewModels = new ArrayList<>();


    @Override
    protected int initLayoutRes() {
        return R.layout.activity_address;
    }

    @Override
    protected void initView() {
        isSelect = getIntent().getBooleanExtra(Constants.DATA, false);
        titleBar.addViewToRight(TitleBar.getTextView(getContext(), "添加", v -> add()));
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeaturesAdapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview);
        recyclerview.setAdapter(mFeaturesAdapter);
        getData();
    }

    private void getData() {
        RetrofitManager.getInstance().getAddressList()
                .doOnNext(addressEntityOptional -> {
                    mFeaturesAdapter.clear();
                    for (int i = 0; i < addressEntityOptional.size(); i++) {
                        mFeaturesAdapter.add(new ItemAddressViewModel(addressEntityOptional.get(i)));
                    }
                    mFeaturesAdapter.notifyDataSetChanged();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }

    @OnClick(R.id.tv_commit)
    public void onViewClicked() {
        DialogUtil.getSureTipsDialog(getContext(), "确认删除?", (dialog, which) -> delete()).show();
    }

    private void delete() {
        ids.clear();
        mDeleteViewModels.clear();
        for (int i = 0; i < mFeaturesAdapter.getDatas().size(); i++) {
            ItemAddressViewModel viewmodel = (ItemAddressViewModel) mFeaturesAdapter.getDatas().get(i);
            if (viewmodel.getIsSelect()) {
                ids.add(viewmodel.getId());
                mDeleteViewModels.add(viewmodel);
            }
        }
        String id = "";

        for (int i = 0; i < ids.size(); i++) {
            id = id + ids.get(i) + (i == ids.size() - 1 ? "" : ",");
        }

        RetrofitManager.getInstance().deleteAddress(id)
                .doOnNext(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        ToastUtils.showShort("删除成功");
                        mFeaturesAdapter.remove(mDeleteViewModels);
                        mFeaturesAdapter.notifyDataSetChanged();
                    }
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }

    private void add() {
        Intent intent = getDefaultIntent(AddAddressActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            getData();
        }
    }
}
