package com.crazystone.me.customview.view.calendar;

/**
 * Created by crazy_stone on 17-7-26.
 */

public class DayEntity implements IDayData {

    private int dayOfMonth;
    private String lunarName;
    private boolean isToday;
    private boolean inCurrentMonth;
    private int state;


    @Override
    public int getDayOfMoth() {
        return this.dayOfMonth;
    }

    @Override
    public String getLunarName() {
        return this.lunarName;
    }

    public DayEntity setLunarName(String lunarName) {
        this.lunarName = lunarName;
        return this;
    }

    @Override
    public boolean isToday() {
        return this.isToday;
    }

    public DayEntity setToday(boolean today) {
        isToday = today;
        return this;
    }


    @Override
    public boolean inCurrentMonth() {
        return this.inCurrentMonth;
    }

    @Override
    public int state() {
        return this.state;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public DayEntity setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
        return this;
    }

    public boolean isInCurrentMonth() {
        return inCurrentMonth;
    }

    public DayEntity setInCurrentMonth(boolean inCurrentMonth) {
        this.inCurrentMonth = inCurrentMonth;
        return this;
    }

    public DayEntity setState(int state) {
        this.state = state;
        return this;
    }
}
