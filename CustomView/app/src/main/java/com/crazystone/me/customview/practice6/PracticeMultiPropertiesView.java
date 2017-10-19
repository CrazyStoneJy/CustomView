package com.crazystone.me.customview.practice6;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Utils;

/**
 * Created by crazy_stone on 17-10-16.
 */

public class PracticeMultiPropertiesView extends RelativeLayout {

    ImageView imageView;
    Button btn;
    int index = 0;
    int DEFAULT_COUNT = 2;
    TestEntity entity;

    public PracticeMultiPropertiesView(Context context) {
        super(context);
    }

    public PracticeMultiPropertiesView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeMultiPropertiesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        imageView = (ImageView) findViewById(R.id.imageView);
        btn = (Button) findViewById(R.id.animateBt);
        imageView.setScaleX(0);
        imageView.setScaleX(0);
        imageView.setAlpha(0F);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 0:
                        imageView.animate()
                                .alpha(1)
                                .translationY(Utils.dpToPixel(200))
                                .translationX(Utils.dpToPixel(200))
                                .rotationX(360)
                                .scaleX(1)
                                .scaleY(1)
                                .setDuration(1000);
                        break;
                    case 1:
                        imageView.setScaleX(0);
                        imageView.setScaleX(0);
                        imageView.setAlpha(0F);
                        imageView.setRotationX(0);
                        imageView.setTranslationX(0);
                        imageView.setTranslationY(0);
                        if (entity == null) {
                            entity = new TestEntity();
                        }
                        ObjectAnimator animator = ObjectAnimator.ofInt(entity, "x", 0, 450);
                        animator.setDuration(3000).setInterpolator(new LinearInterpolator());
                        animator.start();
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int value = (int) animation.getAnimatedValue();
                                Log.d("TAG", "value:" + value);
                            }
                        });
                        break;
                }
                index++;
                if (index == DEFAULT_COUNT) {
                    index = 0;
                }
            }
        });
    }

    private class TestEntity {
        int x;
        int y;

        public int getX() {
            return x;
        }

        public TestEntity setX(int x) {
            this.x = x;
            return this;
        }

        public int getY() {
            return y;
        }

        public TestEntity setY(int y) {
            this.y = y;
            return this;
        }
    }


}
