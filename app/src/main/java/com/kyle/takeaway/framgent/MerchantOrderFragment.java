package com.kyle.takeaway.framgent;

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

import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.bus.RxBus;
import com.kyle.takeaway.entity.OrderItemEntity;
import com.kyle.takeaway.item.MerchantOrderItemViewModel;
import com.kyle.takeaway.item.OrderItemViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

/**
 * Create by kyle on 2019/2/18
 * Function :
 */
public class MerchantOrderFragment extends Fragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    Unbinder unbinder;
    private FeaturesAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = (View) LayoutInflater.from(getContext()).inflate(R.layout.include_recyclerview, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new FeaturesAdapter(getActivity())
                .bindRecyclerView(recyclerview)
                .addItemClickListener((position, itemView) -> Log.d("cola", "position = " + position));
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(mAdapter);
        getData();
        RxBus.getDefault().receiveEvent(Boolean.class,"order_refresh")
                .doOnNext(aBoolean -> getData())
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }

    private void getData() {
        mAdapter.clear();
        RetrofitManager.getInstance().getStoreOrders()
                .doOnNext(orderItemEntities -> {
                    for (int i = 0; i < orderItemEntities.size(); i++) {
                        mAdapter.add(new MerchantOrderItemViewModel(orderItemEntities.get(i)));
                    }
                    mAdapter.notifyDataSetChanged();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }
}
