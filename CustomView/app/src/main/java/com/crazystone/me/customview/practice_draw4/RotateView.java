package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class RotateView extends CustomBitmapView {

    public RotateView(Context context) {
        super(context);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.save();
        canvas.rotate(90, 300, 300);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.restore();
    }
}
