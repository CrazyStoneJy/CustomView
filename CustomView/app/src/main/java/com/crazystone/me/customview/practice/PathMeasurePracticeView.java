package com.crazystone.me.customview.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.crazystone.me.customview.utils.Coordinates;
import com.crazystone.me.customview.utils.Windows;

/**
 * Created by crazy_stone on 17-7-12.
 */

public class PathMeasurePracticeView extends View {

    Paint mPaint;
    Path path, destPath;
    int centerX, centerY;
    PathMeasure pm;
    float[] posValue = new float[2], tanValue = new float[2];

    public PathMeasurePracticeView(Context context) {
        super(context);
        init();
    }

    public PathMeasurePracticeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PathMeasurePracticeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        pm = new PathMeasure();
        destPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = Windows.getScreenWidth(getContext()) >> 1;
        centerY = Windows.getScreenHeight(getContext()) >> 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX, centerY);
        Coordinates.drawCoordination(canvas);
        path.addCircle(0, 0, 200, Path.Direction.CW);
        pm.setPath(path, false);
        Log.d(PathMeasurePracticeView.class.getSimpleName(), "path length:" + pm.getLength() + ",is closed:" + pm.isClosed());
        pm.getSegment(0, 1500, destPath, true);
        pm.getPosTan(200, posValue, tanValue);
        Log.d(PathMeasurePracticeView.class.getSimpleName(), "pos[]:" + posValue[0] + "," + posValue[1] + ";tan[]:" + tanValue[0] + "," + tanValue[1]);
        double degree = Math.atan2(tanValue[1], tanValue[0]) * 180.0 / Math.PI;
        Log.d(PathMeasurePracticeView.class.getSimpleName(), "degree:" + degree);
        canvas.drawPath(destPath, mPaint);
    }
}
