package com.crazystone.me.customview.practice6;

import android.content.Context;
import android.graphics.Outline;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.crazystone.me.customview.R;

import static com.crazystone.me.customview.utils.Utils.dpToPixel;

/**
 * Created by crazy_stone on 17-10-10.
 */

public class PracticeTranslateAnimationView extends RelativeLayout {
    Button btn;
    ImageView imageView;
    int translationState = 0;
    int DEFAULT_LOOP_COUNT = 6;

    public PracticeTranslateAnimationView(Context context) {
        super(context);
    }

    public PracticeTranslateAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PracticeTranslateAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        btn = (Button) findViewById(R.id.animateBt);
        imageView = (ImageView) findViewById(R.id.imageView);
        if (moreThanLollipop()) {
            imageView.setOutlineProvider(new CustomViewProvider());
        }
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (translationState) {
                    case 0:
                        imageView.animate().translationX(300);
                        break;
                    case 1:
                        imageView.animate().translationX(0);
                        break;
                    case 2:
                        post(new Runnable() {
                            @Override
                            public void run() {
                                int height = getHeight();
                                imageView.animate().translationY(height);
                            }
                        });
//                        imageView.animate().translationY(300);
                        break;
                    case 3:
                        imageView.animate().translationY(0);
                        break;
                    case 4:
                        Log.d("TAG", "4>>>");
                        if (moreThanLollipop()) {
                            Log.d("TAG", "translate Z");
                            imageView.animate().translationZ(100);
                        }
                        break;
                    case 5:
                        if (moreThanLollipop()) {
                            imageView.animate().translationZ(0);
                        }
                        break;
                }
                translationState++;
                if (translationState == DEFAULT_LOOP_COUNT) {
                    translationState = 0;
                }
            }
        });
    }

    public boolean moreThanLollipop() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private class CustomViewProvider extends ViewOutlineProvider {

        Path path = new Path();

        {
            path.moveTo(0, dpToPixel(10));
            path.lineTo(dpToPixel(7), dpToPixel(2));
            path.lineTo(dpToPixel(116), dpToPixel(58));
            path.lineTo(dpToPixel(116), dpToPixel(70));
            path.lineTo(dpToPixel(7), dpToPixel(128));
            path.lineTo(0, dpToPixel(120));
            path.close();
        }

        @Override
        public void getOutline(View view, Outline outline) {
            outline.setConvexPath(path);
        }
    }


}
