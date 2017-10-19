package com.crazystone.me.customview.utils;

import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

/**
 * Created by crazy_stone on 17-10-16.
 */

public class Utils {
    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }

    public static boolean api(int sdkVersion) {
        return Build.VERSION.SDK_INT > sdkVersion;
    }

}
