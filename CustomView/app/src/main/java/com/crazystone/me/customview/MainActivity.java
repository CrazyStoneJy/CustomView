package com.crazystone.me.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.crazystone.me.customview.view.BezierTestView;
import com.crazystone.me.customview.view.CircleBezierCurve;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(new CircleBezierCurve(this));
    }
}
