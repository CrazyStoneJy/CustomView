package com.crazystone.me.customview.view.calendar;

import android.text.TextUtils;
import android.util.Log;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by crazy_stone on 17-7-26.
 */

public class Calendars {

    public static final String[] WEEKS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    public static final String[] MONTHS = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "冬月", "腊月"};
    public static final String LEAP = "润";
    //    private final static String[] SOLAR_FESTIVAL = {
//            "0101 元旦", "0214 情人节", "0308 妇女节", "0312 植树节",
//            "0315 消费者权益日", "0401 愚人节", "0501 劳动节", "0504 青年节",
//            "0509 郝维节", "0512 护士节", "0601 儿童节", "0701 建党节",
//            "0801 建军节", "0808 父亲节", "0816 燕衔泥节",
//            "0910 教师节", "0928 孔子诞辰", "1001 国庆节", "1006 老人节",
//            "1024 联合国日", "1111 光棍节",
//            "1225 圣诞节",
//    };
    public static Map<String, String> solarFestival=new HashMap<>();

    static {
        solarFestival.put("0101", "元旦");
        solarFestival.put("0214", "情人节");
        solarFestival.put("0308", "妇女节");
        solarFestival.put("0312", "植树节");
        solarFestival.put("0315", "消费者权益日");
        solarFestival.put("0401", "愚人节");
        solarFestival.put("0501", "劳动节");
        solarFestival.put("0504", "青年节");
        solarFestival.put("0509", "郝维节");
        solarFestival.put("0512", "护士节");
        solarFestival.put("0601", "儿童节");
        solarFestival.put("0701", "建党节");
        solarFestival.put("0801", "建军节");
        solarFestival.put("0808", "父亲节");
        solarFestival.put("0816", "燕衔泥节");
        solarFestival.put("0910", "教师节");
        solarFestival.put("0928", "孔子诞辰");
        solarFestival.put("1001", "国庆节");
        solarFestival.put("1006", "老人节");
        solarFestival.put("1024", "联合国日");
        solarFestival.put("1111", "光棍节");
        solarFestival.put("1225", "圣诞节");
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    /**
     * @param year
     * @param month
     * @return 日：1		一：2		二：3		三：4		四：5		五：6		六：7
     */
    public static int getFirstDayOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static boolean isToday(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        return (year == calendar.get(Calendar.YEAR) && month == calendar.get(Calendar.MONTH) && day == calendar.get(Calendar.DATE));
    }

    /**
     * @param year  the year of lunar
     * @param month the month of lunar
     * @param day   the day of lunar
     * @return lunar chinese string
     */
    public static String getLunarStringByLunarDate(int year, int month, int day) {
        String lunarString = (day == 1 ? MONTHS[month - 1] : Lunars.getLunarDayString(day));
        String holidayString = Lunars.getLunarHoliday(year, month, day);
        return !TextUtils.isEmpty(holidayString) ? holidayString : lunarString;
    }


    public static String getLunarString(int year, int month, int day) {
        Lunars.Lunar lunar = Lunars.solarToLunar(new Lunars.Solar(year, month, day));
        Log.d(Calendars.class.getSimpleName(), "lunarYear:" + lunar.lunarYear + ",lunarMonth:" + lunar.lunarMonth + ",lunarDay:" + lunar.lunarDay);
        return getLunarStringByLunarDate(lunar.lunarYear, lunar.lunarMonth, lunar.lunarDay);
    }

    public static String getSolarLunarString(int year, int month, int day) {
        String montDayStr = getMothDayString(month, day);
        String festival = solarFestival.get(montDayStr);
        return TextUtils.isEmpty(festival) ? getLunarString(year, month, day) : festival;
    }

    private static String getMothDayString(int month, int day) {
        StringBuilder sb = new StringBuilder();
        return sb.append(month / 10 >= 1 ? month : "0" + month).append(day / 10 >= 1 ? day : "0" + day).toString();
    }


}
