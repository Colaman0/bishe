package com.kyle.takeaway.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ConvertUtils;
import com.kyle.takeaway.R;


/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/18
 *     desc   : 标题栏基类
 * </pre>
 */
public abstract class BaseTitleBar extends FrameLayout {
    protected View leftView;
    protected View righttView;
    protected View centerView;
    private Context mContext;

    public BaseTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttrs(context, attrs, defStyleAttr);
        initView();
    }

    public void setSize(float width, float heigth) {
        int lpWidth = 0;
        int lpHeight = 0;
        if (width == ViewGroup.LayoutParams.MATCH_PARENT || width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            lpWidth = (int) width;
        } else {
            lpWidth = ConvertUtils.dp2px(width);
        }

        if (heigth == ViewGroup.LayoutParams.MATCH_PARENT || heigth == ViewGroup.LayoutParams.WRAP_CONTENT) {
            lpHeight = (int) heigth;
        } else {
            lpHeight = ConvertUtils.dp2px(heigth);
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(lpWidth, lpHeight);
        setLayoutParams(params);
    }

    /**
     * 设置高度，宽度默认为match_parent
     */
    public void setHeight(float dp) {
        setSize(ViewGroup.LayoutParams.MATCH_PARENT, dp);
    }

    private void initView() {
        setBackgroundColor(getContext().getResources().getColor(R.color.white));
        leftView = initLeftView();
        righttView = initRightView();
        centerView = initCenterView();
        addToParent();
    }

    void addToParent() {
        if (leftView != null) {
            LayoutParams layoutParams = (LayoutParams) leftView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            layoutParams.gravity = Gravity.LEFT;
            leftView.setLayoutParams(layoutParams);
            addView(leftView);
        }

        if (centerView != null) {
            LayoutParams layoutParams = (LayoutParams) centerView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            layoutParams.gravity = Gravity.CENTER;
            centerView.setLayoutParams(layoutParams);
            addView(centerView);
        }

        if (righttView != null) {
            LayoutParams layoutParams = (LayoutParams) righttView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            layoutParams.gravity = Gravity.RIGHT;
            righttView.setLayoutParams(layoutParams);
            addView(righttView);
        }
    }

    public abstract View initCenterView();

    public abstract View initLeftView();

    public abstract View initRightView();

    public abstract void initAttrs(Context context, AttributeSet attrs, int defStyleAttr);


}
