package com.crazystone.me.customview.practice_draw4;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class FlipBoardView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_maps);
    Point point = new Point(200, 200);
    Camera camera = new Camera();
    int centerX, centerY;
    Rect rect = new Rect();
    Matrix matrix = new Matrix();
    float degree;
    ObjectAnimator animator;

    public FlipBoardView(Context context) {
        super(context);
    }

    public FlipBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlipBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        centerX = bitmap.getWidth() / 2 + point.x;
        centerY = bitmap.getHeight() / 2 + point.y;
        rect.set(point.x, point.y, point.x + bitmap.getWidth(), point.y + bitmap.getHeight() / 2);

        animator = ObjectAnimator.ofFloat(this, "degree", 0, 180);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //截取图片上半部分
        canvas.save();
        canvas.clipRect(rect);
        canvas.drawBitmap(bitmap, point.x, point.y, mPaint);
        canvas.restore();

        camera.save();
        matrix.reset();
        camera.rotateX(degree);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);

        canvas.save();
        if (degree < 90) {
            canvas.clipRect(0, centerY, getWidth(), getHeight());
        } else {
            canvas.clipRect(0, 0, getWidth(), centerY);
        }
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point.x, point.y, mPaint);
        canvas.restore();

//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(5);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);

    }

    public float getDegree() {
        return degree;
    }

    public FlipBoardView setDegree(float degree) {
        this.degree = degree;
        invalidate();
        return this;
    }
}
