package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class MatrixRotateView extends CustomBitmapView {

    Matrix matrix = new Matrix();
    int bitmapX = bitmap.getWidth() / 2;
    int bitmapY = bitmap.getHeight() / 2;

    public MatrixRotateView(Context context) {
        super(context);
    }

    public MatrixRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        matrix.reset();
        // pre先乘 post后乘
        // 表示 矩阵T x Rotate x Translate  所以先以图片中心点旋转45度,再平移(300,300)
        matrix.postRotate(45, bitmapX, bitmapY);
        matrix.postTranslate(300, 300);

        // Translate x Rotate x 矩阵T  先平移(300,300),在以(bitmapX,bitmapY)为中心旋转45度
//        matrix.preRotate(45, bitmapX, bitmapY);
//        matrix.preTranslate(300, 300);

        // Translate x 矩阵T x Rotate  先平移(300,300),在以(bitmapX,bitmapY)为中心旋转45度
//        matrix.postRotate(45, bitmapX, bitmapY);
//        matrix.preTranslate(300, 300);

        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.restore();
    }
}
