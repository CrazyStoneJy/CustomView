package com.crazystone.me.customview.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.crazystone.me.customview.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by crazy_stone on 17-7-24.
 */

public class SecondActivity extends AppCompatActivity {

    TextView txt;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txt_second_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EventBus.getDefault().register(this);
        txt = (TextView) findViewById(R.id.txt_second);
        tabLayout = (TabLayout) findViewById(R.id.tab_second);
        viewPager = (ViewPager) findViewById(R.id.viewpager_second);
        txt_second_content = (TextView) findViewById(R.id.txt_second_content);
        viewPager.setAdapter(new SecondAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SecondEvent("heheda"));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SecondActivityEvent event) {
        txt_second_content.setText(event.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private class SecondAdapter extends FragmentPagerAdapter {
        public SecondAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return position == 0 ? new FirstFragment() : new SecondFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return position == 0 ? "firstFragment" : "secondFragment";
        }
    }


}
