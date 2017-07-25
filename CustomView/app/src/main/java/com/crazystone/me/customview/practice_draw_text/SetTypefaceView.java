package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class SetTypefaceView extends CustomView {

    Typeface[] typefaces;

    public SetTypefaceView(Context context) {
        super(context);
    }

    public SetTypefaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SetTypefaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

        mPaint.setTextSize(50);
        typefaces = new Typeface[3];
        typefaces[0] = Typeface.MONOSPACE;
        typefaces[1] = Typeface.SANS_SERIF;
        typefaces[2] = Typeface.SERIF;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(0,200);
        mPaint.setTypeface(typefaces[0]);
        canvas.drawText("Hello World 你好世界", 20, 100, mPaint);

        canvas.translate(0,200);
        mPaint.setTypeface(typefaces[1]);
        canvas.drawText("Hello World 你好世界", 20, 100, mPaint);

        canvas.translate(0,200);
        mPaint.setTypeface(typefaces[2]);
        canvas.drawText("Hello World 你好世界", 20, 100, mPaint);

        canvas.translate(0,200);
        mPaint.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Achaflft.ttf"));
        canvas.drawText("Hello World 你好世界", 20, 100, mPaint);

    }
}
