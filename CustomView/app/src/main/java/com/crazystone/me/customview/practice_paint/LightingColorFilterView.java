package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-7-20.
 */

public class LightingColorFilterView extends CustomView {

    ColorFilter greenColorFilter, blueColorFilter;
    Bitmap bitmap;

    public LightingColorFilterView(Context context) {
        super(context);
    }

    public LightingColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LightingColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yellow_man);
        greenColorFilter = new LightingColorFilter(0x00ff00, 0x000000);
        blueColorFilter = new LightingColorFilter(0xffff00, 0x2222222);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColorFilter(greenColorFilter);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);

        canvas.translate(250, 0);

        mPaint.setColorFilter(blueColorFilter);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);

    }
}
