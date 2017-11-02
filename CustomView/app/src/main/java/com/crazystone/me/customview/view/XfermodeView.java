package com.crazystone.me.customview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-5-26.
 */

public class XfermodeView extends View {

    private static final int DEFAUT_SIZE = 100;
    int size;
    int circleRadius, squareSize;
    private Paint paint, textPaint;
    private ModeInfo info;
    private PorterDuffXfermode porterDuffXfermode;
    private Bitmap circleBitmap, squareBitmap;

    {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    public XfermodeView(Context context) {
        super(context);
        init();
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.STROKE);
        circleBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_red_cirlce);
        squareBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.img_blue_rect);
    }

    private Bitmap getCircleBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        paint.setColor(getContext().getResources().getColor(R.color.orange));
        canvas.drawCircle(circleRadius, circleRadius, circleRadius, paint);
        return bitmap;
    }

    private Bitmap getSquareBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        paint.setColor(getContext().getResources().getColor(R.color.blue));
        float left = circleRadius;
        float top = circleRadius;
        float right = left + squareSize;
        float bottom = top + squareSize;
        canvas.drawRect(left, top, right, bottom, paint);
        return bitmap;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int width = widthMode == MeasureSpec.EXACTLY ? widthSize : DEFAUT_SIZE;
        int height = heightMode == MeasureSpec.EXACTLY ? heightSize : DEFAUT_SIZE;
        size = Math.max(width, height);
        float even = (size / 4);
        circleRadius = (int) ((float) (even * 3) / 2);
        squareSize = (int) (even * 2);

//        circleBitmap = getCircleBitmap();
//        squareBitmap = getSquareBitmap();

        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.reset();
        textPaint.reset();
        if (info == null) return;

        //创建一个图层，在图层上演示图形混合后的效果 (离屏缓冲)
        int saveCount = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(squareBitmap,0,0,paint);
        paint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(circleBitmap,0,0,paint);
        canvas.restoreToCount(saveCount);

    }

    private void drawText(Canvas canvas) {
        float textLen = textPaint.measureText(info.getName());
        canvas.drawText(info.getName(), size / 2 - textLen / 2, squareSize + 20, textPaint);
    }

    private void drawOutterRect(Canvas canvas) {
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(5);
        textPaint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, size, size, textPaint);
    }

    private void drawRect(Canvas canvas) {
        paint.setColor(getContext().getResources().getColor(R.color.blue));
        float left = circleRadius;
        float top = circleRadius;
        float right = left + squareSize;
        float bottom = top + squareSize;
        canvas.drawRect(left, top, right, bottom, paint);
    }

    private void drawCircle(Canvas canvas) {

        paint.setColor(getContext().getResources().getColor(R.color.orange));
        canvas.drawCircle(circleRadius, circleRadius, circleRadius, paint);
    }

    public XfermodeView setInfo(ModeInfo info) {
        this.info = info;
        porterDuffXfermode = new PorterDuffXfermode(info.getMode());
        invalidate();
        return this;
    }

    public static class ModeInfo {
        private PorterDuff.Mode mode;
        private String name;

        public ModeInfo() {

        }

        public PorterDuff.Mode getMode() {
            return mode;
        }

        public ModeInfo setMode(PorterDuff.Mode mode) {
            this.mode = mode;
            return this;
        }

        public String getName() {
            return name;
        }

        public ModeInfo setName(String name) {
            this.name = name;
            return this;
        }
    }

}
