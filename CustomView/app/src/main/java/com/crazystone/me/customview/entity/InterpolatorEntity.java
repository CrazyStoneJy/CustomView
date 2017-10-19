package com.crazystone.me.customview.entity;

import android.view.animation.Interpolator;

/**
 * Created by crazy_stone on 17-10-17.
 */

public class InterpolatorEntity {

    private String name;
    private Interpolator interpolator;

    public String getName() {
        return name;
    }

    public InterpolatorEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Interpolator getInterpolator() {
        return interpolator;
    }

    public InterpolatorEntity setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
        return this;
    }
}
