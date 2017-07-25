package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class SetFakeBoldTextView extends CustomView {
    public SetFakeBoldTextView(Context context) {
        super(context);
    }

    public SetFakeBoldTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SetFakeBoldTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setFakeBoldText(true);
        mPaint.setTextSize(50);
        canvas.drawText("HelloWorld",10,50,mPaint);

        canvas.translate(0,200);
        mPaint.setFakeBoldText(false);
        canvas.drawText("HelloWorld",10,50,mPaint);

    }
}
