package com.crazystone.me.customview.utils;

import android.content.Context;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * Created by crazy_stone on 17-5-20.
 */

public class Windows {

    /**
     * 获取屏幕的宽高，会返回point，x代表屏幕的宽度，y代表屏幕的高度 (d单位均为像素px)
     *
     * @return
     */
    public static Point getScreen(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        return point;
    }

    public static int getScreenWidth(Context context) {
        return getScreen(context).x;
    }

    public static int getScreenHeight(Context context) {
        return getScreen(context).y;
    }
}
