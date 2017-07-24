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

public class StrokeJoinView extends CustomView {

    Path path;

    public StrokeJoinView(Context context) {
        super(context);
    }

    public StrokeJoinView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeJoinView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);

        path.moveTo(0, 0);
        path.lineTo(200, 0);
        path.lineTo(100, 40);

        canvas.translate(0, 50);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path, mPaint);

        canvas.translate(0, 200);
        mPaint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path, mPaint);

        canvas.translate(0, 200);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, mPaint);

    }
}
