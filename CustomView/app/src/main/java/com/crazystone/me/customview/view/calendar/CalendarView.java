package com.crazystone.me.customview.view.calendar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crazystone.me.customview.utils.Windows;

import java.util.Calendar;

/**
 * Created by crazy_stone on 17-7-26.
 */

public class CalendarView extends LinearLayout implements MonthView.ChangeMonthListener {


    MonthView monthView;
    TextView monthText;
    float touchX, touchY;

    public CalendarView(Context context) {
        super(context);
        init();
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        setOrientation(LinearLayout.VERTICAL);

        monthText = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        monthText.setGravity(Gravity.CENTER);
        monthText.setLayoutParams(lp);
        addView(monthText);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        monthText.setText(year + "年" + month + "月");

        LinearLayout linear = new LinearLayout(getContext());
        linear.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linear.setLayoutParams(llp);
        addView(linear);
        TextView lastText = new TextView(getContext());
        LinearLayout.LayoutParams lastLp = new LinearLayout.LayoutParams(0, 50, 1);
        lastText.setText("上一月");
        lastText.setLayoutParams(lastLp);
        lastText.setGravity(Gravity.CENTER);
        lastText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monthView != null) {
                    monthView.lastMonth();
                    monthText.setText(monthView.getCurrentYear() + "年" + (monthView.getCurrentMonth() + 1) + "月");
                }
            }
        });

        final TextView nextText = new TextView(getContext());
        LinearLayout.LayoutParams nextLp = new LinearLayout.LayoutParams(0, 50, 1);
        nextText.setGravity(Gravity.CENTER);
        nextText.setLayoutParams(nextLp);
        nextText.setText("下一月");
        nextText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (monthView != null) {
                    monthView.nextMonth();
                    monthText.setText(monthView.getCurrentYear() + "年" + (monthView.getCurrentMonth() + 1) + "月");
                }
            }
        });
        linear.addView(lastText);
        linear.addView(nextText);

        LinearLayout monthLinear = new LinearLayout(getContext());
        monthLinear.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams monthLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 80);
        monthLinear.setLayoutParams(monthLp);
        addView(monthLinear);
        int width = Windows.getScreenWidth(getContext());
        for (int i = 0; i < 7; i++) {
            TextView textView = new TextView(getContext());
            LinearLayout.LayoutParams textLp = new LinearLayout.LayoutParams(width / 7, ViewGroup.LayoutParams.WRAP_CONTENT);
            textLp.setMargins(0, 10, 0, 10);
            textView.setLayoutParams(textLp);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(10);
            textView.setText(Calendars.WEEKS[i]);
            monthLinear.addView(textView);
        }

        monthView = new MonthView(getContext());
        monthView.setChangeMonthListener(this);
        addView(monthView);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        Log.d(CalendarView.class.getSimpleName(), "x:" + event.getX() + ",y:" + event.getY());
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                touchX = event.getX();
                touchY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                float y = event.getY();
                float x = event.getX();
                if (x - touchX >= 50) {
                    if (monthView != null)
                        monthView.lastMonth();
                } else if (x - touchX <= -50) {
                    if (monthView != null)
                        monthView.nextMonth();
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }


        return true;
    }

    @Override
    public void onChangeMonth(int year, int month) {
        monthText.setText(year + "年" + (month + 1) + "月");
    }
}
