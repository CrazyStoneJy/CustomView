package com.crazystone.me.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.crazystone.me.customview.view.calendar.CalendarView;

/**
 * Created by crazy_stone on 17-7-26.
 */

public class CalendarViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DayView dayView = new DayView(this);
//        dayView.setSelected(true);
//        dayView.setData(new DayEntity().setDayOfMonth(23).setLunarName("初一").setToday(false));
//        setContentView(dayView);
        setContentView(new CalendarView(this));
    }
}
