package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-7-20.
 */

public class ComposeShaderView extends CustomView {

    ComposeShader composeShader;

    public ComposeShaderView(Context context) {
        super(context);
    }

    public ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.yellow_man);
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo1);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        BitmapShader logoShader = new BitmapShader(logoBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        composeShader = new ComposeShader(bitmapShader, logoShader, PorterDuff.Mode.SRC_OVER);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPaint.setShader(composeShader);
        canvas.drawCircle(500, 500, 500, mPaint);
    }
}
