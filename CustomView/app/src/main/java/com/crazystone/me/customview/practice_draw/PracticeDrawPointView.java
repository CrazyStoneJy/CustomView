package com.crazystone.me.customview.practice_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class PracticeDrawPointView extends CustomView {
    public PracticeDrawPointView(Context context) {
        super(context);
    }

    public PracticeDrawPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }


    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(50);
        canvas.drawPoint(100, 100, mPaint);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(200, 200, mPaint);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(300, 300, mPaint);
    }

}
