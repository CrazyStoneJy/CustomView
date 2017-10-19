package com.crazystone.me.customview.practice6;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Utils;
import com.crazystone.me.customview.utils.Views;

/**
 * Created by crazy_stone on 17-10-16.
 */

public class PracticeProgressView extends CustomView {

    int height, width;
    int progress;
    float startAngle = 135F;
    int centerX, centerY;
    float radius = Utils.dpToPixel(80);


    public PracticeProgressView(Context context) {
        super(context);
    }

    public PracticeProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mPaint.setStrokeWidth(Utils.dpToPixel(10));
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = (int) Utils.dpToPixel(200);
        width = (int) Utils.dpToPixel(200);
        setMeasuredDimension(width, height);
//        rectF = new RectF(0, 0, widthMeasureSpec, height);
    }

//    @Override
//    protected void dispatchDraw(Canvas canvas) {
//        super.dispatchDraw(canvas);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        centerX = this.getWidth() / 2;
        centerY = this.getHeight() / 2;

        //draw text color
        mPaint.setColor(Views.getColorInt(R.color.white));
        mPaint.setStrokeWidth(Utils.dpToPixel(8));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(Utils.dpToPixel(40));
        String progressText = String.valueOf(progress) + "%";
        float textLen = mPaint.measureText(progressText);
        canvas.drawText(progressText, width / 2 - textLen / 2, (height - (mPaint.ascent() + mPaint.descent())) / 2, mPaint);

        //draw progress
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Utils.dpToPixel(10));
        mPaint.setColor(Views.getColorInt(R.color.colorAccent));
        if (Utils.api(Build.VERSION_CODES.LOLLIPOP)) {
            canvas.drawArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius, startAngle, 270F * ((float) progress / 100F), false, mPaint);
        }
    }

    public void start() {
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "progress", 0, 65);
        animator.setDuration(2000)
                .setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();

            }
        });
        animator.start();
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }
}
