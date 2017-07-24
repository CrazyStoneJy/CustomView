package com.crazystone.me.customview.practice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by crazy_stone on 17-7-13.
 */

public class GroupView1 extends ViewGroup {
    public GroupView1(Context context) {
        super(context);
    }

    public GroupView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
