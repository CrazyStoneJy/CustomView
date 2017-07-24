package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-20.
 */

public class PathEffectView extends CustomView {

    Path path;
    PathEffect pathEffect;
    PathEffect[] pathEffects;
    Path circlePath;

    public PathEffectView(Context context) {
        super(context);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        path = new Path();
        path.moveTo(100, 100);
        path.rLineTo(50, 100);
        path.rLineTo(50, -150);
        path.rLineTo(100, 50);
        path.rLineTo(100, -40);
        path.rLineTo(150, 80);
        circlePath = new Path();
//        circlePath.addCircle(10, 10, 10, Path.Direction.CW);
        circlePath.moveTo(0, 0);
        circlePath.lineTo(10, 10);
        circlePath.lineTo(20, 10);
        circlePath.close();

        pathEffects = new PathEffect[6];

        pathEffects[0] = new CornerPathEffect(40);
        pathEffects[1] = new DashPathEffect(new float[]{20, 10, 20, 5}, 0);
        pathEffects[2] = new DiscretePathEffect(10, 2);
        pathEffects[3] = new PathDashPathEffect(circlePath, 30, 5, PathDashPathEffect.Style.MORPH);
//        pathEffects[3] = new PathEffect();
        pathEffects[4] = new ComposePathEffect(pathEffects[0], pathEffects[1]);
        pathEffects[5] = new SumPathEffect(pathEffects[0], pathEffects[1]);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);

        for (int i = 0; i < 6; i++) {
            canvas.save();
            canvas.translate(0, 300 * i);
            mPaint.setPathEffect(pathEffects[i]);
            canvas.drawCircle(30, 30, 30, mPaint);
            canvas.drawPath(path, mPaint);
            mPaint.setPathEffect(null);
            canvas.restore();
        }
    }
}
