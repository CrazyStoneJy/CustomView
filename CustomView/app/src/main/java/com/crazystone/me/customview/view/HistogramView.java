package com.crazystone.me.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Windows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *　直方图
 * Created by crazy_stone on 17-7-10.
 */

public class HistogramView extends View {

    /* 坐标轴上下左右的间距 */
    final static int MARGIN_SIDE = 50;
    /* 直方图的间距 */
    final static int MARGIN_OFFSET = 20;
    /* 坐标轴的高度 */
    final static int COORDINATE_HEIGHT = 400;
    /* 直方图中间圆圈的半径 */
    final static int CIRCLE_RADIUS = 10;
    Paint mPaint;
    Paint mTextPaint;
    Paint mCirclePaint;
    Paint curvePaint;
    List<HistogramModel> list = new ArrayList<>();
    Random random;
    Path path;

    {
        random = new Random();
    }

    public HistogramView(Context context) {
        super(context);
        init();
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GRAY);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(Color.BLACK);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(getResources().getColor(R.color.green));
        curvePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        curvePaint.setStyle(Paint.Style.STROKE);
        curvePaint.setStrokeWidth(10);
        curvePaint.setColor(getResources().getColor(R.color.red));
        path = new Path();
        initData();
    }

    private void initData() {
        for (int i = 0; i < 7; i++) {
            HistogramModel model = new HistogramModel();
            int value = random.nextInt(100);
            model.setValue(value);
            model.setValueColor(random.nextBoolean() ? getResources().getColor(R.color.orange) : getResources().getColor(R.color.blue));
            model.setValueName("value" + i);
            list.add(model);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {

        drawCoordinate(canvas);

        drawHistogram(canvas);
    }

    private void drawHistogram(Canvas canvas) {
        int width = Windows.getScreenWidth(getContext()) - MARGIN_SIDE * 2;
        int rectWidth = (int) ((float) width / list.size() - MARGIN_OFFSET);
        int start = MARGIN_SIDE;
        for (int i = 0; i < list.size(); i++) {
            Rect rect = new Rect();
            int left = start + MARGIN_OFFSET * (i + 1) + rectWidth * i;
            int top = (int) ((1 - (float) list.get(i).getValue() / 100f) * COORDINATE_HEIGHT) + MARGIN_SIDE;
            int right = start + MARGIN_OFFSET * (i + 1) + rectWidth * (i + 1);
            int bottom = MARGIN_SIDE + COORDINATE_HEIGHT;
            rect.set(left, top, right, bottom);
            mPaint.setColor(list.get(i).getValueColor());
            canvas.drawRect(rect, mPaint);

            //矩形中心坐标
            int rectXCenter = (left + right) / 2;
            int rectYCenter = (top + bottom) / 2;

//            //显示矩形中间的文字
            mTextPaint.setTextSize(50);
            float valueWidth = mTextPaint.measureText(String.valueOf(list.get(i).getValue()));
            canvas.drawText(String.valueOf(list.get(i).getValue()), rectXCenter - valueWidth / 2, MARGIN_SIDE, mTextPaint);

            //画文字
            mPaint.setTextSize(30);
            float textWidth = mPaint.measureText(list.get(0).getValueName());
            mPaint.setColor(Color.BLACK);
            canvas.drawText(list.get(i).getValueName(), rectXCenter - textWidth / 2, MARGIN_SIDE + COORDINATE_HEIGHT + MARGIN_SIDE, mPaint);

            //画折线
//            canvas.drawCircle(rectXCenter, rectYCenter, CIRCLE_RADIUS, mCirclePaint);
            if (i == 0) {
                path.moveTo(rectXCenter, rectYCenter);
            } else {
                path.lineTo(rectXCenter, rectYCenter);
            }
        }
        canvas.drawPath(path, curvePaint);
    }

    private void drawCoordinate(Canvas canvas) {
        canvas.drawLine(MARGIN_SIDE, MARGIN_SIDE + COORDINATE_HEIGHT, Windows.getScreenWidth(getContext()) - MARGIN_SIDE, MARGIN_SIDE + COORDINATE_HEIGHT, mPaint);
        canvas.drawLine(MARGIN_SIDE, MARGIN_SIDE, MARGIN_SIDE, MARGIN_SIDE + COORDINATE_HEIGHT, mPaint);
    }

    private class HistogramModel {

        private int value;
        private String valueName;
        @ColorInt
        private int valueColor;

        public int getValue() {
            return value;
        }

        public HistogramModel setValue(int value) {
            this.value = value;
            return this;
        }

        public String getValueName() {
            return valueName;
        }

        public HistogramModel setValueName(String valueName) {
            this.valueName = valueName;
            return this;
        }

        public int getValueColor() {
            return valueColor;
        }

        public HistogramModel setValueColor(int valueColor) {
            this.valueColor = valueColor;
            return this;
        }
    }

}
