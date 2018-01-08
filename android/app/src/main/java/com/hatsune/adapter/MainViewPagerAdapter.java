package com.hatsune.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hatsune.fragment.AboutUsFragment;
import com.hatsune.fragment.HomeFragment;
import com.hatsune.fragment.OpenResultFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private static final String[] TITLES = new String[] {
            "彩票资讯",
            "开奖公告",
            "彩票新闻"
    };

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        if (position == 1) {
            fragment = OpenResultFragment.newInstance(TITLES[position]);
        } else if(position == 0) {
            fragment = HomeFragment.newInstance(TITLES[position]);
        } else {
            fragment = AboutUsFragment.newInstance(TITLES[position]);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
