package com.crazystone.me.customview.practice_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class PracticeRoundRectView extends CustomView {

    RectF rectF;

    public PracticeRoundRectView(Context context) {
        super(context);
    }

    public PracticeRoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeRoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        rectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rectF.set(100, 100, 200, 400);
        canvas.drawRoundRect(rectF, 50, 50, mPaint);
    }
}
