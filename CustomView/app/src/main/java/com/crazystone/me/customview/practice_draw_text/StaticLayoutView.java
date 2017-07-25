package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class StaticLayoutView extends CustomView {

    StaticLayout staticLayout1, staticLayout2, staticLayout3, staticLayout4;

    public StaticLayoutView(Context context) {
        super(context);
    }

    public StaticLayoutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StaticLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mPaint.setTextSize(50);
        TextPaint textPaint = new TextPaint();
        textPaint.set(mPaint);
        staticLayout1 = new StaticLayout(CommonStrings.LONG_STRING, textPaint, 500, Layout.Alignment.ALIGN_CENTER, 1, 0, true);
        staticLayout2 = new StaticLayout(CommonStrings.LONG_STRING, textPaint, 500, Layout.Alignment.ALIGN_NORMAL, 1, 0, true);
        staticLayout3 = new StaticLayout(CommonStrings.LONG_STRING, textPaint, 500, Layout.Alignment.ALIGN_OPPOSITE, 1, 0, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        staticLayout1.draw(canvas);
        canvas.translate(0, 300);
        staticLayout2.draw(canvas);
        canvas.translate(0, 300);
        staticLayout3.draw(canvas);
    }


}
