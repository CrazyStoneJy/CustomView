package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class MatrixSkewView extends CustomBitmapView {
    Matrix matrix = new Matrix();
    float[] src = new float[]{10, 10};

    public MatrixSkewView(Context context) {
        super(context);
    }

    public MatrixSkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixSkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        matrix.reset();
        matrix.preTranslate(10, 10);
        matrix.preScale(2, 3);
//        matrix.preSkew(0.5f, 1f);
        matrix.mapPoints(src);
        Log.d(MatrixSkewView.class.getSimpleName(), "point x:" +src[0]+",y:"+src[1]);
    }
}
