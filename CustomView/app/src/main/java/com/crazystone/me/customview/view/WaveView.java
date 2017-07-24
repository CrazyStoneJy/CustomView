package com.crazystone.me.customview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.crazystone.me.customview.utils.Windows;

import java.lang.ref.WeakReference;

/**
 * Created by crazy_stone on 17-5-19.
 */

public class WaveView extends View {

    /**
     * 最大振幅
     */
    private static final int DEFAULT_WAVE_HEIGHT = 30;
    private static final int DEFAULT_VIEW_SIZE = 300;
    public int maxProgress = 70;
    Paint paint;
    Path wavePath;
    Path circlePath;
    ValueAnimator animator;
    private int width, height;
    private int centerY, centerX;
    private int offset = 0;
    private int waveLen;
    private int progress = 50;
    private int circleRadius = 0;
    private ProgressHandler progressHandler;

    public WaveView(Context context) {
        super(context);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);

        wavePath = new Path();
        circlePath = new Path();
        progressHandler = new ProgressHandler(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        centerY = h / 2;
        centerX = w / 2;
        waveLen = width;
        circleRadius = Math.min(centerX, centerY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int viewHeight = MeasureSpec.EXACTLY == heightMode ? heightSize : DEFAULT_VIEW_SIZE;
        int viewWidth = MeasureSpec.EXACTLY == widthMode ? widthSize : DEFAULT_VIEW_SIZE;
        int size = Math.min(viewHeight, viewWidth);
        Log.d(WaveView.class.getSimpleName(), "size:" + size + ",width:" + viewWidth + ",height:" + viewHeight);
        Log.d(WaveView.class.getSimpleName(), "window width:" + Windows.getScreenWidth(getContext()) + ",height:" + Windows.getScreenHeight(getContext()));
        setMeasuredDimension(size, size);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        wavePath.reset();
        circlePath.reset();

        drawCircle(canvas, Color.MAGENTA);
        drawWave(canvas);
        drawPercentageText(canvas);

    }

    private void drawPercentageText(Canvas canvas) {


    }


    private void drawWave(Canvas canvas) {
        if (progress == 0) return;
        if (progress == 100) {
            drawCircle(canvas, Color.CYAN);
            return;
        }
        paint.setColor(Color.CYAN);
        wavePath.moveTo(-waveLen + offset, getWaveHeight());
        for (int i = -waveLen; i <= width; i += waveLen) {
            wavePath.quadTo(width / 4 + i + offset, getWaveHeight() - DEFAULT_WAVE_HEIGHT, width / 2 + i + offset, getWaveHeight());
            wavePath.quadTo(width * 3 / 4 + i + offset, getWaveHeight() + DEFAULT_WAVE_HEIGHT, width + i + offset, getWaveHeight());
        }
        wavePath.lineTo(width, height);
        wavePath.lineTo(0, height);
        wavePath.close();

        circlePath.addCircle(centerX, centerY, circleRadius, Path.Direction.CW);

        circlePath.op(wavePath, Path.Op.INTERSECT);
        canvas.drawPath(circlePath, paint);

    }

    private int getWaveHeight() {
        return (int) ((centerY + circleRadius) - (progress / 100F) * (circleRadius * 2));
    }

    private void drawCircle(Canvas canvas, @ColorInt int color) {
        paint.setColor(color);
        canvas.drawCircle(centerX, centerY, circleRadius, paint);
//        canvas.drawPath(circlePath, paint);
    }

    public int getMaxProgress() {
        return maxProgress;
    }

    public WaveView setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
        return this;
    }

    public void start() {
        resetAnim();
        showAnim();
    }

    public void stop() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator = null;
        }
    }

    public int getOffset() {
        return offset;
    }

    public WaveView setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    public int getProgress() {
        return progress;
    }

    public WaveView setProgress(int progress) {
        this.progress = progress;
        invalidate();
        return this;
    }

    private void showAnim() {
        animator = ValueAnimator.ofInt(0, waveLen);
        animator.setDuration(2000);
        animator.setRepeatCount(Integer.MAX_VALUE);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setOffset((Integer) animation.getAnimatedValue());
                invalidate();
            }
        });
    }

    private void resetAnim() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
            animator = null;
        }
    }

    private static class ProgressHandler extends Handler {

        private WeakReference<WaveView> ref;
        private int currentProgress = 0;

        public ProgressHandler(WaveView waveView) {
            ref = new WeakReference<>(waveView);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (ref != null) {
                WaveView waveView = ref.get();
                if (waveView != null) {
                    if (currentProgress < waveView.getMaxProgress()) {
                        currentProgress += 1;
                        waveView.setProgress(currentProgress);
                        waveView.postInvalidate();
                    }
                }
            }
        }
    }

}
