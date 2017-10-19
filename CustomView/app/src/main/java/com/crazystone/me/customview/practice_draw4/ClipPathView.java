package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class ClipPathView extends CustomView {

    Path path;
    Bitmap bitmap;

    public ClipPathView(Context context) {
        super(context);
    }

    public ClipPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClipPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {

        path = new Path();
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yellow_man);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        path.addCircle(100, 100, 50, Path.Direction.CW);

        canvas.clipPath(path);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);

    }


}
