package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class MatrixScaleView extends CustomBitmapView {

    private Matrix matrix = new Matrix();

    public MatrixScaleView(Context context) {
        super(context);
    }

    public MatrixScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        matrix.reset();
        matrix.preScale(0.5F, 0.5F);
//        matrix.preTranslate(-bitmap.getWidth() >> 1, -bitmap.getHeight() >> 1);
//        matrix.postTranslate(bitmap.getWidth() >> 1, bitmap.getHeight() >> 1);
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }
}
