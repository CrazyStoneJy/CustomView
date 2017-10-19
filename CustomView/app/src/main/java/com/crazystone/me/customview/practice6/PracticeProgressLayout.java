package com.crazystone.me.customview.practice6;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-10-17.
 */

public class PracticeProgressLayout extends RelativeLayout {

    PracticeProgressView progressView;
    Button btn;

    public PracticeProgressLayout(Context context) {
        super(context);
    }

    public PracticeProgressLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeProgressLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        btn = (Button) findViewById(R.id.animateBt);
        progressView = (PracticeProgressView) findViewById(R.id.progress_view);
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                progressView.start();
            }
        });
    }
}
