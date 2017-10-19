package com.crazystone.me.customview.practice6;

import android.view.animation.Interpolator;

/**
 * Created by crazy_stone on 17-10-17.
 */

public class CustomInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        return input * input;
    }
}
