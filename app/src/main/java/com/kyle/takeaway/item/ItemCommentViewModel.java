package com.kyle.takeaway.item;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;
import com.kyle.takeaway.entity.CommentEntity;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/20
 *     desc   :
 * </pre>
 */
public class ItemCommentViewModel extends RecyclerViewModel {

    private final CommentEntity mCommentEntity;

    public ItemCommentViewModel(CommentEntity commentEntity) {
        mCommentEntity = commentEntity;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_comment;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        holder.setText(R.id.tv_name, mCommentEntity.getStore_name());
        holder.setText(R.id.tv_content,mCommentEntity.getContent());
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }
}
