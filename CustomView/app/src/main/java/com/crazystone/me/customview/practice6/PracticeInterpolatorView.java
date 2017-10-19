package com.crazystone.me.customview.practice6;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Path;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crazy_stone on 17-10-16.
 */

public class PracticeInterpolatorView extends RelativeLayout {

    ImageView imageView;
    Button btn;
    Spinner spinner;
    List<Interpolator> list;

    public PracticeInterpolatorView(Context context) {
        super(context);
    }

    public PracticeInterpolatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeInterpolatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        imageView = (ImageView) findViewById(R.id.imageView);
        btn = (Button) findViewById(R.id.animateBt);
        spinner = (Spinner) findViewById(R.id.spinner);
        list = getInterpolatorList();
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.animate()
                        .translationX(Utils.dpToPixel(100))
                        .setDuration(1000)
                        .setInterpolator(list.get(spinner.getSelectedItemPosition()))
                        .setUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                Log.d("TAG", "value:" + animation.getAnimatedValue());
                            }
                        })
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setTranslationX(0);
                            }
                        });
            }
        });

        ObjectAnimator animator;

    }

    private List<Interpolator> getInterpolatorList() {
        Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(0.2F, 0.2F);
        path.lineTo(0.5F, 1.4F);
        path.moveTo(0.5F, 0.6F);
        path.lineTo(1, 1);
        List<Interpolator> list = new ArrayList<>();
        list.add(new AccelerateDecelerateInterpolator());
        list.add(new LinearInterpolator());
        list.add(new AccelerateInterpolator());
        list.add(new DecelerateInterpolator());
        list.add(new AnticipateInterpolator());
        list.add(new OvershootInterpolator());
        list.add(new AnticipateOvershootInterpolator());
        list.add(new BounceInterpolator());
        list.add(new CycleInterpolator(2F));
        list.add(PathInterpolatorCompat.create(path));
        list.add(new FastOutLinearInInterpolator());
        list.add(new FastOutSlowInInterpolator());
        list.add(new LinearOutSlowInInterpolator());
        list.add(new CustomInterpolator());
        return list;
    }

}
