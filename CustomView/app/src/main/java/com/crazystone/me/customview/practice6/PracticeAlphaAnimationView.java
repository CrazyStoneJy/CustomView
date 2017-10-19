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

public class PracticeAlphaAnimationView extends RelativeLayout {

    ImageView imageView;
    Button btn;
    int index = 0;
    int DEFAULT_COUNT = 2;

    public PracticeAlphaAnimationView(Context context) {
        super(context);
    }

    public PracticeAlphaAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeAlphaAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        btn = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.animate();

        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (index) {
                    case 0:
                        imageView.animate().alpha(0.3F);
                        break;
                    case 1:
                        imageView.animate().alpha(0.8F);
                        break;
                    case 2:
                        imageView.animate().alpha(1);
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
