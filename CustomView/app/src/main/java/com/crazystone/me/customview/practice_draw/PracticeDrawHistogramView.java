package com.crazystone.me.customview.practice_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.crazystone.me.customview.view.HistogramView;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class PracticeDrawHistogramView extends HistogramView {
    public PracticeDrawHistogramView(Context context) {
        super(context);
    }

    public PracticeDrawHistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDrawHistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(PracticeDrawHistogramView.class.getSimpleName(), ">>>>annie>>>");
    }
}
