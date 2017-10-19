package com.crazystone.me.customview.callforpaper;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

import static com.crazystone.me.customview.R.id.view_wheel;
import static com.crazystone.me.customview.R.id.view_wheel2;
import static com.crazystone.me.customview.R.id.view_wheel3;
import static com.crazystone.me.customview.R.id.view_wheel4;
import static com.crazystone.me.customview.R.id.view_wheel5;

/**
 * Created by crazy_stone on 17-10-17.
 */

public class JiKeLayout extends LinearLayout {

    JiKeAgreeView[] view_agrees = new JiKeAgreeView[5];

    public JiKeLayout(Context context) {
        super(context);
    }

    public JiKeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public JiKeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        view_agrees[0] = (JiKeAgreeView) findViewById(view_wheel);
        view_agrees[1] = (JiKeAgreeView) findViewById(view_wheel2);
        view_agrees[2] = (JiKeAgreeView) findViewById(view_wheel3);
        view_agrees[3] = (JiKeAgreeView) findViewById(view_wheel4);
        view_agrees[4] = (JiKeAgreeView) findViewById(view_wheel5);

        view_agrees[0].setShowingNumber(19);
        view_agrees[1].setShowingNumber(1234);
        view_agrees[2].setShowingNumber(12359);
        view_agrees[3].setShowingNumber(12);
        view_agrees[4].setShowingNumber(9);
        Random random = new Random();

        for (JiKeAgreeView jiKeAgreeView : view_agrees) {
            final JiKeAgreeView wheel = jiKeAgreeView;
            wheel.setActive(random.nextBoolean());
            jiKeAgreeView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    wheel.toggle().setCallback(new JiKeAgreeView.AgreeCallback() {
                        @Override
                        public void agree(boolean isAgree) {
                            Toast.makeText(getContext(), ((isAgree ? "点赞成功" : "点赞取消")), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}
