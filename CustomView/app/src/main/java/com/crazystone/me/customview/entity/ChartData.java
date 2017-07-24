package com.crazystone.me.customview.entity;

/**
 * Created by crazy_stone on 17-7-11.
 */

public class ChartData implements IChartData {

    private float value;
    private String valueName;

    @Override
    public float getValue() {
        return this.value;
    }

    public ChartData setValue(float value) {
        this.value = value;
        return this;
    }

    @Override
    public String getValueName() {
        return this.valueName;
    }

    public ChartData setValueName(String valueName) {
        this.valueName = valueName;
        return this;
    }
}
