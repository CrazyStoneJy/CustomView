package com.crazystone.me.customview.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.utils.LeakHandler;

/**
 * Created by crazy_stone on 17-7-18.
 */

public class TestView extends CustomView {
    Paint mPaint;
    MyHandler handler;
    private int radius = 10;

    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        handler = new MyHandler(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(0, 0, radius, mPaint);
    }

    public void start() {
        Message msg = Message.obtain();
        msg.what = 10;
        handler.sendMessageDelayed(msg, 10);
    }


    public TestView setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    private static class MyHandler extends LeakHandler<TestView> {

        boolean isOrder = true;

        public MyHandler(TestView testView) {
            super(testView);
        }

        @Override
        protected void handleMessage(Message message, TestView testView) {

            int radius = message.what;

            Message msg = Message.obtain();
            if (radius <= 300 && isOrder) {
                isOrder = true;
                msg.what = radius + 10;
            } else if (radius > 0) {
                isOrder = false;
                msg.what = radius - 10;
            } else {
                isOrder = true;
                msg.what = 0;
            }
            testView.setRadius(radius);
            testView.invalidate();
            sendMessageDelayed(msg, 10);
        }
    }

}
