package com.crazystone.me.customview.view.thumbView;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by crazy_stone on 17-10-14.
 */

public class ThumbUpView extends View implements View.OnClickListener {

    boolean isAgree;

    public ThumbUpView(Context context) {
        super(context);
    }

    public ThumbUpView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ThumbUpView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }

    @Override
    public void onClick(View v) {
        isAgree = !isAgree;
    }
}
