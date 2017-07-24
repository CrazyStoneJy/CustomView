package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-20.
 */

public class StrokeCapView extends CustomView {
    public StrokeCapView(Context context) {
        super(context);
    }

    public StrokeCapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeCapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);

        canvas.translate(0, 50);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100, 0, 300, 0, mPaint);

        canvas.translate(0, 100);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100, 0, 300, 0, mPaint);

        canvas.translate(0, 100);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100, 0, 300, 0, mPaint);
    }
}
