package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class ClipRectView extends CustomView {

    Rect rect;
    Bitmap bitmap;

    public ClipRectView(Context context) {
        super(context);
    }

    public ClipRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClipRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        rect = new Rect();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yellow_man);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        rect.set(0, 0, 200, 200);
        canvas.clipRect(rect);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }


}
