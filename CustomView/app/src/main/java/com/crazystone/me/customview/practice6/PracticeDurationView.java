package com.crazystone.me.customview.practice6;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Utils;

/**
 * Created by crazy_stone on 17-10-16.
 */

public class PracticeDurationView extends RelativeLayout {

    SeekBar sb;
    ImageView imageView;
    Button button;
    int progress = 1000;
    boolean isAnimate = false;

    public PracticeDurationView(Context context) {
        super(context);
    }

    public PracticeDurationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeDurationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        sb = (SeekBar) findViewById(R.id.seekbar);
        button = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        sb.setMax(3000);
        sb.setProgress(progress);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                PracticeDurationView.this.progress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAnimate) {
                    imageView.animate().translationX(Utils.dpToPixel(200)).setDuration(progress);
                } else {
                    imageView.setTranslationX(0);
                }
                isAnimate = !isAnimate;
            }
        });

    }
}
