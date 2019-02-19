package com.kyle.takeaway.base;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * <pre>
 *     author : kyle
 *     time   : 2019/2/14
 *     desc   : 通用viewpager's adapter
 * </pre>
 */
public class CommonViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private List<String> mTitles = new ArrayList<>();

    public CommonViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        mFragments.addAll(fragments);
    }

    /**
     * 设置标题
     *
     * @param titles
     * @return
     */
    public CommonViewPagerAdapter setTitles(List<String> titles) {
        mTitles.addAll(titles);
        return this;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
