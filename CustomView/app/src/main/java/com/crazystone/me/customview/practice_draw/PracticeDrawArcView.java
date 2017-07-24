package com.crazystone.me.customview.practice_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class PracticeDrawArcView extends CustomView {

    RectF rectF1, rectF2, rectF3;
    Paint rectPaint;

    public PracticeDrawArcView(Context context) {
        super(context);
    }

    public PracticeDrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        rectF1 = new RectF();
        rectF2 = new RectF();
        rectF3 = new RectF();
        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectPaint.setColor(Color.RED);
        rectPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rectF1.set(100, 100, 200, 200);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF1, 180, 90, false, mPaint);
        canvas.drawRect(rectF1, rectPaint);
        rectF2.set(200, 200, 350, 400);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF2, 0, 90, true, mPaint);
        canvas.drawRect(rectF2, rectPaint);
        rectF3.set(400, 400, 600, 550);
        canvas.drawArc(rectF3, 0, 180, false, mPaint);
        canvas.drawRect(rectF3, rectPaint);
    }
}
