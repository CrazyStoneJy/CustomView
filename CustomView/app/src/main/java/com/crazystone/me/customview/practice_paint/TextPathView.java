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

public class TextPathView extends CustomView {

    Path textPath;
    String str;

    public TextPathView(Context context) {
        super(context);
    }

    public TextPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        str = "HelloWorld";
        mPaint.setTextSize(50);
        textPath = new Path();
        mPaint.getTextPath(str, 0, str.length(), 100, 500, textPath);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawText(str, 100, 200, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(textPath, mPaint);

    }
}
