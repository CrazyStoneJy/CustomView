package com.crazystone.me.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.entity.ChartData;
import com.crazystone.me.customview.entity.IChartData;
import com.crazystone.me.customview.utils.Views;
import com.crazystone.me.customview.utils.Windows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by crazy_stone on 17-7-10.
 */

public class RadarView extends View {

    /* 多边形的边 */
    final static int EDGE_COUNT = 6;
    /* 嵌套的层数 */
    final static int NESTING_COUNT = 5;
    /* 多边形半径的长度 */
    final static int POLYGON_RADIUS = 70;
    /* 多边形增大的比率 */
    final static float RADIO = 1.0F;
    /* 画文字的长度比例 */
    final static float TEXT_RADIO = 1.2F;
    final static float MAX_VALUE = 100F;
    float radians;
    Random random = new Random();
    int maxRadius;
    private Paint mPaint, overLayPaint, circlePaint, textPaint;
    private int centerWidth = 0, centerHeight;
    private Path path;
    private List<IChartData> list = new ArrayList<>();

    public RadarView(Context context) {
        super(context);
        init();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(2);

        overLayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        overLayPaint.setStyle(Paint.Style.FILL);
        overLayPaint.setColor(Views.getColorInt(getContext(), R.color.transparent_blue));

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setColor(Views.getColorInt(getContext(), R.color.blue));

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);

        path = new Path();
        radians = (float) (Math.PI * (360F / EDGE_COUNT) / 180F);
        initData();
    }

    private void initData() {
        for (int i = 0; i < EDGE_COUNT; i++) {
            ChartData data = new ChartData();
            data.setValue(random.nextInt(100))
                    .setValueName("value" + i);
            list.add(data);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerWidth = Windows.getScreenWidth(getContext()) / 2;
        centerHeight = Windows.getScreenHeight(getContext()) / 2;
        Log.d(RadarView.class.getSimpleName(), "centerHeight:" + centerHeight + ",centerWidth:" + centerWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerWidth, centerHeight);
        //将起始坐标摆放在纵坐标轴
//        canvas.rotate(270);
        drawSpiderNet(canvas);
        drawAuxiliaryLine(canvas);
        drawValuePoint(canvas);
    }

    private void drawValuePoint(Canvas canvas) {
        path.reset();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                float radio = list.get(i).getValue() / MAX_VALUE;
                Log.d(RadarView.class.getSimpleName(), "radio:" + radio);
                float x = (float) (Math.cos(radians * i) * maxRadius * radio);
                float y = (float) (Math.sin(radians * i) * maxRadius * radio);
                if (i == 0) {
                    path.moveTo(x, 0);
                } else {
                    path.lineTo(x, y);
                }
                canvas.drawCircle(x, y, 5, circlePaint);
                //draw text value
                textPaint.setTextSize(40);
                String valueName = list.get(i).getValueName();
                float valueWidth = textPaint.measureText(valueName);
                canvas.drawText(valueName, (float) (Math.cos(radians * i) * maxRadius) * TEXT_RADIO - (valueWidth / 2F), TEXT_RADIO * (float) (Math.sin(radians * i) * maxRadius), textPaint);
            }
            path.close();
            canvas.drawPath(path, overLayPaint);
        }
    }

    private void drawAuxiliaryLine(Canvas canvas) {
        maxRadius = NESTING_COUNT * POLYGON_RADIUS;
        path.reset();
        for (int i = 0; i < EDGE_COUNT; i++) {
            path.moveTo(0, 0);
            float x = (float) (Math.cos(radians * i) * maxRadius);
            float y = (float) (Math.sin(radians * i) * maxRadius);
            path.lineTo(x, y);
            canvas.drawPath(path, mPaint);
        }
    }

    private void drawSpiderNet(Canvas canvas) {
        for (int j = 0; j < NESTING_COUNT; j++) {
            drawPolygon(canvas, POLYGON_RADIUS * (1 + RADIO * j));
        }
    }

    /**
     * @param canvas
     * @param radius 多边形的半径
     */
    private void drawPolygon(Canvas canvas, float radius) {
        for (int i = 0; i < EDGE_COUNT; i++) {
            if (i == 0) {
                path.moveTo(radius, 0);
            } else {
                float x = (float) (Math.cos(radians * i) * radius);
                float y = (float) (Math.sin(radians * i) * radius);
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path, mPaint);
    }

    private class RadarData {
        private int value;
        private String valueName;

        public int getValue() {
            return value;
        }

        public RadarData setValue(int value) {
            this.value = value;
            return this;
        }

        public String getValueName() {
            return valueName;
        }

        public RadarData setValueName(String valueName) {
            this.valueName = valueName;
            return this;
        }
    }


}
