package com.crazystone.me.customview;

import android.app.Application;

/**
 * Created by crazy_stone on 17-7-17.
 */

public class CustomApplication extends Application {

    private static CustomApplication thiz;

    public CustomApplication() {
        thiz = this;
    }

    public static CustomApplication getApplication() {
        return thiz;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
