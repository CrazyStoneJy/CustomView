package com.crazystone.me.customview.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

/**
 * Created by crazy_stone on 17-7-10.
 */

public class Views {

    public static int getColorInt(Context context, int colorRes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getResources().getColor(colorRes, null);
        }
        return context.getResources().getColor(colorRes);
    }

    public static
    @ColorInt
    int getColorInt(@ColorRes int colorRes) {
        return getColorInt(Contexts.getContext(), colorRes);
    }

    public static float px2dp() {
//        Contexts.getContext();
        return 0;
    }


}
