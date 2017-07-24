package com.crazystone.me.customview.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by crazy_stone on 17-7-17.
 */

public class Coordinates {

    private static final int DEFAULT_ARROW_WIDTH = 40;
    private static int width, height;

    static {
        width = Windows.getScreenWidth(Contexts.getContext());
        height = Windows.getScreenHeight(Contexts.getContext());
    }

    public static void drawCoordination(Canvas canvas, Paint paint, int arrowLength) {
        drawXAxis(canvas, arrowLength, paint);
        drawYAxis(canvas, arrowLength, paint);
    }

    public static void drawCoordination(Canvas canvas, Paint paint) {
        drawXAxis(canvas, DEFAULT_ARROW_WIDTH, paint);
        drawYAxis(canvas, DEFAULT_ARROW_WIDTH, paint);
    }

    public static void drawCoordination(Canvas canvas) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        drawXAxis(canvas, DEFAULT_ARROW_WIDTH, paint);
        drawYAxis(canvas, DEFAULT_ARROW_WIDTH, paint);
    }

    private static void drawXAxis(Canvas canvas, int arrowLength, Paint paint) {
        //x axis
        canvas.drawLine(-width / 2, 0, width / 2, 0, paint);
        canvas.drawLine((float) (width / 2 - arrowLength * Math.sqrt(3) / 2), -arrowLength / 2, width / 2, 0, paint);
        canvas.drawLine((float) (width / 2 - arrowLength * Math.sqrt(3) / 2), arrowLength / 2, width / 2, 0, paint);
    }

    private static void drawYAxis(Canvas canvas, int arrowLength, Paint paint) {
        canvas.drawLine(0, -height / 2, 0, height / 2, paint);
        canvas.drawLine(-arrowLength / 2, (float) (arrowLength * Math.sqrt(3) / 2 - height / 2), 0, -height / 2, paint);
        canvas.drawLine(0, -height / 2, arrowLength / 2, (float) (arrowLength * Math.sqrt(3) / 2 - height / 2), paint);
    }

}
