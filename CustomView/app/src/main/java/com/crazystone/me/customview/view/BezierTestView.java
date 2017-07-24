package com.crazystone.me.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by crazy_stone on 17-5-17.
 */

public class BezierTestView extends View {

    private static final int DEFAULT_ARROW_WIDTH = 40;
    Paint blackPaint, redPaint;
    private int width, height;
    private Path mPath;
    private PointF startPoint, endPoint, controlPoint;

    public BezierTestView(Context context) {
        super(context);
        init();
    }

    public BezierTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        startPoint = new PointF(-300, 0);
        endPoint = new PointF(300, 0);
        controlPoint = new PointF(0, -300);
    }

    private void init() {
        blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        blackPaint.setStrokeWidth(3);
        blackPaint.setColor(Color.parseColor("#000000"));
        blackPaint.setStyle(Paint.Style.STROKE);

        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStrokeWidth(3);
        redPaint.setColor(Color.RED);
        redPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(width / 2, height / 2);

        drawCoordinate(canvas);

        drawBezierCurve(canvas);

    }

    private void drawBezierCurve(Canvas canvas) {
        canvas.drawLine(startPoint.x, startPoint.y, controlPoint.x, controlPoint.y, blackPaint);
        canvas.drawLine(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y, blackPaint);
        mPath.moveTo(startPoint.x, startPoint.y);
        mPath.quadTo(controlPoint.x, controlPoint.y, endPoint.x, endPoint.y);
        canvas.drawPath(mPath, redPaint);
    }

    private void drawCoordinate(Canvas canvas) {
        //x axis
        canvas.drawLine(-width / 2, 0, width / 2, 0, blackPaint);
        canvas.drawLine((float) (width / 2 - DEFAULT_ARROW_WIDTH * Math.sqrt(3) / 2), -DEFAULT_ARROW_WIDTH / 2, width / 2, 0, blackPaint);
        canvas.drawLine((float) (width / 2 - DEFAULT_ARROW_WIDTH * Math.sqrt(3) / 2), DEFAULT_ARROW_WIDTH / 2, width / 2, 0, blackPaint);

//        //旋转画布绘制箭头
//        canvas.save();
//        canvas.rotate(-45, width / 2, 0);
//        mPath.moveTo(width / 2 - DEFAULT_ARROW_WIDTH, 0);
//        mPath.lineTo(width / 2, 0);
//        mPath.lineTo(width / 2, -(float) DEFAULT_ARROW_WIDTH);
//        canvas.drawPath(mPath, blackPaint);
//        canvas.restore();

        //y axis
        canvas.drawLine(0, -height / 2, 0, height / 2, blackPaint);
        canvas.drawLine(-DEFAULT_ARROW_WIDTH / 2, (float) (DEFAULT_ARROW_WIDTH * Math.sqrt(3) / 2 - height / 2), 0, -height / 2, blackPaint);
        canvas.drawLine(0, -height / 2, DEFAULT_ARROW_WIDTH / 2, (float) (DEFAULT_ARROW_WIDTH * Math.sqrt(3) / 2 - height / 2), blackPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        controlPoint.x = event.getX() - width / 2;
        controlPoint.y = event.getY() - height / 2;
        //getRawX() getX() 是相对于屏幕而言，与canvas无关，
        Log.d(BezierTestView.class.getSimpleName(), "x:" + event.getX() + ",y:" + event.getY() + ",raw x:" + event.getRawX() + ",raw y:" + event.getRawY());
        mPath.reset();
        invalidate();

        return true;
    }
}
