package com.crazystone.me.customview.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

/**
 * Created by crazy_stone on 17-7-17.
 */

public abstract class LeakHandler<T> extends Handler {

    Reference<T> ref = null;

    public LeakHandler(T t) {
        ref = new SoftReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        T t = getRef();
        if (t == null) {
            removeCallbacksAndMessages(null);
        } else {
            handleMessage(msg, t);
        }
    }

    protected abstract void handleMessage(Message message, T t);

    public T getRef() {
        if (ref != null) {
            return ref.get();
        }
        return null;
    }


}
