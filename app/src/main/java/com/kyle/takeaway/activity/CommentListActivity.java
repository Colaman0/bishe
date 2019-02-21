package com.kyle.takeaway.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.adapter.FeaturesAdapter;
import com.kyle.takeaway.base.BaseActivity;
import com.kyle.takeaway.item.ItemCommentViewModel;

import butterknife.BindView;

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
        for (int i = 0; i < 10; i++) {
            mAdapter.add(new ItemCommentViewModel());
        }
    }
}
