package com.crazystone.me.customview.practice_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class PracticeLineView extends CustomView {
    public PracticeLineView(Context context) {
        super(context);
    }

    public PracticeLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeWidth(20);
        canvas.drawLine(100, 100, 400, 400, mPaint);
    }
}
