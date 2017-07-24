package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Views;

/**
 * Created by crazy_stone on 17-7-20.
 */

public class RadialGradientView extends CustomView {
    Shader shader;

    public RadialGradientView(Context context) {
        super(context);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        shader = new RadialGradient(150, 150, 100, Views.getColorInt(R.color.red), Views.getColorInt(R.color.blue), Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setShader(shader);
        canvas.translate(100, 200);
        canvas.drawCircle(150, 150, 150, mPaint);
    }
}


