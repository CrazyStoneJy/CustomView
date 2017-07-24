package com.crazystone.me.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Views;
import com.crazystone.me.customview.utils.Windows;

import java.util.Random;

/**
 * 六边形的绘制
 * <p>
 * Created by crazy_stone on 17-7-10.
 */

public class HexagonView extends View {

    final static int HEXAGON_RADIUS = 100;
    final static int[] COLORS = {R.color.green, R.color.blue, R.color.red, R.color.orange, R.color.purple, R.color.yellow};
    //注意：Math sin/cos的参数是弧度不是角度
    float radians = (float) ((Math.PI / 180F) * 60);
    Random random;
    private Paint mPaint;
    private int centerX, centerY;
    private Path mPath;

    public HexagonView(Context context) {
        super(context);
        init();
    }

    public HexagonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HexagonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = Windows.getScreenWidth(getContext()) / 2;
        centerY = Windows.getScreenHeight(getContext()) / 2;
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setColor(Views.getColorInt(getContext(), R.color.blue));
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX, centerY);

        drawHexagon2(canvas, -1);
//        drawHexagon(canvas);
        drawAroundHexagon(canvas);
    }

    private void drawAroundHexagon(Canvas canvas) {
        //相邻的两个六边形的距离
        float centerRadius = (float) (HEXAGON_RADIUS * Math.sin(radians) * 2);
        for (int i = 0; i < 6; i++) {
            canvas.save();
            float translateX = (float) (centerRadius * Math.sin(radians * i));
            float translateY = (float) (centerRadius * Math.cos(radians * i));
            canvas.translate(translateX, translateY);
            drawHexagon2(canvas, i);
            canvas.restore();
        }
    }

    private void drawHexagon2(Canvas canvas, int pos) {

        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                mPath.moveTo(HEXAGON_RADIUS, 0);
            } else {
                float x = (float) (HEXAGON_RADIUS * Math.cos(radians * i));
                float y = (float) (HEXAGON_RADIUS * Math.sin(radians * i));
                mPath.lineTo(x, y);
            }
        }
        mPath.close();
        if (pos != -1) {
            mPaint.setColor(Views.getColorInt(getContext(), COLORS[pos]));
        }
        canvas.drawPath(mPath, mPaint);
    }

    private void drawHexagon(Canvas canvas) {
        mPath.moveTo(HEXAGON_RADIUS, 0);
        mPath.lineTo(HEXAGON_RADIUS / 2, (float) (Math.sin(radians) * HEXAGON_RADIUS));
        mPath.lineTo(-HEXAGON_RADIUS / 2, (float) (Math.sin(radians) * HEXAGON_RADIUS));
        mPath.lineTo(-HEXAGON_RADIUS, 0);
        mPath.lineTo(-HEXAGON_RADIUS / 2, (float) (-Math.sin(radians) * HEXAGON_RADIUS));
        mPath.lineTo(HEXAGON_RADIUS / 2, (float) (-Math.sin(radians) * HEXAGON_RADIUS));
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }
}
