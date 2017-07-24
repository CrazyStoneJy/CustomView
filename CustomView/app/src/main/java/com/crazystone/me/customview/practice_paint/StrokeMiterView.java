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

public class StrokeMiterView extends CustomView {

    Path path;

    public StrokeMiterView(Context context) {
        super(context);
    }

    public StrokeMiterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeMiterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setStrokeMiter(4);
        canvas.drawPath(path, mPaint);

        canvas.translate(0, 200);
        mPaint.setStrokeMiter(1);
        canvas.drawPath(path, mPaint);

        canvas.translate(0, 200);
        mPaint.setStrokeMiter(10);
        canvas.drawPath(path, mPaint);
    }
}
