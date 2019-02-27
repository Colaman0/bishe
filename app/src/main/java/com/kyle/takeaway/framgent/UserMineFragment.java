package com.kyle.takeaway.framgent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.common.base.Optional;
import com.kyle.takeaway.R;
import com.kyle.takeaway.RetrofitManager;
import com.kyle.takeaway.activity.AddressActivity;
import com.kyle.takeaway.activity.CommentListActivity;
import com.kyle.takeaway.activity.UserInfoActivity;
import com.kyle.takeaway.entity.UserInfoEntity;
import com.kyle.takeaway.util.UserHelper;
import com.kyle.takeaway.utils.GlideImageLoader;
import com.kyle.takeaway.utils.PhotoPickerUtil;
import com.zhihu.matisse.MimeType;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;
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

    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        RetrofitManager.getInstance().getUserInfo(String.valueOf(UserHelper.getId()))
                .doOnNext(new Consumer<Optional<UserInfoEntity>>() {
                    @Override
                    public void accept(Optional<UserInfoEntity> userInfoEntityOptional) throws Exception {
                        if (userInfoEntityOptional.get() != null) {
                            UserHelper.setUserInfoEntity(userInfoEntityOptional.get());
                            GlideImageLoader.getInstance().loadImage(getContext(), userInfoEntityOptional.get().getAvatar(), ivHead);
                        }
                    }
                })
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
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
                getContext().startActivity(new Intent(getActivity(), AddressActivity.class));
                break;
            case R.id.tv_comment:
                getContext().startActivity(new Intent(getActivity(), CommentListActivity.class));
                break;
        }
    }

    public void setPhoto(String photo) {
        RetrofitManager.getInstance().uploadPic(photo)
                .flatMap((Function<String, ObservableSource<?>>) s -> RetrofitManager.getInstance().editUserInfo(s))
                .doOnNext(o -> ToastUtils.showShort("修改成功"))
                .subscribe(Functions.emptyConsumer(), com.kyle.takeaway.base.Functions.throwables());
        GlideImageLoader.getInstance().loadImage(getContext(), photo, ivHead);
    }


}
