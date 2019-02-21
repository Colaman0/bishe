package com.kyle.takeaway.framgent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kyle.takeaway.R;
import com.kyle.takeaway.activity.EditProdcutActivity;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.item.ItemMerchantProductViewModel;
import com.kyle.takeaway.view.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/21
 *     desc   :
 * </pre>
 */
public class MerChantProductListFragment extends SupportFragment {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private FeaturesAdapter mFeaturesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_merchant_product_list, container, false);
        ButterKnife.bind(this, view);
        titleBar.addViewToRight(TitleBar.getTextView(getContext(), "添加", v -> getContext().startActivity(new Intent(getContext(), EditProdcutActivity.class))));
        titleBar.setBackIconVisible(false);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeaturesAdapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview);
        recyclerview.setAdapter(mFeaturesAdapter);
        mFeaturesAdapter.add(new ItemMerchantProductViewModel());
        mFeaturesAdapter.add(new ItemMerchantProductViewModel());
        mFeaturesAdapter.add(new ItemMerchantProductViewModel());
        mFeaturesAdapter.notifyDataSetChanged();
        return view;
    }

}
