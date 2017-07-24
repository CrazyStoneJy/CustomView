package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;

/**
 * 需要练习
 * Created by crazy_stone on 17-7-20.
 */

public class XfermodeView extends CustomView {

    Bitmap yellowManBitmap, logoBitmap;
    PorterDuffXfermode mode_src_in, mode_dst_over;

    public XfermodeView(Context context) {
        super(context);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        yellowManBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yellow_man);
        logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo1);
        mode_src_in = new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER);
        mode_dst_over = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(yellowManBitmap, 0, 0, mPaint);
        mPaint.setXfermode(mode_src_in);
        canvas.drawBitmap(logoBitmap, 0, 0, mPaint);
        mPaint.setXfermode(null);

        canvas.translate(300, 0);

        canvas.drawBitmap(yellowManBitmap, 0, 0, mPaint);
        mPaint.setXfermode(mode_dst_over);
        canvas.drawBitmap(logoBitmap, 0, 0, mPaint);
        canvas.restoreToCount(saved);

    }
}
