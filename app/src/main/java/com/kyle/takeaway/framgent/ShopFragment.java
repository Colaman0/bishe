package com.kyle.takeaway.framgent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.activity.ShopDetailActivity;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.entity.StoreEntity;
import com.kyle.takeaway.item.ShopItemViewModel;
import com.kyle.takeaway.view.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Create by kyle on 2019/2/18
 * Function :
 */
public class ShopFragment extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    @BindView(R.id.tv_sell)
    TextView tvSell;
    @BindView(R.id.ll_sell)
    LinearLayout llSell;
    @BindView(R.id.tv_fee)
    TextView tvFee;
    @BindView(R.id.ll_fee)
    LinearLayout llFee;
    @BindView(R.id.iv_sell)
    ImageView ivSell;
    @BindView(R.id.iv_fee)
    ImageView ivFee;
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    private FeaturesAdapter mAdapter;

    private String sell = "sell";
    private String fee = "fee";

    private String sortType = "";
    private int sort = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) LayoutInflater.from(getContext()).inflate(R.layout.include_shop_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerview = view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleBar
                .setBackIconVisible(false)
                .addViewToRight(TitleBar.getIconView(getContext(), R.mipmap.ic_search, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("搜索");
                    }
                }));
        mAdapter = new FeaturesAdapter(getActivity())
                .bindRecyclerView(recyclerview)
                .addItemClickListener((position, itemView) -> gotoDetail(position));
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(mAdapter);
        getData();
    }

    private void getData() {
        mAdapter.clear();
        RetrofitManager.getInstance().getStores(sort, "")
                .doOnNext(storeEntities -> {
                    for (int i = 0; i < storeEntities.size(); i++) {
                        mAdapter.add(new ShopItemViewModel(storeEntities.get(i)));
                    }
                    mAdapter.notifyDataSetChanged();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }

    private void gotoDetail(int position) {
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        intent.putExtra(Constants.DATA, ((ShopItemViewModel) mAdapter.getDatas().get(position)).getId());
        getContext().startActivity(intent);
    }


    @OnClick({R.id.ll_sell, R.id.ll_fee})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_sell:
                sortType = sell;

                if (sortType.equals(sell)) {
                    llSell.setSelected(!llSell.isSelected());
                    setIconStatus(ivSell, llSell.isSelected());
                    llFee.setSelected(false);
                    resetIcon(ivFee);
                } else {
                    llSell.setSelected(false);
                    resetIcon(ivSell);
                }
                sortType = sell;
                break;
            case R.id.ll_fee:
                sortType = fee;

                if (sortType.equals(fee)) {
                    llFee.setSelected(!llFee.isSelected());
                    setIconStatus(ivFee, ivFee.isSelected());

                    llSell.setSelected(false);
                    resetIcon(ivSell);
                } else {
                    llFee.setSelected(false);
                    resetIcon(ivFee);
                }
                break;
        }
        if (sortType.equalsIgnoreCase(sell)) {
            sort = llSell.isSelected() ? 1 : 2;
        } else {
            sort = llFee.isSelected() ? 3 : 4;
        }
        getData();
    }

    private void setIconStatus(ImageView view, boolean isSelect) {
        view.setImageResource(isSelect ? R.mipmap.ic_sell_price_down : R.mipmap.ic_sell_price_up);
    }

    private void resetIcon(ImageView view) {
        view.setImageResource(R.mipmap.ic_sort_none);
    }
}
