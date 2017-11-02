package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.utils.Utils;

/**
 * Created by crazy_stone on 17-10-31.
 */

public class TextOnPathView extends CustomView {

    Path path;
    String string = "hello,jiayan";

    public TextOnPathView(Context context) {
        super(context);
    }

    public TextOnPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TextOnPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        path = new Path();
        path.moveTo(100, 100);
        path.quadTo(0, 200, 200, 300);
        path.rLineTo(100, 50);
        path.rLineTo(100, 100);

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(Utils.dpToPixel(30));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawTextOnPath(string, path, 0, 8, mPaint);
//        canvas.drawText(string, 200, 500, mPaint);
    }



}
