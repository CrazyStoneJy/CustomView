package com.crazystone.me.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crazystone.me.customview.view.BezierTestView;
import com.crazystone.me.customview.view.CircleBezierCurve;
import com.crazystone.me.customview.view.WaveView;

public class MainActivity extends AppCompatActivity {

    TextView txt_start;
    TextView txt_stop;
    WaveView waveView;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        setContentView(new WaveView(this));
    }

    private void init() {
        txt_start = (TextView) findViewById(R.id.txt_start);
        txt_stop = (TextView) findViewById(R.id.txt_stop);
        waveView = (WaveView) findViewById(R.id.view_wave);
        seekBar = (SeekBar) findViewById(R.id.seekbar_height);
        txt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveView.start();
            }
        });
        txt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waveView.stop();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waveView.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
