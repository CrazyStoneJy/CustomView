package com.crazystone.me.customview.practice_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class PracticeDrawPathView extends CustomView {

    Path path;
    Path linePath;

    public PracticeDrawPathView(Context context) {
        super(context);
    }

    public PracticeDrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        path = new Path();
        linePath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        path.addCircle(50, 50, 50, Path.Direction.CW);
//        path.addCircle(150, 50, 50, Path.Direction.CW);
//        float x = (float) (50 - Math.sqrt(50) / 50F);
//        float y = (float) (50 + Math.sqrt(50) / 50F);
//        float x2 =
//
//        linePath.moveTo();
//        path.addPath();
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);

        canvas.drawPath(path, mPaint);

    }
}
