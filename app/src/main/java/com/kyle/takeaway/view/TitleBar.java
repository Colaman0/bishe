package com.kyle.takeaway.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.kyle.takeaway.R;


/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/18
 *     desc   : 通用标题栏
 * </pre>
 */
public class TitleBar extends BaseTitleBar {
    private final int mBackIconRes = R.mipmap.ic_back;
    private String mTitle;
    @ColorInt
    private int mTitleColor;
    private float mTitleSize;
    private LinearLayout mLeftLayout;
    private LinearLayout mRightLayout;
    private LinearLayout mCenterLayout;


    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addView();
    }

    @Override
    public View initCenterView() {
        return mCenterLayout = getLayout();
    }

    @Override
    public View initLeftView() {
        return mLeftLayout = getLayout();
    }

    @Override
    public View initRightView() {
        return mRightLayout = getLayout();
    }

    @Override
    public void initAttrs(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, 0);
        mTitle = array.getString(R.styleable.TitleBar_title);
        mTitleColor = array.getColor(R.styleable.TitleBar_titleColor, context.getResources().getColor(R.color.black));
        mTitleSize = array.getDimension(R.styleable.TitleBar_titleSize, 16f);
        array.recycle();
        setHeight(50);
    }

    public LinearLayout getLayout() {
        LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return layout;
    }

    private void addView() {
        // 添加一个返回按钮
        getLeftLayout().addView(getBackIcon());
        // 添加标题
        getCenterLayout().addView(getTitleView());
    }

    private TextView getTitleView() {
        TextView title = new TextView(getContext());
        title.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        title.setText(mTitle);
        title.setTextColor(mTitleColor);
        title.setTextSize(mTitleSize);
        title.setGravity(Gravity.CENTER);
        return title;
    }

    private ImageView getBackIcon() {
        int padding = ConvertUtils.dp2px(16);
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(mBackIconRes);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setOnClickListener(v -> {
            if (getContext() instanceof Activity) {
                ((Activity) getContext()).finish();
            }
        });
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ConvertUtils.dp2px(50f), ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setBackground(getRippleDrawable(getContext()));
        return imageView;
    }

    public LinearLayout getLeftLayout() {
        return mLeftLayout;
    }

    public LinearLayout getRightLayout() {
        return mRightLayout;
    }

    public LinearLayout getCenterLayout() {
        return mCenterLayout;
    }

    public TitleBar addViewToCenter(View view) {
        getCenterLayout().addView(view);
        return this;
    }

    public TitleBar addViewToLeft(View view) {
        getLeftLayout().addView(view);
        return this;
    }

    public TitleBar addViewToRight(View view) {
        getRightLayout().addView(view);
        return this;
    }

    public static ImageView getIconView(Context context, @DrawableRes int iconRes, OnClickListener listener) {
        int padding = ConvertUtils.dp2px(16);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ConvertUtils.dp2px(50f), ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setImageResource(iconRes);
        imageView.setOnClickListener(listener);
        imageView.setBackground(getRippleDrawable(context));
        return imageView;
    }

    public static TextView getTextView(Context context, String content, OnClickListener listener) {
        return getTextView(context, content, R.color.black, 16, listener);
    }

    public static TextView getTextView(Context context, String content, @ColorRes int titleColor, int textsize, OnClickListener listener) {
        int padding = ConvertUtils.dp2px(16);
        TextView text = new TextView(context);
        text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        text.setText(content);
        text.setPadding(padding,padding,padding,padding);
        text.setGravity(Gravity.CENTER);
        text.setTextSize(TypedValue.COMPLEX_UNIT_SP, textsize);
        text.setTextColor(context.getResources().getColor(titleColor));
        text.setOnClickListener(listener);
        text.setBackground(getRippleDrawable(context));
        return text;
    }

    private static Drawable getRippleDrawable(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
        int[] attribute = new int[]{android.R.attr.selectableItemBackground};
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(typedValue.resourceId, attribute);
        Drawable drawable = typedArray.getDrawable(0);
        typedArray.recycle();
        return drawable.getConstantState().newDrawable();
    }
}

