package com.crazystone.me.customview.practice_draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Views;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class PracticeDrawPieChartView extends CustomView {

    static final int TOTAL = 100;
    static final int RADIUS = 300;
    static final int AUXILIARY_LINE_LEN = 50;
    List<PieChartData> list;
    Random random;
    int[] colorArray = {R.color.yellow, R.color.blue, R.color.green, R.color.red};
    RectF rect;
    private float currentAngle = 0;

    public PracticeDrawPieChartView(Context context) {
        super(context);
    }

    public PracticeDrawPieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDrawPieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        mPaint.setStyle(Paint.Style.FILL);
        rect = new RectF();
        random = new Random();
        list = new ArrayList<>();
        generateDatas();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        Log.d(PracticeDrawPieChartView.class.getSimpleName(), ">>>>>>>>>>>>>>woca>>>>>>>>>>>>");
        canvas.translate(width >> 1, height >> 1);
//        canvas.scale(1, -1);
        currentAngle = 0;
        if (list == null || list.size() == 0) return;
        for (int i = 0; i < list.size(); i++) {
            mPaint.setColor(Views.getColorInt(getContext(), colorArray[(i) % 4]));
            rect.set(-RADIUS, -RADIUS, RADIUS, RADIUS);
            float value = list.get(i).getValue();
            float proportionAngle = 360F * (value / 100F);
//            Log.d(PracticeDrawPieChartView.class.getSimpleName(), "angle:" + proportionAngle + ",value:" + value + ",size:" + list.size());
//            Log.d(PracticeDrawPieChartView.class.getSimpleName(), "value:" + list.get(i).getValue() + ",valueName:" + list.get(i).getValueName());
            canvas.drawArc(rect, currentAngle, proportionAngle, true, mPaint);
            Log.d(PracticeDrawPieChartView.class.getSimpleName(), "proportionAngle:" + proportionAngle + ",currentAngle:" + currentAngle);
            drawValueName(canvas, (float) (proportionAngle + currentAngle + currentAngle) / 2F, list.get(i).getValueName());
            currentAngle += proportionAngle;
        }
    }

    private void drawValueName(Canvas canvas, float angle, String valueName) {
        Log.d(PracticeDrawPieChartView.class.getSimpleName(), ">>>angle:" + angle);
        float x = (float) (Math.cos(Math.toRadians(angle)) * RADIUS);
        float y = (float) (Math.sin(Math.toRadians(angle)) * RADIUS);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(30);
        drawQuadrantLine(canvas, x, y, valueName);
    }

    private void drawQuadrantLine(Canvas canvas, float x, float y, String valueName) {
        int number = 0;
        if (x >= 0 && y >= 0) {
            drawFirstQuadrant(canvas, x, y, valueName);
        } else if (x < 0 && y > 0) {
            drawSecondQuadrant(canvas, x, y, valueName);
        } else if (x < 0 && y < 0) {
            drawThirdQuadrant(canvas, x, y, valueName);
        } else if (x > 0 && y < 0) {
            drawFOurQuadrant(canvas, x, y, valueName);
        }
    }

    private void drawFirstQuadrant(Canvas canvas, float x, float y, String valueName) {
        float x1 = x + AUXILIARY_LINE_LEN;
        float y1 = y + AUXILIARY_LINE_LEN;
        float x2 = x1 + AUXILIARY_LINE_LEN;
        float y2 = y1;
        canvas.drawLine(x, y, x1, y1, mPaint);
        canvas.drawLine(x1, y1, x2, y2, mPaint);
        canvas.drawText(valueName, x2, y2, mPaint);
    }

    private void drawSecondQuadrant(Canvas canvas, float x, float y, String valueName) {
        float x1 = x - AUXILIARY_LINE_LEN;
        float y1 = y + AUXILIARY_LINE_LEN;
        float x2 = x1 - AUXILIARY_LINE_LEN;
        float y2 = y1;
        canvas.drawLine(x, y, x1, y1, mPaint);
        canvas.drawLine(x1, y1, x2, y2, mPaint);
        float textWidth = mPaint.measureText(valueName);
        canvas.drawText(valueName, x2 - textWidth, y2, mPaint);
    }

    private void drawThirdQuadrant(Canvas canvas, float x, float y, String valueName) {
        float x1 = x - AUXILIARY_LINE_LEN;
        float y1 = y - AUXILIARY_LINE_LEN;
        float x2 = x1 - AUXILIARY_LINE_LEN;
        float y2 = y1;
        canvas.drawLine(x, y, x1, y1, mPaint);
        canvas.drawLine(x1, y1, x2, y2, mPaint);
        float textWidth = mPaint.measureText(valueName);
        canvas.drawText(valueName, x2 - textWidth, y2, mPaint);
    }

    private void drawFOurQuadrant(Canvas canvas, float x, float y, String valueName) {
        float x1 = x + AUXILIARY_LINE_LEN;
        float y1 = y - AUXILIARY_LINE_LEN;
        float x2 = x1 + AUXILIARY_LINE_LEN;
        float y2 = y1;
        canvas.drawLine(x, y, x1, y1, mPaint);
        canvas.drawLine(x1, y1, x2, y2, mPaint);
        float textWidth = mPaint.measureText(valueName);
        canvas.drawText(valueName, x2, y2, mPaint);
    }


    public void showPieChart() {
        generateDatas();
        invalidate();
    }

    private void generateDatas() {

        int hasAllocate = 0;
        int size = random.nextInt(10);
        size = size == 0 ? 1 : size;
        for (int i = 0; i < size; i++) {
            float current = 0;
            if (i == size - 1) {
                current = TOTAL - hasAllocate;
                hasAllocate = TOTAL;
            } else {
                current = random.nextInt(TOTAL - hasAllocate);
                hasAllocate += current;
            }
            PieChartData data = new PieChartData();
            data.setValueName("value" + i);
            data.setValue(current);
            list.add(data);
            Log.d(PracticeDrawPieChartView.class.getSimpleName(), "current:" + current);
        }
    }


    private static class PieChartData {
        private float value;
        private String valueName;

        public float getValue() {
            return value;
        }

        public PieChartData setValue(float value) {
            this.value = value;
            return this;
        }

        public String getValueName() {
            return valueName;
        }

        public PieChartData setValueName(String valueName) {
            this.valueName = valueName;
            return this;
        }
    }

}
