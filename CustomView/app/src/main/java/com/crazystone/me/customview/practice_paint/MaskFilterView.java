package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-7-20.
 */

public class MaskFilterView extends CustomView {

    MaskFilter[] maskFilters;
    Bitmap bitmap;

    public MaskFilterView(Context context) {
        super(context);
    }

    public MaskFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MaskFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        maskFilters = new MaskFilter[4];
        maskFilters[0] = new BlurMaskFilter(20, BlurMaskFilter.Blur.NORMAL);
        maskFilters[1] = new BlurMaskFilter(20, BlurMaskFilter.Blur.INNER);
        maskFilters[2] = new BlurMaskFilter(20, BlurMaskFilter.Blur.SOLID);
        maskFilters[3] = new EmbossMaskFilter(new float[]{0, 1, 1}, 0.2f, 8, 10);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(50,0);
        for (int i = 0; i < 4; i++) {
            canvas.save();
            canvas.translate(0, 400 * i);
            mPaint.setMaskFilter(maskFilters[i]);
            canvas.drawBitmap(bitmap, 0, 0, mPaint);
            canvas.restore();
        }
    }
}
