package com.kyle.takeaway.base;

import android.arch.lifecycle.GenericLifecycleObserver;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

/**
 * Create by kyle on 2019/1/1
 * Function :
 */
public abstract class BaseViewModel implements GenericLifecycleObserver {
    private Lifecycle mLifecycle;

    public void bindParent(BaseViewModel viewModel) {
        if (viewModel != null) {
            bindLife(viewModel.getLifecycle());
        }
    }

    public void bindLife(Lifecycle lifecycle) {
        if (lifecycle != null) {
            mLifecycle = lifecycle;
            lifecycle.addObserver(this);
        }
    }

    @Override
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        switch (event) {
            case ON_DESTROY:
                onLifeDestory();
                break;
            case ON_START:
                onLifeStart();
                break;
            case ON_CREATE:
                onLifeCreate();
                break;
            case ON_RESUME:
                onLifeResume();
                break;
            default:
                onLifeStateChange(source, event);
                break;
        }
    }

    protected void onLifeDestory() {

    }

    protected void onLifeCreate() {

    }

    protected void onLifeStart() {

    }

    protected void onLifeResume() {

    }

    protected void onLifeStateChange(LifecycleOwner source, Lifecycle.Event event) {

    }

    public Lifecycle getLifecycle() {
        return mLifecycle;
    }
}
