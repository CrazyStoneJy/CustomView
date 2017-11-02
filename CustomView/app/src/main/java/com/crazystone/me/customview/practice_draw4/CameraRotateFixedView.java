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
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class CameraRotateFixedView extends View {

    Camera camera = new Camera();
    Bitmap bitmap = null;
    ObjectAnimator animator = null;
    int bitmapX;
    int bitmapY;
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmapScale;
    Matrix matrix = new Matrix();
    Point point = new Point(100, 100);
    private float degree;

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_maps);

//        bitmapScale = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() * 2, bitmap.getHeight() * 2, false);
        bitmapX = bitmap.getWidth() / 2 + point.x;
        bitmapY = bitmap.getHeight() / 2 + point.y;

        animator = ObjectAnimator.ofFloat(this, "degree", 0, 360);
        animator.setDuration(5000)
                .setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
    }

    public CameraRotateFixedView(Context context) {
        super(context);
    }

    public CameraRotateFixedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraRotateFixedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animator.end();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        camera.save();
        camera.rotateX(degree);
        // 将bitmap中心点移到原点(0,0,0)即屏幕的左上角 ,再将canvas移回 ,canvas的几何变换是逆序的,先执行canvas.translate(-bitmapX, -bitmapY)再执行canvas.translate(bitmapX, bitmapY)
//        canvas.translate(bitmapX, bitmapY);
//        camera.applyToCanvas(canvas);
//        canvas.translate(-bitmapX, -bitmapY);

        //用matrix实现
        matrix.reset();
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-bitmapX, -bitmapY);
        matrix.postTranslate(bitmapX, bitmapY);

        canvas.save();
        canvas.concat(matrix);
        canvas.drawBitmap(bitmap, point.x, point.y, mPaint);
        canvas.restore();
    }

    public float getDegree() {
        return degree;
    }

    public CameraRotateFixedView setDegree(float degree) {
        this.degree = degree;
        invalidate();
        return this;
    }
}
