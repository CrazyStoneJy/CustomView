package com.crazystone.me.customview.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by crazy_stone on 17-10-31.
 */

public class TestBean implements Parcelable {
    private String name;
    private int key;

    protected TestBean(Parcel in) {
        name = in.readString();
        key = in.readInt();
    }

    public static final Creator<TestBean> CREATOR = new Creator<TestBean>() {
        @Override
        public TestBean createFromParcel(Parcel in) {
            return new TestBean(in);
        }

        @Override
        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(key);
    }
}
