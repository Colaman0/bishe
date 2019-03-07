package com.kyle.takeaway.activity;

import com.blankj.utilcode.util.ScreenUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/18
 *     desc   :
 * </pre>
 */
public class FirstActivity extends BaseActivity {
    @Override
    protected int initLayoutRes() {
        return R.layout.activity_first;
    }

    @Override
    protected void initView() {
        ScreenUtils.setFullScreen(this);
        Observable.interval(2, TimeUnit.SECONDS)
                .take(1)
                .doOnNext(aLong -> {
                    goToAcitivty(UrlActivity.class);
                    finish();
                })
                .subscribe(Functions.emptyConsumer());
    }
}
