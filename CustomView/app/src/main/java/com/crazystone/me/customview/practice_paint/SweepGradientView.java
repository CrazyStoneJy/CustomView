package com.crazystone.me.customview.practice_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Views;

/**
 * Created by crazy_stone on 17-7-20.
 */

public class SweepGradientView extends CustomView {
    Shader shader;

    public SweepGradientView(Context context) {
        super(context);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        shader = new SweepGradient(150, 150, Views.getColorInt(R.color.red), Views.getColorInt(R.color.blue));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(100, 200);
        mPaint.setShader(shader);
        canvas.drawCircle(150, 150, 150, mPaint);
    }
}
