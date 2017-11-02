package com.crazystone.me.customview.callforpaper;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Utils;


/**
 * 即刻的点赞view练习
 * 效果分析:从gif动画分析可看出,每个数字的动画是独立的,所以应该给每个数字设置一个动画效果,
 * 需要将数字做分离,例如12359,分为 1 2 3 5 9 五个部分考虑,因此该view最主要的问题是按位拆分数字
 *
 * 扩展练习:可以将这个增加减少的numberView分离出来,做一个自增的一个数字View.
 *
 * Created by crazy_stone on 17-10-14.
 */
public class JiKeAgreeView extends View {

    /* 动画时长 */
    private static final int DEFAULT_DURATION = 500;
    private static final String TAG = JiKeAgreeView.class.getSimpleName();
    Paint paint;
    int width, height;
    int centerX, centerY;
    int yAxis = 0;
    int changedDigit = 0;
    Bitmap unselectedBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_unselected);
    Bitmap selectBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_selected);
    Bitmap shiningBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_messages_like_selected_shining);
    /* 是否是点赞状态 */
    boolean isActive = false;
    float bitmapHeight = 0;
    float bitmapWidth = 0;
    float shingBitmapHeight = 0;
    AnimatorSet animatorSet = new AnimatorSet();
    AgreeCallback callback;
    /* 传进来的实际的数字 */
    private int showingNumber = 999;
    /* 纵坐标的索引值 */
    private int yAxisIndex = 0;
    /* 图片的缩放比例 */
    private float bitmapScale = 1;

    public JiKeAgreeView(Context context) {
        super(context);
        init();
    }

    public JiKeAgreeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JiKeAgreeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(width, height);
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setTextSize(Utils.dpToPixel(20));
        width = (int) Utils.dpToPixel(80);
        height = (int) Utils.dpToPixel(40);
        yAxis = 0;
        bitmapHeight = selectBitmap.getHeight();
        bitmapWidth = selectBitmap.getWidth();
        shingBitmapHeight = shiningBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        centerX = (width / 2);
        centerY = (height / 2);

        drawPic(canvas);
        drawNumber(canvas);

    }

    private void drawPic(Canvas canvas) {
        canvas.save();
        canvas.scale(bitmapScale, bitmapScale);
        if (isActive) {
            canvas.drawBitmap(shiningBitmap, 0, centerY - bitmapHeight / 2 - shingBitmapHeight / 2, paint);
        }
        canvas.drawBitmap(isActive ? selectBitmap : unselectedBitmap, 0, centerY - bitmapHeight / 2, paint);
        canvas.restore();
    }

    private void drawNumber(Canvas canvas) {
        drawShowingNumber(canvas, showingNumber - 1, -1);
        drawShowingNumber(canvas, showingNumber, 0);
        drawShowingNumber(canvas, showingNumber + 1, 1);
    }

    private void drawShowingNumber(Canvas canvas, int number, int type) {
        int digit = calPositiveDigit(number);
        int[] numbers = splitNumber(number);
        for (int i = 0; i < digit; i++) {
            float y = 0;
            if (digit - changedDigit <= i) {
                y = centerY - (paint.descent() + paint.ascent()) / 2 + height * type + yAxis;
            } else {
                y = centerY - (paint.descent() + paint.ascent()) / 2 + height * type;
            }
            canvas.drawText(String.valueOf(numbers[i]), bitmapWidth + Utils.dpToPixel(3) + Utils.dpToPixel(10) * i, y, paint);
        }
    }

    public int getYAxis() {
        return yAxis;
    }

    public JiKeAgreeView setYAxis(int yAxis) {
        this.yAxis = yAxis;
        invalidate();
        return this;
    }

    /**
     * 图片大小的缩放动画
     *
     * @return
     */
    private ObjectAnimator bitmapScaleAnimator() {
        Keyframe keyframe1 = Keyframe.ofFloat(0, 1);
        Keyframe keyframe2 = Keyframe.ofFloat(0.5F, 1.1F);
        Keyframe keyframe3 = Keyframe.ofFloat(1F, 1F);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("bitmapScale", keyframe1, keyframe2, keyframe3);
        ObjectAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(this, holder);
        scaleAnimator.setDuration(DEFAULT_DURATION);
        return scaleAnimator;
    }

    public float getBitmapScale() {
        return bitmapScale;
    }

    public JiKeAgreeView setBitmapScale(float bitmapScale) {
        this.bitmapScale = bitmapScale;
        return this;
    }

    /**
     * 计算number改变时所影响的进数,例如12359变为12360,改变的进数为2
     *
     * @return 改变的进数
     */
    private int changedDigit() {
        int tempNumber = showingNumber + 1;
        int count = 1;
        while (tempNumber % 10 == 0) {
            count++;
            tempNumber /= 10;
        }
        return count;
    }

    public void change() {
        Log.d(TAG, "isActive:" + isActive);
        changedDigit = changedDigit();
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofInt(this, "yAxis", yAxisIndex, isActive ? (yAxisIndex += height) : (yAxisIndex -= height));
        animator.setDuration(DEFAULT_DURATION);
        animatorSet.playTogether(animator, bitmapScaleAnimator());
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isActive = !isActive;
                if (callback != null) {
                    callback.agree(isActive);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    public JiKeAgreeView setShowingNumber(int showingNumber) {
        this.showingNumber = showingNumber;
        return this;
    }

    /**
     * 计算正整数的有几位,例如12359为5位
     *
     * @param num 要计算的数字
     * @return 正整数的位数
     */
    public int calPositiveDigit(int num) {
        int digit = 0;
        int tempNum = num;
        while (tempNum > 0) {
            tempNum /= 10;
            digit++;
        }
        return digit;
    }

    /**
     * 将数字分为每一位的数字,例如12359分为 [1,2,3,5,9]
     *
     * @param num
     * @return
     */
    public int[] splitNumber(int num) {
        int size = calPositiveDigit(num);
        int[] numbers = new int[size];
        int tempNum = num;
        int i = 0;
        while (tempNum > 0) {
            numbers[size - 1 - i] = tempNum % 10;
            tempNum /= 10;
            i++;
        }
        return numbers;
    }

    public boolean isActive() {
        return isActive;
    }

    public JiKeAgreeView setActive(boolean active) {
        isActive = active;
        return this;
    }

    public JiKeAgreeView toggle() {
        change();
        return this;
    }

    public void setCallback(AgreeCallback callback) {
        this.callback = callback;
    }


    public interface AgreeCallback {
        void agree(boolean isAgree);
    }


}
