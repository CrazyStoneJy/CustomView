package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class CameraRotateView extends CustomBitmapView {

    Camera camera;
    int bitmapX = bitmap.getWidth() / 2;
    int bitmapY = bitmap.getHeight() / 2;


    public CameraRotateView(Context context) {
        super(context);
    }

    public CameraRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CameraRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void init() {
        camera = new Camera();


        //        bitmapX =
//                bitmapY = bitmap.getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        camera.save();
        camera.rotateX(45);
        canvas.translate(bitmapX, bitmapY);
        camera.applyToCanvas(canvas);
        canvas.translate(-bitmapX, -bitmapY);
        camera.restore();

        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.restore();
        canvas.translate(100, 0);

    }

}
