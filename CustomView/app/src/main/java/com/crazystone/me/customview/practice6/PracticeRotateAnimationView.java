package com.crazystone.me.customview.practice6;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-10-16.
 */

public class PracticeRotateAnimationView extends RelativeLayout {

    Button btn;
    ImageView imageView;
    int index;
    int DEFAULT_COUNT = 4;

    public PracticeRotateAnimationView(Context context) {
        super(context);
    }

    public PracticeRotateAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeRotateAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        btn = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 0:
//                        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "rotationX", 360);
//                        animator.setDuration(2000);
//                        animator.start();
                        imageView.animate().setDuration(2000).rotationX(360);
                        break;
                    case 1:
                        imageView.animate().setDuration(2000).rotationXBy(180);
                        break;
                    case 2:
                        imageView.animate().setDuration(2000).rotationY(360);
                        break;
                    case 3:
                        imageView.animate().setDuration(2000).rotationYBy(180);
                        break;
                }
                index++;
                if (index == DEFAULT_COUNT) {
                    index = 0;
                }
            }
        });
    }
}
