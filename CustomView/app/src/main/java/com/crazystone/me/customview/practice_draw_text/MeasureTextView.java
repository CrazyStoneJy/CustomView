package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class MeasureTextView extends CustomView {
    public MeasureTextView(Context context) {
        super(context);
    }

    public MeasureTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setTextSize(50);
        String str = "HelloWorld";
        canvas.drawText(str, 10, 50, mPaint);
        float width = mPaint.measureText(str);
        canvas.drawText(String.valueOf(width), 10, 50 + mPaint.getFontSpacing(), mPaint);

    }


}
