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

public class PracticeDrawOvalView extends CustomView {
    RectF rect;

    public PracticeDrawOvalView(Context context) {
        super(context);
    }

    public PracticeDrawOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        rect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rect.set(100, 100, 500, 300);
        canvas.drawOval(rect, mPaint);
    }
}
