package com.crazystone.me.customview.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crazystone.me.customview.entity.BaseFragmentEntity;

import java.util.List;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class BasePagerAdapter extends FragmentPagerAdapter {
    List<BaseFragmentEntity> list;

    public BasePagerAdapter(FragmentManager fm, List<BaseFragmentEntity> list) {
        super(fm);
        this.list = list;
    }

    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (list == null) return null;
        Fragment fragment = BaseFragment.newInstance(list.get(position).getLayoutRes());
        return fragment;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (list == null) return "";
        return list.get(position).getTitle();
    }
}
