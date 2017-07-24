package com.crazystone.me.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.crazystone.me.customview.utils.Windows;

/**
 * Created by crazy_stone on 17-7-17.
 */

public abstract class CustomView extends View {
    protected Paint mPaint;

    public CustomView(Context context) {
        super(context);
        alreadyInit();
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        alreadyInit();
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        alreadyInit();
        init();
    }

    protected abstract void init();

    private void alreadyInit() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(Windows.getScreenWidth(getContext()) >> 1, Windows.getScreenHeight(getContext()) >> 1);
        canvas.scale(1, -1);
    }
}
