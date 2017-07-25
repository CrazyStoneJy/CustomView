package com.crazystone.me.customview.practice_draw_text;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.crazystone.me.customview.CustomView;
import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Views;

/**
 * Created by crazy_stone on 17-7-25.
 */

public class GetTextBoundsView extends CustomView {

    Rect rect;

    public GetTextBoundsView(Context context) {
        super(context);
    }

    public GetTextBoundsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GetTextBoundsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(50);
        String str = "HelloWorld";
        canvas.drawText(str, 100, 50, mPaint);
        mPaint.setColor(Views.getColorInt(R.color.red));

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.getTextBounds(str, 0, str.length(), rect);
        Log.d(GetTextBoundsView.class.getSimpleName(), "left:" + rect.left + ",top:" + rect.top + ",right:" + rect.right + ",bottom:" + rect.bottom);
        rect.left += 100;
        rect.top += 50;
        rect.right += 100;
        rect.bottom += 50;
        canvas.drawRect(rect, mPaint);
    }


}
