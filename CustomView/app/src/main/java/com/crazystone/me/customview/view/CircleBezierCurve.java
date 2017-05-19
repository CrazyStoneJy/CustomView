package com.crazystone.me.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by crazy_stone on 17-5-17.
 */

public class CircleBezierCurve extends View {

    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
    private static final int DEFAULT_ARROW_WIDTH = 40, DEFAULT_RADIUS = 300, DEFAULT_DURATION = 1000, PER = 10;
    Paint paint;
    private int currentTime = 0;
    private int centerX, centerY;

    //圆形4个数据点
    private Point[] dataPoints;
    //圆形8个控制点
    private Point[] controlPoints;

    private Path mPath;

    public CircleBezierCurve(Context context) {
        super(context);
        init();
    }

    public CircleBezierCurve(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleBezierCurve(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        mPath = new Path();
        initData();

    }

    private void initData() {
        dataPoints = new Point[4];
        controlPoints = new Point[8];

        dataPoints[0] = new Point(0, -DEFAULT_RADIUS);
        dataPoints[1] = new Point(DEFAULT_RADIUS, 0);
        dataPoints[2] = new Point(0, DEFAULT_RADIUS);
        dataPoints[3] = new Point(-DEFAULT_RADIUS, 0);

        //control point
        int controlSize = (int) (C * DEFAULT_RADIUS);

        controlPoints[0] = new Point(dataPoints[0].x + controlSize, dataPoints[0].y);
        controlPoints[1] = new Point(dataPoints[1].x, dataPoints[1].y - controlSize);
        controlPoints[2] = new Point(dataPoints[1].x, dataPoints[1].y + controlSize);
        controlPoints[3] = new Point(dataPoints[2].x + controlSize, dataPoints[2].y);
        controlPoints[4] = new Point(dataPoints[2].x - controlSize, dataPoints[2].y);
        controlPoints[5] = new Point(dataPoints[3].x, dataPoints[3].y + controlSize);
        controlPoints[6] = new Point(dataPoints[3].x, dataPoints[3].y - controlSize);
        controlPoints[7] = new Point(dataPoints[0].x - controlSize, dataPoints[0].y);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        canvas.translate(centerX, centerY);
        paint.setColor(Color.BLACK);
        drawCoordinate(canvas);
        drawControlLine(canvas);
        paint.setColor(Color.RED);
        drawBezierCircle(canvas);

        if (currentTime <= DEFAULT_DURATION) {
            dataPoints[0].y += 1.0;
//            controlPoints[0].y -= 1.2;
//            controlPoints[7].y -= 1.2;

//            controlPoints[3].y -= 0.1;
//            controlPoints[4].y -= 0.1;
//
            controlPoints[2].x -= 0.2;
            controlPoints[5].x += 0.2;

            currentTime += PER;
            postInvalidateDelayed(PER);
        }

    }

    private void drawControlLine(Canvas canvas) {

        canvas.drawLine(dataPoints[0].x, dataPoints[0].y, controlPoints[0].x, controlPoints[0].y, paint);
        canvas.drawLine(controlPoints[1].x, controlPoints[1].y, dataPoints[1].x, dataPoints[1].y, paint);
        canvas.drawLine(dataPoints[1].x, dataPoints[1].y, controlPoints[2].x, controlPoints[2].y, paint);
        canvas.drawLine(controlPoints[3].x, controlPoints[3].y, dataPoints[2].x, dataPoints[2].y, paint);
        canvas.drawLine(dataPoints[2].x, dataPoints[2].y, controlPoints[4].x, controlPoints[4].y, paint);
        canvas.drawLine(controlPoints[5].x, controlPoints[5].y, dataPoints[3].x, dataPoints[3].y, paint);
        canvas.drawLine(dataPoints[3].x, dataPoints[3].y, controlPoints[6].x, controlPoints[6].y, paint);
        canvas.drawLine(controlPoints[7].x, controlPoints[7].y, dataPoints[0].x, dataPoints[0].y, paint);

    }

    private void drawBezierCircle(Canvas canvas) {

        mPath.moveTo(dataPoints[0].x, dataPoints[0].y);
        mPath.cubicTo(controlPoints[0].x, controlPoints[0].y, controlPoints[1].x, controlPoints[1].y, dataPoints[1].x, dataPoints[1].y);
        mPath.cubicTo(controlPoints[2].x, controlPoints[2].y, controlPoints[3].x, controlPoints[3].y, dataPoints[2].x, dataPoints[2].y);
        mPath.cubicTo(controlPoints[4].x, controlPoints[4].y, controlPoints[5].x, controlPoints[5].y, dataPoints[3].x, dataPoints[3].y);
        mPath.cubicTo(controlPoints[6].x, controlPoints[6].y, controlPoints[7].x, controlPoints[7].y, dataPoints[0].x, dataPoints[0].y);
        canvas.drawPath(mPath, paint);

    }

    private void drawCoordinate(Canvas canvas) {
        //x axis
        canvas.drawLine(-centerX, 0, centerX, 0, paint);
        canvas.drawLine((float) (centerX - DEFAULT_ARROW_WIDTH * Math.sqrt(3) / 2), -DEFAULT_ARROW_WIDTH / 2, centerX, 0, paint);
        canvas.drawLine((float) (centerX - DEFAULT_ARROW_WIDTH * Math.sqrt(3) / 2), DEFAULT_ARROW_WIDTH / 2, centerX, 0, paint);

        //y axis
        canvas.drawLine(0, -centerY, 0, centerY, paint);
        canvas.drawLine(-DEFAULT_ARROW_WIDTH / 2, (float) (DEFAULT_ARROW_WIDTH * Math.sqrt(3) / 2 - centerY), 0, -centerY, paint);
        canvas.drawLine(0, -centerY, DEFAULT_ARROW_WIDTH / 2, (float) (DEFAULT_ARROW_WIDTH * Math.sqrt(3) / 2 - centerY), paint);
    }


}
