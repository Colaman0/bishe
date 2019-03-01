package com.kyle.takeaway.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.entity.CommentEntity;
import com.kyle.takeaway.item.ItemCommentViewModel;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/19
 *     desc   :
 * </pre>
 */
public class CommentListActivity extends BaseActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private FeaturesAdapter mAdapter;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView() {
        mAdapter = new FeaturesAdapter(getContext());
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerview.setAdapter(mAdapter);
        RetrofitManager.getInstance().getComments()
                .doOnNext(commentEntities -> {
                    for (int i = 0; i < commentEntities.size(); i++) {
                        mAdapter.add(new ItemCommentViewModel(commentEntities.get(i)));
                    }
                    mAdapter.notifyDataSetChanged();
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
    }
}
