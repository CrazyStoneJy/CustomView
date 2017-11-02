package com.crazystone.me.customview.callforpaper;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-10-17.
 */

public class FlipBoardView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_maps);
    int centerX;
    int centerY;
    int startX, startY;
    Camera camera = new Camera();
    Matrix matrix = new Matrix();
    float degreeY;
    float degreeZ;
    float fixDegreeY;
    ObjectAnimator degreeYAnimator = ObjectAnimator.ofFloat(this, "degreeY", 0, -45);
    ObjectAnimator rotateZAnimator = ObjectAnimator.ofFloat(this, "degreeZ", 0, 270);
    ObjectAnimator fixDegreeAnimator = ObjectAnimator.ofFloat(this, "fixDegreeY", 0, 30);
    AnimatorSet animatorSet = new AnimatorSet();
    Rect rect = new Rect();

    {
        degreeYAnimator.setDuration(1000);
        degreeYAnimator.setInterpolator(new LinearInterpolator());
//        degreeYAnimator.setRepeatMode(ValueAnimator.REVERSE);
//        degreeYAnimator.setRepeatCount(ValueAnimator.INFINITE);

        rotateZAnimator.setDuration(2000);
        rotateZAnimator.setInterpolator(new LinearInterpolator());
//        rotateZAnimator.setRepeatCount(Va);

        fixDegreeAnimator.setDuration(1000);
        fixDegreeAnimator.setInterpolator(new LinearInterpolator());

        animatorSet.playSequentially(degreeYAnimator, rotateZAnimator, fixDegreeAnimator);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                reset();
                animatorSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public FlipBoardView(Context context) {
        super(context);
        init();
    }

    public FlipBoardView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlipBoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float newZ = -displayMetrics.density * 6;
        Log.d(FlipBoardView.class.getName(), "newZ:" + newZ);
        camera.setLocation(0, 0, newZ);
        animatorSet.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        animatorSet.end();

    }

    private void reset() {
        degreeY = 0;
        degreeZ = 0;
        fixDegreeY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        startX = centerX - bitmap.getWidth() / 2;
        startY = centerY - bitmap.getHeight() / 2;

        //画变换的一半
        //先旋转，再裁切，再使用camera执行3D动效,**然后保存camera效果**,最后再旋转回来
//        canvas.save();
//        camera.save();
//        canvas.translate(centerX, centerY);
//        canvas.rotate(-degreeZ);
//        camera.rotateY(degreeY);
//        camera.applyToCanvas(canvas);
//        //计算裁切参数时清注意，此时的canvas的坐标系已经移动
//        canvas.clipRect(0, -centerY, centerX, centerY);
//        canvas.rotate(degreeZ);
//        canvas.translate(-centerX, -centerY);
//        camera.restore();
//        canvas.drawBitmap(bitmap, startX, startY, mPaint);
//        canvas.restore();

        //画不变换的另一半
        canvas.save();
        camera.save();
        canvas.translate(centerX, centerY);
        canvas.rotate(-degreeZ);
//        //计算裁切参数时清注意，此时的canvas的坐标系已经移动
        canvas.clipRect(-centerX, -centerY, 0, centerY);
        //此时的canvas的坐标系已经旋转，所以这里是rotateY
        camera.rotateY(fixDegreeY);
        camera.applyToCanvas(canvas);
        canvas.rotate(degreeZ);
        canvas.translate(-centerX, -centerY);
        camera.restore();
        canvas.drawBitmap(bitmap, startX, startY, mPaint);
        canvas.restore();

    }

    public float getDegreeY() {
        return degreeY;
    }

    public FlipBoardView setDegreeY(float degreeY) {
        this.degreeY = degreeY;
        invalidate();
        return this;
    }

    public float getDegreeZ() {
        return degreeZ;
    }

    public FlipBoardView setDegreeZ(float degreeZ) {
        this.degreeZ = degreeZ;
        invalidate();
        return this;
    }

    public float getFixDegreeY() {
        return fixDegreeY;
    }

    public FlipBoardView setFixDegreeY(float fixDegreeY) {
        this.fixDegreeY = fixDegreeY;
        invalidate();
        return this;
    }
}
