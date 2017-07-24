package com.crazystone.me.customview.entity;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class BaseFragmentEntity {

    private String title;
    private int layoutRes;

    public String getTitle() {
        return title;
    }

    public BaseFragmentEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getLayoutRes() {
        return layoutRes;
    }

    public BaseFragmentEntity setLayoutRes(int layoutRes) {
        this.layoutRes = layoutRes;
        return this;
    }
}
