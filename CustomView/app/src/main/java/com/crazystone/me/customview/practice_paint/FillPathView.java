package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-20.
 */

public class FillPathView extends CustomView {

    Path path, destPath1, destPath2, destPath3;

    public FillPathView(Context context) {
        super(context);
    }

    public FillPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FillPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        destPath1 = new Path();
        destPath2 = new Path();
        path = new Path();
        path.moveTo(100, 300);
        path.rLineTo(100, -250);
        path.rLineTo(100, 200);
        path.rLineTo(100, -100);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, mPaint);

        canvas.translate(0, 300);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3);
        mPaint.getFillPath(path, destPath1);
        canvas.drawPath(destPath1, mPaint);

        canvas.translate(0, 300);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);
        mPaint.getFillPath(path, destPath2);
        canvas.drawPath(destPath2, mPaint);


    }
}
