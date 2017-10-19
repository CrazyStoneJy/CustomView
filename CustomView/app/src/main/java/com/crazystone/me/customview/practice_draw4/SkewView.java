package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class SkewView extends CustomBitmapView {
    public SkewView(Context context) {
        super(context);
    }

    public SkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.save();
        canvas.skew(0.5F, 0);
        canvas.drawBitmap(bitmap, 300, 300, mPaint);
        canvas.restore();
    }
}
