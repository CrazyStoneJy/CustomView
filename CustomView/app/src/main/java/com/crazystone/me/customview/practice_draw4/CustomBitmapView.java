package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-8-11.
 */

public class CustomBitmapView extends CustomView {

    protected Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yellow_man);;

    public CustomBitmapView(Context context) {
        super(context);
    }

    public CustomBitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomBitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
    }
}
