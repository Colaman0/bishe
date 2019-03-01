package com.kyle.takeaway.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.entity.Constants;
import com.kyle.takeaway.item.ShopItemViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.internal.functions.Functions;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/3/1
 *     desc   :
 * </pre>
 */
public class SearchActivity extends BaseActivity {
    @BindView(R.id.edt_content)
    EditText edtContent;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    FeaturesAdapter mFeaturesAdapter;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mFeaturesAdapter = new FeaturesAdapter(getContext())
                .bindRecyclerView(recyclerview)
                .addItemClickListener((position, itemView) -> gotoDetail(position));;
        recyclerview.setAdapter(mFeaturesAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void gotoDetail(int position) {
        Intent intent = new Intent(getContext(), ShopDetailActivity.class);
        intent.putExtra(Constants.DATA, ((ShopItemViewModel) mFeaturesAdapter.getDatas().get(position)).getId());
        getContext().startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_search)
    public void onViewClicked() {
        if (edtContent.getText().toString().length() > 0) {
            getData();
        } else {
            ToastUtils.showShort("搜索内容不能为空");
        }
    }

    private void getData() {
        mFeaturesAdapter.clear();
        RetrofitManager.getInstance().getStores(0, edtContent.getText().toString().trim())
                .doOnNext(storeEntities -> {
                    for (int i = 0; i < storeEntities.size(); i++) {
                        mFeaturesAdapter.add(new ShopItemViewModel(storeEntities.get(i)));
                    }
                    mFeaturesAdapter.notifyDataSetChanged();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }
}
