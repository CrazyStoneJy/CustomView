package com.crazystone.me.customview.view.calendar;

/**
 * Created by crazy_stone on 17-7-26.
 */

public interface IDayData {

    int LAST = 0X1;
    int CURRENT = 0X2;
    int NEXT = 0X3;

    int getDayOfMoth();

    String getLunarName();

    boolean isToday();


    boolean inCurrentMonth();

    /* 当前点击的是在上个月,当前月,还是下个月 */
    int state();


}
