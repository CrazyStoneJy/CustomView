package com.crazystone.me.customview.utils;

/**
 * 4x5的矩阵表示RGBA
 * Created by crazy_stone on 17-7-18.
 */

public class ColorMatrixs {

    public static final float[] HALF = {
            0.5F, 0, 0, 0, 0,
            0, 0.5F, 0, 0, 0,
            0, 0, 0.5F, 0, 0,
            0, 0, 0, 1, 0,
    };
    public static float[] PRIME = {
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0,};

    public static float[] GRAY = {
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0.33F, 0.59F, 0.11F, 0, 0,
            0, 0, 0, 1, 0,
    };

    public static float[] REVERSE = {
            -1, 0, 0, 1, 1,
            0, -1, 0, 1, 1,
            0, 0, -1, 1, 1,
            0, 0, 0, 1, 0,
    };

    public static float[] OLD = {
            0.393F, 0.769F, 0.189F, 0, 0,
            0.349F, 0.686F, 0.168F, 0, 0,
            0.272F, 0.534F, 0.131F, 0, 0,
            0, 0, 0, 1, 0,
    };

    public static float[] BLACK_WHITE = {
            1.5F, 1.5F, 1.5F, 0, -1,
            1.5F, 1.5F, 1.5F, 0, -1,
            1.5F, 1.5F, 1.5F, 0, -1,
            0, 0, 0, 1, 0,
    };

}
