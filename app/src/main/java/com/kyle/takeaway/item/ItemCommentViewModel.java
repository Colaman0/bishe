package com.kyle.takeaway.item;

import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseViewHolder;
import com.kyle.takeaway.base.RecyclerViewModel;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/20
 *     desc   :
 * </pre>
 */
public class ItemCommentViewModel extends RecyclerViewModel {
    @Override
    public int getLayoutRes() {
        return R.layout.item_comment;
    }

    @Override
    protected void onBindView(BaseViewHolder holder) {
        holder.setText(R.id.tv_name, "店铺");
        holder.setText(R.id.tv_content, "评论");
    }

    @Override
    public boolean isSameData(Object o) {
        return false;
    }
}
