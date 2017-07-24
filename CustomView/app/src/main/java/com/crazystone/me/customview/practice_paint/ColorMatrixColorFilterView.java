package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.ColorMatrixs;

/**
 * Created by crazy_stone on 17-7-20.
 */

public class ColorMatrixColorFilterView extends CustomView {

    ColorMatrixColorFilter colorMatrixColorFilter;
    Bitmap bitmap;

    public ColorMatrixColorFilterView(Context context) {
        super(context);
    }

    public ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        ColorMatrix colorMatrix = new ColorMatrix(ColorMatrixs.GRAY);
        colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yellow_man);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }
}
