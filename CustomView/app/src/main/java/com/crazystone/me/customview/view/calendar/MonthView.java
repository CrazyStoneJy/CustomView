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

import com.crazystone.me.customview.utils.Windows;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by crazy_stone on 17-7-26.
 */

public class MonthView extends LinearLayout {

    List<IDayData> dayList;
    int[] COMMON_YEAR = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int[] LEAP_YEAR = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int currentYear, currentMonth, currentDay;
    DayView[] monthOfDayView;
    ChangeMonthListener changeMonthListener;
    private int selectedIndex;

    public MonthView(Context context) {
        super(context);
        init();
    }

    public MonthView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MonthView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        initMothData();
    }

    private void addDayView() {
        removeAllViews();
        LinearLayout rowLinear = null;
        monthOfDayView = new DayView[dayList.size()];
        for (int i = 0; i < dayList.size(); i++) {
            if (i % 7 == 0) {
                rowLinear = new LinearLayout(getContext());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Windows.getScreenWidth(getContext()) / 7);
                rowLinear.setLayoutParams(lp);
                rowLinear.setGravity(Gravity.CENTER);
                rowLinear.setOrientation(LinearLayout.HORIZONTAL);
                addView(rowLinear);
            }
            if (rowLinear != null) {
                final DayView dayView = new DayView(getContext());
                monthOfDayView[i] = dayView;
                if (dayList.get(i).isToday()) {
                    dayView.setSelected(true);
                    selectedIndex = i;
                }
                dayView.setData(dayList.get(i));
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(Windows.getScreenWidth(getContext()) / 7, Windows.getScreenWidth(getContext()) / 7);
                dayView.setLayoutParams(lp);
                rowLinear.addView(dayView);
                final int pos = i;
                dayView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        dayView.toggle();
                        selectDay(pos);
                    }
                });
            }
        }
    }

    private void selectDay(int pos) {
        if (dayList.get(pos).state() == IDayData.CURRENT) {
            monthOfDayView[selectedIndex].setSelected(false);
            monthOfDayView[pos].setSelected(true);
            selectedIndex = pos;
        } else if (dayList.get(pos).state() == IDayData.LAST) {
            lastMonth();
            if (changeMonthListener != null) {
                changeMonthListener.onChangeMonth(currentYear, currentMonth);
            }
        } else if (dayList.get(pos).state() == IDayData.NEXT) {
            nextMonth();
            if (changeMonthListener != null) {
                changeMonthListener.onChangeMonth(currentYear, currentMonth);
            }
        }
    }

    private void setMonthData(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        currentYear = calendar.get(Calendar.YEAR);
        currentMonth = calendar.get(Calendar.MONTH);
        currentDay = calendar.get(Calendar.DATE);
        dayList = new ArrayList<>();

        getLastMonthData(dayList);
        getCurrentData(dayList);
        getNextMonthData(dayList);

        addDayView();
    }

    private void initMothData() {
        Calendar calendar = Calendar.getInstance();
        setMonthData(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
    }

    private void getLastMonthData(List<IDayData> list) {
        int lastMonth, lastYear;
        if (currentMonth == 0) {
            lastMonth = 11;
            lastYear = currentYear - 1;
        } else {
            lastMonth = currentMonth - 1;
            lastYear = currentYear;
        }

        //上个月的日数
        int daysOfLastMonth = getDaysOfMonth(lastYear, lastMonth);
        // 当前月份第一个天是第几周
        int weekOfCurrentMonth = Calendars.getFirstDayOfMonth(currentYear, currentMonth);

        for (int i = 0; i < weekOfCurrentMonth - 1; i++) {
            int day = daysOfLastMonth - weekOfCurrentMonth + i + 2;
            list.add(new DayEntity().setToday(false).setState(IDayData.LAST).setDayOfMonth(day).setInCurrentMonth(false).setLunarName(Calendars.getSolarLunarString(lastYear, lastMonth + 1, day)));
        }
    }

    private void getCurrentData(List<IDayData> list) {
        int daysOfLastMonth = getDaysOfMonth(currentYear, currentMonth);
        for (int i = 1; i <= daysOfLastMonth; i++) {
            list.add(new DayEntity().setToday(Calendars.isToday(currentYear, currentMonth, i))
                    .setState(IDayData.CURRENT).setDayOfMonth(i).setInCurrentMonth(true)
                    .setLunarName(Calendars.getSolarLunarString(currentYear, currentMonth + 1, i)));
        }
    }

    private void getNextMonthData(List<IDayData> list) {
        if (list.size() % 7 == 0) return;

        int lastRow = list.size() / 7 + 1;
        int rest = 7 * lastRow - list.size();
        int nextYear, nextMonth;
        if (currentMonth == 11) {
            nextYear = currentYear + 1;
            nextMonth = 0;
        } else {
            nextYear = currentYear;
            nextMonth = currentMonth + 1;
        }

        for (int i = 1; i <= rest; i++) {
            list.add(new DayEntity().setToday(false).setState(IDayData.NEXT).setDayOfMonth(i)
                    .setInCurrentMonth(false).setLunarName(Calendars.getSolarLunarString(nextYear, nextMonth + 1, i)));
        }
    }

    /**
     * 计算xxxx年xx月有多少天
     *
     * @return
     */
    private int getDaysOfMonth(int year, int month) {
        //润年
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return LEAP_YEAR[month];
        } else {
            return COMMON_YEAR[month];
        }
    }

    public void lastMonth() {
        if (currentMonth == 0) {
            currentMonth = 11;
            currentYear -= 1;
        } else {
            currentMonth -= 1;
        }
        setMonthData(currentYear, currentMonth);
    }

    public void nextMonth() {
        if (currentMonth == 11) {
            currentMonth = 0;
            currentYear += 1;
        } else {
            currentMonth += 1;
        }
        setMonthData(currentYear, currentMonth);
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public MonthView setChangeMonthListener(ChangeMonthListener changeMonthListener) {
        this.changeMonthListener = changeMonthListener;
        return this;
    }


    public interface ChangeMonthListener {

        void onChangeMonth(int year, int month);
    }
}
