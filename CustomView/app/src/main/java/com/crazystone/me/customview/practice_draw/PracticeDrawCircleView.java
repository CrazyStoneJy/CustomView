package com.crazystone.me.customview.practice_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class PracticeDrawCircleView extends CustomView {

    Paint mPaint;

    public PracticeDrawCircleView(Context context) {
        super(context);
    }

    public PracticeDrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(300, 300, 100, mPaint);
        mPaint.setColor(Color.CYAN);
        canvas.drawCircle(300, 600, 100, mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(1);
        canvas.drawCircle(600, 300, 100, mPaint);
        mPaint.setStrokeWidth(40);
        canvas.drawCircle(600, 600, 100, mPaint);
    }
}
