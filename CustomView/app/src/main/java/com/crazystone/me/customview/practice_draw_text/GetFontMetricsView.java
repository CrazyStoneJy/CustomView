package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Views;
import com.crazystone.me.customview.utils.Windows;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class GetFontMetricsView extends CustomView {

    int[] colors = {R.color.red, R.color.orange, R.color.blue, R.color.green};
    float[] values = new float[4];

    public GetFontMetricsView(Context context) {
        super(context);
    }

    public GetFontMetricsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GetFontMetricsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        String str = "HelloWorld";
        mPaint.setTextSize(150);
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.BLACK);
        canvas.drawText(str, 100, 200, mPaint);

        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();

        values[0] = fontMetrics.ascent;
        values[1] = fontMetrics.bottom;
        values[2] = fontMetrics.descent;
        values[3] = fontMetrics.top;
        float leading = fontMetrics.leading;

        float width = Windows.getScreenWidth(getContext());
        Log.d(GetFontMetricsView.class.getSimpleName(), "ascent:" + values[0] + ",bottom:" + values[1] + ",descent:" + values[2] + ",top:" + values[3] + ",leading:" + leading);
        for (int i = 0; i < 4; i++) {
            mPaint.setColor(Views.getColorInt(colors[i]));
            float y = values[i] + 200;
            canvas.drawLine(0, y, width, y, mPaint);
        }

        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, 200, width, 200, mPaint);

    }
}
