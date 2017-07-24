package com.crazystone.me.customview.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.crazystone.me.customview.utils.Windows;

/**
 * Created by crazy_stone on 17-5-26.
 */

public class XfermodeGroup extends LinearLayout {


//    CLEAR       (0),
//    /** [Sa, Sc] */
//    SRC         (1),
//    /** [Da, Dc] */
//    DST         (2),
//    /** [Sa + (1 - Sa)*Da, Rc = Sc + (1 - Sa)*Dc] */
//    SRC_OVER    (3),
//    /** [Sa + (1 - Sa)*Da, Rc = Dc + (1 - Da)*Sc] */
//    DST_OVER    (4),
//    /** [Sa * Da, Sc * Da] */
//    SRC_IN      (5),
//    /** [Sa * Da, Sa * Dc] */
//    DST_IN      (6),
//    /** [Sa * (1 - Da), Sc * (1 - Da)] */
//    SRC_OUT     (7),
//    /** [Da * (1 - Sa), Dc * (1 - Sa)] */
//    DST_OUT     (8),
//    /** [Da, Sc * Da + (1 - Sa) * Dc] */
//    SRC_ATOP    (9),
//    /** [Sa, Sa * Dc + Sc * (1 - Da)] */
//    DST_ATOP    (10),
//    /** [Sa + Da - 2 * Sa * Da, Sc * (1 - Da) + (1 - Sa) * Dc] */
//    XOR         (11),
//    /** [Sa + Da - Sa*Da,
//     Sc*(1 - Da) + Dc*(1 - Sa) + min(Sc, Dc)] */
//    DARKEN      (16),
//    /** [Sa + Da - Sa*Da,
//     Sc*(1 - Da) + Dc*(1 - Sa) + max(Sc, Dc)] */
//    LIGHTEN     (17),
//    /** [Sa * Da, Sc * Dc] */
//    MULTIPLY    (13),
//    /** [Sa + Da - Sa * Da, Sc + Dc - Sc * Dc] */
//    SCREEN      (14),
//    /** Saturate(S + D) */
//    ADD         (12),
//    OVERLAY     (15);


    private PorterDuff.Mode[] modes = {PorterDuff.Mode.CLEAR, PorterDuff.Mode.SRC, PorterDuff.Mode.DST, PorterDuff.Mode.SRC_OVER, PorterDuff.Mode.DST_OVER,
            PorterDuff.Mode.SRC_IN, PorterDuff.Mode.DST_IN, PorterDuff.Mode.SRC_OUT, PorterDuff.Mode.DST_OUT, PorterDuff.Mode.SRC_ATOP, PorterDuff.Mode.DST_ATOP,
            PorterDuff.Mode.XOR, PorterDuff.Mode.DARKEN, PorterDuff.Mode.LIGHTEN, PorterDuff.Mode.MULTIPLY, PorterDuff.Mode.SCREEN, PorterDuff.Mode.ADD,
            PorterDuff.Mode.OVERLAY};
    private String[] names = {"CLEAR", "SRC", "DST", "SRC_OVER", "DST_OVER", "SRC_IN", "DST_IN", "SRC_OUT", "DST_OUT", "SRC_ATOP", "DST_ATOP", "XOR", "DARKEN",
            "LIGHTEN", "MULTIPLY", "SCREEN", "ADD", "OVERLAY"};


    public XfermodeGroup(Context context) {
        super(context);
        init();
    }

    public XfermodeGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XfermodeGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        int screenWidth = Windows.getScreenWidth(getContext());
        int sixEvenWith = screenWidth / 6;
        int fourEvenWidth = screenWidth / 4;
        int margin = (fourEvenWidth - sixEvenWith) / 2;
        int len = modes.length;
        int column = len % 4 == 0 ? (len / 4) : ((len / 4) + 1);

        Log.d(XfermodeGroup.class.getSimpleName(), "column:" + column);
        setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < column; i++) {
            LinearLayout rowLinear = new LinearLayout(getContext());
            rowLinear.setOrientation(LinearLayout.HORIZONTAL);
            int limit = i == (column - 1) ? (len - i * 4) : 4;
            Log.d(XfermodeGroup.class.getSimpleName(), "limit:" + limit + ",i:" + i + ",column:" + column);
            for (int j = 0; j < limit; j++) {
                int index = 4 * i + j;
                Log.d(XfermodeGroup.class.getSimpleName(), "index:" + index + ",i:" + i + ",limit:" + limit);
                LinearLayout innerLinear = new LinearLayout(getContext());
//                innerLinear.setBackgroundColor(Color.RED);
                innerLinear.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(sixEvenWith, LayoutParams.WRAP_CONTENT);
                lp.leftMargin = margin;
                lp.topMargin = margin;
                lp.rightMargin = margin;
                lp.bottomMargin = margin;
                innerLinear.setLayoutParams(lp);

                XfermodeView view = new XfermodeView(getContext()).setInfo(new XfermodeView.ModeInfo().setMode(modes[index]).setName(names[index]));
                LinearLayout.LayoutParams view_lp = new LinearLayout.LayoutParams(sixEvenWith, sixEvenWith);
                view.setLayoutParams(view_lp);
                innerLinear.addView(view);

                TextView textView = new TextView(getContext());
                textView.setText(names[index]);
                textView.setTextColor(Color.BLACK);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                innerLinear.addView(textView);

                rowLinear.addView(innerLinear);

            }
            addView(rowLinear);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
