package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class SetTextAlignView extends CustomView {
    public SetTextAlignView(Context context) {
        super(context);
    }

    public SetTextAlignView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SetTextAlignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setTextSize(50);
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("HelloWorld", 400, 50, mPaint);

        canvas.translate(0, 200);
        mPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("HelloWorld", 400, 50, mPaint);

        canvas.translate(0, 200);
        mPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("HelloWorld", 400, 50, mPaint);
    }

}
