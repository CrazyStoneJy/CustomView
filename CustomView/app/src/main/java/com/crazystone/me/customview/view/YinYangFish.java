package com.crazystone.me.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.crazystone.me.customview.utils.Windows;

/**
 * Created by crazy_stone on 17-7-12.
 */

public class YinYangFish extends View {

    Paint mPaint;
    int centerX, centerY;
    Path[] paths = new Path[4];
    boolean isDraw = false;

    public YinYangFish(Context context) {
        super(context);
        init();
    }

    public YinYangFish(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public YinYangFish(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        centerX = Windows.getScreenWidth(getContext()) >> 1;
        centerY = Windows.getScreenHeight(getContext()) >> 1;
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        for (int i = 0; i < 4; i++) {
            paths[i] = new Path();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isDraw) {
            canvas.translate(centerX, centerY);
            paths[0].addCircle(0, 0, 200, Path.Direction.CW);
            paths[1].addCircle(0, 100, 100, Path.Direction.CW);
            paths[2].addCircle(0, -100, 100, Path.Direction.CW);
            paths[3].addRect(0, -200, 200, 200, Path.Direction.CW);
            paths[0].op(paths[3], Path.Op.DIFFERENCE);
            paths[0].op(paths[1], Path.Op.DIFFERENCE);
            paths[0].op(paths[2], Path.Op.UNION);
            canvas.drawPath(paths[0], mPaint);
            isDraw = true;
        }

    }
}
