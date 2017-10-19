package com.crazystone.me.customview.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.view.WaveView;

/**
 * Created by crazy_stone on 17-10-17.
 */

public class WaveViewActivity extends AppCompatActivity {

    WaveView waveView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_view);
        waveView = (WaveView) findViewById(R.id.wave_view);
        waveView.start();
    }
}
