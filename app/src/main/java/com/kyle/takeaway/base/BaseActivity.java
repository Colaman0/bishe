package com.kyle.takeaway.base;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.ViewGroup;


import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * Create by kyle on 2018/9/19
 * Function : baseActivity
 */
public abstract class BaseActivity extends SupportActivity {
    private LifecycleRegistry mLifecycleRegistry;


    private ViewGroup mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutRes());
        initLifeCycle();
        ButterKnife.bind(this, this);
        initView();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initLifeCycle() {
        mLifecycleRegistry = new LifecycleRegistry(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @LayoutRes
    protected abstract int initLayoutRes();

    protected abstract void initView();

    public Context getContext() {
        return this;
    }

    public Activity getActivity() {
        return this;
    }

    public Intent getDefaultIntent(Class activity) {
        return new Intent(this, activity);
    }

    public void goToAcitivty(Class activity) {
        startActivity(getDefaultIntent(activity));
    }

    public void switchLayout(String layoutType) {
//        contentLayout.switchLayout(layoutType);
    }
}
