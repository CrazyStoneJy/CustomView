package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class SetUnderlineTextView extends CustomView {
    public SetUnderlineTextView(Context context) {
        super(context);
    }

    public SetUnderlineTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SetUnderlineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setUnderlineText(true);
        mPaint.setTextSize(50);
        canvas.drawText("HelloWolrd", 10, 50, mPaint);
    }
}
