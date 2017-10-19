package com.crazystone.me.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.crazystone.me.customview.CustomView;

/**
 * Created by crazy_stone on 17-8-11.
 */

public class MatrixTestView extends CustomView {

    Matrix matrix = new Matrix();
    float[] point = new float[]{10, 10};


    public MatrixTestView(Context context) {
        super(context);
    }

    public MatrixTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        matrix.reset();

//        matrix.preTranslate(10, 10);
//        matrix.preScale(2, 3);
//        matrix.preTranslate(20, 30);

        matrix.postTranslate(10, 10);
        matrix.postScale(2, 3);
        matrix.postTranslate(20, 30);

        matrix.mapPoints(point);
        Log.d(MatrixTestView.class.getSimpleName(), "x:" + point[0] + ",y:" + point[1]);
    }
}
