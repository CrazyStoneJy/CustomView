package com.crazystone.me.customview.view.calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Views;
import com.crazystone.me.customview.utils.Windows;

/**
 * Created by crazy_stone on 17-7-26.
 */

public class DayView extends View {

    int width, evenWidth;
    Paint mPaint;
    boolean isSelected;
    private IDayData data;

    public DayView(Context context) {
        super(context);
        init();
    }

    public DayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = Windows.getScreenWidth(getContext());
        evenWidth = width / 7;
        setMeasuredDimension(evenWidth, evenWidth);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (data == null) return;

        drawSelectedCircle(canvas);
        drawDayOfMoth(canvas);
        drawLunarName(canvas);
    }

    private void drawSelectedCircle(Canvas canvas) {
        mPaint.setColor(Views.getColorInt(R.color.blue));
        if (!data.isToday() && !isSelected) return;
        mPaint.setStyle(isSelected ? Paint.Style.FILL : Paint.Style.STROKE);
        canvas.drawCircle(evenWidth / 2F, evenWidth / 2F, evenWidth / 2F, mPaint);
    }

    private void drawLunarName(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(30);
        float lunarNameWidth = mPaint.measureText(data.getLunarName() == null ? "" : data.getLunarName());
        boolean inCurrentMonth = data.inCurrentMonth();
        mPaint.setColor(isSelected ? Color.WHITE : (inCurrentMonth ? Color.BLACK : Color.GRAY));
        Log.d(DayView.class.getSimpleName(), "lunarName:" + lunarNameWidth);
        canvas.drawText(String.valueOf(data.getLunarName()), (evenWidth - lunarNameWidth) / 2F, evenWidth / 2F + mPaint.getFontSpacing(), mPaint);
    }

    private void drawDayOfMoth(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50);
        float dayOfMonthWidth = mPaint.measureText(String.valueOf(data.getDayOfMoth()));
        Log.d(DayView.class.getSimpleName(), "dayOfMonth:" + dayOfMonthWidth);
        boolean inCurrentMonth = data.inCurrentMonth();
        mPaint.setColor(isSelected ? Color.WHITE : (inCurrentMonth ? Color.BLACK : Color.GRAY));
        canvas.drawText(String.valueOf(data.getDayOfMoth()), (evenWidth - dayOfMonthWidth) / 2F, evenWidth / 2F, mPaint);
    }

    public void setData(IDayData data) {
        this.data = data;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        invalidate();
    }

    public void toggle() {
        this.isSelected = !this.isSelected;
        invalidate();
    }


}
