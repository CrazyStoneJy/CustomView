package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class SetTextScaleXView extends CustomView {
    public SetTextScaleXView(Context context) {
        super(context);
    }

    public SetTextScaleXView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SetTextScaleXView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setTextSize(50);
        for (int i = 0; i < 4; i++) {
            canvas.translate(0, 200);
            mPaint.setTextScaleX(0.5F * i);
            canvas.drawText("HelloWorld", 10, 50, mPaint);
        }


    }
}
