package com.kyle.takeaway.framgent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyle.takeaway.R;
import com.kyle.takeaway.activity.UserInfoActivity;
import com.kyle.takeaway.utils.GlideImageLoader;
import com.kyle.takeaway.utils.PhotoPickerUtil;
import com.zhihu.matisse.MimeType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/18
 *     desc   :
 * </pre>
 */
public class UserMineFragment extends SupportFragment {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_comment)
    TextView tvComment;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.include_mine_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.iv_head, R.id.tv_info, R.id.tv_address, R.id.tv_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                PhotoPickerUtil.pickPhoto(getActivity(), 1, MimeType.ofAll(), true, 100);
                break;
            case R.id.tv_info:
                Intent intent = new Intent(getActivity(), UserInfoActivity.class);
                intent.putExtra("name", "张三");
                getContext().startActivity(intent);
                break;
            case R.id.tv_address:
                break;
            case R.id.tv_comment:
                break;
        }
    }

    public void setPhoto(String photo) {
        GlideImageLoader.getInstance().loadImage(getContext(), photo, ivHead);
    }


}
