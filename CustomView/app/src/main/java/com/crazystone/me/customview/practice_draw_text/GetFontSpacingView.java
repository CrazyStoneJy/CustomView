package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class GetFontSpacingView extends CustomView {
    public GetFontSpacingView(Context context) {
        super(context);
    }

    public GetFontSpacingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GetFontSpacingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {

        float spacing = mPaint.getFontSpacing();
        mPaint.setTextSize(50);
        canvas.drawText("HelloWorld", 50, 100, mPaint);

        Log.d(GetFontSpacingView.class.getSimpleName(), "spacing:" + spacing);
        canvas.drawText("HelloWorld", 50, 100 + spacing, mPaint);


    }


}
