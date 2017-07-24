package com.crazystone.me.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.crazystone.me.customview.view.LeavesView;

/**
 * Created by crazy_stone on 17-5-26.
 */

public class XferModeActivity extends AppCompatActivity {

    LeavesView leavesView;
    TextView txt_start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        HistogramView histogramView = new HistogramView(this);
//        RadarView radarView = new RadarView(this);
//        HexagonView hexagonView = new HexagonView(this);
//        YinYangFish yinYangFish = new YinYangFish(this);
//        PathMeasurePracticeView pathMeasurePracticeView = new PathMeasurePracticeView(this);
//        XfermodeGroup group = new XfermodeGroup(this);
//        ShaderView shaderView = new ShaderView(this);
//        LeavesView leavesView = new LeavesView(this);
//        setContentView(leavesView);
//        leavesView.setProgress(30);
        setContentView(R.layout.activity_xfermode);
        leavesView = (LeavesView) findViewById(R.id.leavesView);
        txt_start = (TextView) findViewById(R.id.txt_start1);
        txt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leavesView.start();
            }
        });
        startActivity(new Intent(this, TestActivity.class));
    }
}
