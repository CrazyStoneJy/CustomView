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
import com.crazystone.me.customview.utils.Views;

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
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman_logo);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        BitmapShader logoShader = new BitmapShader(logoBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        composeShader = new ComposeShader(bitmapShader, logoShader, PorterDuff.Mode.SRC_OVER);
        mPaint.setShader(composeShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Views.getColorInt(R.color.colorAccent));
        canvas.drawCircle(200, 200, 200, mPaint);

//        canvas.translate(300, 300);
//
//        mPaint.setColor(Views.getColorInt(R.color.white));
//        canvas.drawCircle(0, 0, 300, mPaint);
    }
}
