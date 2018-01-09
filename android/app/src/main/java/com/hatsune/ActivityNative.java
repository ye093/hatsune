package com.hatsune;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hatsune.adapter.MainViewPagerAdapter;

public class ActivityNative extends AppCompatActivity {
    private LinearLayout tabContainer;
    private ViewPager viewPager;
    int normalColor = Color.parseColor("#99A0AA");
    int selectColor = Color.parseColor("#00A8E1");

    private static final int[] NORMAL_ICONS = new int[]{
            R.drawable.icon_new_msg,
            R.drawable.icon_gonggao,
            R.drawable.icon_manager
    };

    private static final int[] SELECTED_ICONS = new int[]{
            R.drawable.icon_new_msg_selected,
            R.drawable.icon_gonggao_selected,
            R.drawable.icon_manager_selected
    };

    private static final CharSequence[] TITLES = new CharSequence[]{
            "热点资讯",
            "开奖公告",
            "彩票新闻"
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        viewPager = (ViewPager) findViewById(R.id.splashView);
        tabContainer = (LinearLayout) findViewById(R.id.bottomContainer);
        initTabs();
        MainViewPagerAdapter mViewPager = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mViewPager);
        viewPager.setOffscreenPageLimit(3);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                resetTabSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initTabs() {
        if (tabContainer.getChildCount() > 0) tabContainer.removeAllViews();
        for (int i = 0; i < TITLES.length; i++) {
            addTab(i == 0, i);
        }
    }

    private void addTab(boolean selected, final int position) {
        View view = getLayoutInflater().inflate(R.layout.view_tab, null);
        view.setTag(position);
        if (selected)
            tabContainer.setTag(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.tabImage);
        TextView textView = (TextView) view.findViewById(R.id.tabText);
        textView.setText(TITLES[position]);
        if (selected) {
            imageView.setImageResource(SELECTED_ICONS[position]);
            textView.setTextColor(selectColor);
        } else {
            imageView.setImageResource(NORMAL_ICONS[position]);
            textView.setTextColor(normalColor);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f);

        tabContainer.addView(view, position, params);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer p = (Integer) v.getTag();
                if (p != null) {
                    viewPager.setCurrentItem(p);
                    resetTabSelected(p);
                }
            }
        });
    }

    private void resetTabSelected(int position) {
        Integer oldPosition = (Integer) tabContainer.getTag();
        if (oldPosition != null) {
            View view = tabContainer.getChildAt(oldPosition);
            ImageView imageView = (ImageView) view.findViewById(R.id.tabImage);
            TextView textView = (TextView) view.findViewById(R.id.tabText);
            imageView.setImageResource(NORMAL_ICONS[oldPosition]);
            textView.setTextColor(normalColor);
        }
        View view = tabContainer.getChildAt(position);
        tabContainer.setTag(position);
        ImageView imageView = (ImageView) view.findViewById(R.id.tabImage);
        TextView textView = (TextView) view.findViewById(R.id.tabText);
        imageView.setImageResource(SELECTED_ICONS[position]);
        textView.setTextColor(selectColor);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("确定要退出吗？")
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("取消", null).create().show();
    }
}