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

public class PracticeScaleAnimationView extends RelativeLayout {

    public static final int DEFAULT_COUNT = 6;
    Button btn;
    ImageView imageView;
    int index;

    public PracticeScaleAnimationView(Context context) {
        super(context);
    }

    public PracticeScaleAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeScaleAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        imageView = (ImageView) findViewById(R.id.imageView);
        btn = (Button) findViewById(R.id.animateBt);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 0:
                        imageView.animate().scaleX(2);
                        break;
                    case 1:
                        imageView.animate().scaleX(0.5F);
                        break;
                    case 2:
                        imageView.animate().scaleY(2);
                        break;
                    case 3:
                        imageView.animate().scaleY(0.5F);
                        break;
                    case 4:
                        imageView.animate().scaleX(2);
                        break;
                    case 5:
                        imageView.animate().scaleXBy(1.5F);
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
