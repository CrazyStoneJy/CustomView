package com.crazystone.me.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.utils.LeakHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by crazy_stone on 17-7-11.
 */

public class LeavesView extends CustomView {

    /* 外边圆角矩形的半径 */
    static final int OUTTER_ROUND_RECT_DIAMETER = 180;
    /* 内部进度的圆角矩形半径 */
    static final int INNER_ROUND_RECT_DIAMETER = 150;
    /* 进度条矩形的实际长度 */
    static final int PROGRESS_RECT_LENGTH = 500;
    // 叶子飘动一个周期所花的时间
    private static final long LEAF_FLOAT_TIME = 3000;
    // 叶子旋转一周需要的时间
    private static final long LEAF_ROTATE_TIME = 2000;
    RectF outterRect = new RectF();
    RectF innerRect = new RectF();
    Paint mPaint;
    int progress;
    /* 外部圆角矩形与内部圆角矩形的间距 */
    int offset;
    /* 进度条的实际长度 */
    int progressWidth;
    ProgressHandler handler;
    Random random;
    private int mCurrentProgressWidth;

    public LeavesView(Context context) {
        super(context);
    }

    public LeavesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LeavesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //外部圆角矩形的颜色
        mPaint.setColor(Color.parseColor("#fce196"));

        offset = (OUTTER_ROUND_RECT_DIAMETER - INNER_ROUND_RECT_DIAMETER) >> 1;
        progressWidth = INNER_ROUND_RECT_DIAMETER / 2 + PROGRESS_RECT_LENGTH;
        handler = new ProgressHandler(this);
        random = new Random();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#fdcd4d"));
        drawOutter(canvas);
        drawProgress(canvas);
    }

    private void drawProgress(Canvas canvas) {
        mPaint.setColor(Color.parseColor("#ffa800"));

        if (mCurrentProgressWidth < INNER_ROUND_RECT_DIAMETER / 2) {
//            //计算弧形的夹角的一半
            float angle = (float) Math.toDegrees(Math.acos((INNER_ROUND_RECT_DIAMETER / 2 - mCurrentProgressWidth) / (float) (INNER_ROUND_RECT_DIAMETER / 2)));
            Log.d(LeavesView.class.getSimpleName(), "angle:" + angle);
            int arcHalfHeight = (int) ((INNER_ROUND_RECT_DIAMETER / 2) * Math.sin(Math.toRadians(angle)));
//            int top = INNER_ROUND_RECT_DIAMETER / 2 - arcHalfHeight;
//            int bottom = top + arcHalfHeight * 2;
            innerRect.set(offset, offset, offset + mCurrentProgressWidth, offset + INNER_ROUND_RECT_DIAMETER);
            float startAngle = 180 - angle;
            float sweepAngle = 2 * angle;
            canvas.drawArc(innerRect, startAngle, sweepAngle, false, mPaint);
        } else {
            innerRect.set(offset, offset, INNER_ROUND_RECT_DIAMETER + offset, INNER_ROUND_RECT_DIAMETER + offset);
            canvas.drawArc(innerRect, 90, 180, true, mPaint);
            canvas.drawRect(INNER_ROUND_RECT_DIAMETER / 2 + offset, offset, mCurrentProgressWidth + offset, INNER_ROUND_RECT_DIAMETER + offset, mPaint);
        }

    }

    public void start() {
        Message msg = Message.obtain();
        msg.what = 1;
        handler.sendMessageDelayed(msg, 100);
    }

    public void setProgress(int progress) {
        if (progress > 100) {
            this.progress = 100;
        }
        this.progress = progress;
        mCurrentProgressWidth = (int) (progressWidth * progress / 100F);
        Log.d(LeavesView.class.getSimpleName(), "mCurrentProgressWidth:" + mCurrentProgressWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec, 400);

    }

    private void drawOutter(Canvas canvas) {
        //外部圆角矩形的颜色
        mPaint.setColor(Color.parseColor("#fce196"));
        outterRect.set(0F, 0F, OUTTER_ROUND_RECT_DIAMETER, OUTTER_ROUND_RECT_DIAMETER);
        canvas.drawArc(outterRect, 90, 180, true, mPaint);
        canvas.drawRect(OUTTER_ROUND_RECT_DIAMETER / 2, 0, OUTTER_ROUND_RECT_DIAMETER / 2 + PROGRESS_RECT_LENGTH, OUTTER_ROUND_RECT_DIAMETER, mPaint);
    }

    private Leaf generateLeaf() {
        Leaf leaf = new Leaf();
        int type = random.nextInt(2);
        leaf.setAmplitudeType(AmplitudeType.BIG.value == type ? AmplitudeType.BIG : (AmplitudeType.MID.value == type ? AmplitudeType.MID : AmplitudeType.SMALL));
        leaf.setRotateAngle(random.nextInt(360));
        leaf.setRotateDirection(random.nextBoolean());
        // 为了产生交错的感觉，让开始的时间有一定的随机性
        int mAddTime = random.nextInt((int) (LEAF_FLOAT_TIME * 1.5));
        long startTime = System.currentTimeMillis() + mAddTime;
        leaf.setStartTime(startTime);
        return leaf;
    }

    public List<Leaf> generateLeaves(){
        int number = random.nextInt(10);
        List<Leaf> list = new ArrayList<>();
        for(int i=0;i<number;i++){
            list.add(generateLeaf());
        }
        return list;

    }

    /**
     * 振幅类型
     */
    private enum AmplitudeType {
        SMALL(0, 8),
        MID(1, 13),
        BIG(2, 18);
        int value;
        /**
         * 振幅
         */
        int amplitude;

        AmplitudeType(int value, int amplitude) {
            this.value = value;
            this.amplitude = amplitude;
        }
    }

    private static class ProgressHandler extends LeakHandler<LeavesView> {

        public ProgressHandler(LeavesView leavesView) {
            super(leavesView);
        }

        @Override
        protected void handleMessage(Message message, LeavesView leavesView) {
            int progress = message.what;
            if (progress <= 100) {
                Log.d(LeavesView.class.getSimpleName(), "handler progress:" + progress);
                leavesView.setProgress(progress);
                leavesView.invalidate();
                Message msg = Message.obtain();
                msg.what = ++progress;
                Random random = new Random();
                sendMessageDelayed(msg, random.nextInt(100));
            }
        }
    }

    private class Leaf {

        private int x, y;
        private AmplitudeType amplitudeType;
        private float rotateAngle;
        private boolean rotateDirection;
        private long startTime;

        public int getX() {
            return x;
        }

        public Leaf setX(int x) {
            this.x = x;
            return this;
        }

        public int getY() {
            return y;
        }

        public Leaf setY(int y) {
            this.y = y;
            return this;
        }

        public AmplitudeType getAmplitudeType() {
            return amplitudeType;
        }

        public Leaf setAmplitudeType(AmplitudeType amplitudeType) {
            this.amplitudeType = amplitudeType;
            return this;
        }

        public float getRotateAngle() {
            return rotateAngle;
        }

        public Leaf setRotateAngle(float rotateAngle) {
            this.rotateAngle = rotateAngle;
            return this;
        }

        public boolean isRotateDirection() {
            return rotateDirection;
        }

        public Leaf setRotateDirection(boolean rotateDirection) {
            this.rotateDirection = rotateDirection;
            return this;
        }

        public long getStartTime() {
            return startTime;
        }

        public Leaf setStartTime(long startTime) {
            this.startTime = startTime;
            return this;
        }
    }


}
