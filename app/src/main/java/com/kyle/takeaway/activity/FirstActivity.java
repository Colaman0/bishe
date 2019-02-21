package com.kyle.takeaway.activity;

import com.blankj.utilcode.util.ScreenUtils;
import com.kyle.takeaway.R;
import com.kyle.takeaway.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

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
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                goToAcitivty(UserHomeActivity.class);
            }
        };
        new Timer().schedule(timerTask, 2*1000 );
    }
}
