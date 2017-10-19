package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class MatrixTranslateView extends CustomBitmapView {

    Matrix matrix = new Matrix();

    public MatrixTranslateView(Context context) {
        super(context);
    }

    public MatrixTranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixTranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        matrix.reset();
        matrix.preTranslate(300, 300);
//        matrix.preRotate(90);
//        matrix.preSkew(0.5F, 0);
//        matrix.postTranslate(300, 300);
//        matrix.postRotate(90);
//        matrix.preSkew(0, 0.5F);

        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.restore();
    }
}
