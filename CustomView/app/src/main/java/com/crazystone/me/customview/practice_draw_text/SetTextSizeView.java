package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class SetTextSizeView extends CustomView {
    public SetTextSizeView(Context context) {
        super(context);
    }

    public SetTextSizeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SetTextSizeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < 5; i++) {
            canvas.save();
            canvas.translate(0, 100 * i);
            mPaint.setTextSize(10 + 10 * i);
            canvas.drawText("hello world", 20, 50, mPaint);
            canvas.restore();
        }
    }
}
