package com.crazystone.me.customview.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.entity.BaseFragmentEntity;

import java.util.List;

/**
 * Created by crazy_stone on 17-7-20.
 */

public abstract class BaseActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        tabLayout = (TabLayout) findViewById(R.id.base_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.base_view_pager);
        init();
    }

    private void init() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(new BasePagerAdapter(getSupportFragmentManager(), getDatas()));
        tabLayout.setupWithViewPager(viewPager);
    }

    protected abstract List<BaseFragmentEntity> getDatas();
}
