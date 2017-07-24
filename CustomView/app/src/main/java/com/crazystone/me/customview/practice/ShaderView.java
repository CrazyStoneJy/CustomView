package com.crazystone.me.customview.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-7-17.
 */

public class ShaderView extends CustomView {

    Paint paint;
    Bitmap bitmap;
    Shader shader;

    public ShaderView(Context context) {
        super(context);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.city_dust);
        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        paint.setShader(shader);
//        canvas.drawCircle(0, 0, 200, paint);
        paint.setMaskFilter(new BlurMaskFilter(40, BlurMaskFilter.Blur.NORMAL));
        canvas.drawBitmap(bitmap,0,0, paint);
    }
}
