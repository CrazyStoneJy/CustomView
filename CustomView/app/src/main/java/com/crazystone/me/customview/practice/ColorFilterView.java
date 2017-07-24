package com.crazystone.me.customview.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.ColorMatrixs;

/**
 * Created by crazy_stone on 17-7-18.
 */

public class ColorFilterView extends CustomView {

    Paint mPaint;
    Bitmap bitmap;
    ColorMatrixColorFilter colorMatrixFilter;

    public ColorFilterView(Context context) {
        super(context);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yellow_man);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(ColorMatrixs.PRIME);
        colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, 500);
        setMeasuredDimension(300,300);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }

    public void setColorMatrix(ColorMatrix colorMatrix) {
        colorMatrixFilter = new ColorMatrixColorFilter(colorMatrix);
        invalidate();
    }

    public void setStyle(float[] matrix) {
        ColorMatrix colorMatrix = new ColorMatrix(matrix);
        setColorMatrix(colorMatrix);
    }

}
