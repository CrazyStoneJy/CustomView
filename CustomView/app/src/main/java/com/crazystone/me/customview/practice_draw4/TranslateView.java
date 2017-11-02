package com.crazystone.me.customview.practice_draw4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Views;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class TranslateView extends CustomView {

    Bitmap bitmap;
    int bitmapX, bitmapY;

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
        bitmapX = bitmap.getWidth() / 2;
        bitmapY = bitmap.getHeight() / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Views.getColorInt(R.color.colorAccent));
//        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        //canvas 几何变化的顺序是逆序的 ,比如canvas.translate()  canvas.rotate() 表示先旋转再平移
        canvas.save();
        canvas.translate(300, 300);
        canvas.rotate(145, bitmapX, bitmapY);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.restore();

//        canvas.save();
//        canvas.translate(300,300);
//        canvas.drawBitmap(bitmap,0,0,mPaint);
//        canvas.restore();

//        canvas.save();
//        canvas.rotate(145, bitmapX, bitmapY);
//        canvas.translate(300, 300);
//        canvas.drawBitmap(bitmap, 0, 0, mPaint);
//        canvas.restore();

    }
}
