package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class TranslateView extends CustomView {

    Bitmap bitmap;

    public TranslateView(Context context) {
        super(context);
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yellow_man);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.save();
        canvas.translate(300, 300);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.restore();

    }
}
