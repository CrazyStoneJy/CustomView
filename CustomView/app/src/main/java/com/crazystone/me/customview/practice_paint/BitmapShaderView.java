package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Views;

/**
 * Created by crazy_stone on 17-7-20.
 */

public class BitmapShaderView extends CustomView {

    BitmapShader shader;

    public BitmapShaderView(Context context) {
        super(context);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yellow_man);
        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Views.getColorInt(R.color.colorAccent));
        canvas.translate(100, 200);
        mPaint.setShader(shader);
        canvas.drawCircle(150, 150, 150, mPaint);
        canvas.translate(400, 500);
//        mPaint.setColor(Views.getColorInt(R.color.red));
        canvas.drawRect(0, 0, 300, 200, mPaint);
    }
}
