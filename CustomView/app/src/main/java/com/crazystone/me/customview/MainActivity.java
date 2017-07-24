package com.crazystone.me.customview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crazystone.me.customview.practice_draw.PracticeDrawActivity;
import com.crazystone.me.customview.practice_draw.PracticeDrawPieChartView;
import com.crazystone.me.customview.practice_paint.PracticePaintActivity;
import com.crazystone.me.customview.view.WaveView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txt_start;
    TextView txt_stop;
    WaveView waveView;
    SeekBar seekBar;
    TextView txt_practice_draw1;
    TextView txt_practice_paint;
    PracticeDrawPieChartView pieChart;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        startActivity(new Intent(this, XferModeActivity.class));


    }

    private void init() {
//        txt_start = (TextView) findViewById(R.id.txt_start);
//        txt_stop = (TextView) findViewById(R.id.txt_stop);
//        waveView = (WaveView) findViewById(R.id.view_wave);
//        seekBar = (SeekBar) findViewById(R.id.seekbar_height);
//        txt_start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                waveView.start();
//            }
//        });
//        txt_stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                waveView.stop();
//            }
//        });
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                waveView.setProgress(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//        txt_practice_draw1 = (TextView) findViewById(R.id.txt_practice_draw);
//        txt_practice_draw1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, PracticeDrawActivity.class));
//            }
//        });
//        txt_practice_paint = (TextView) findViewById(R.id.txt_practice_paint);
//        txt_practice_paint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, PracticePaintActivity.class));
//            }
//        });

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new MainAdapter(getDatas()));

    }

    private List<ItemData> getDatas() {
        List<ItemData> list = new ArrayList<>();
        list.add(new ItemData().setName("PracticeDraw").setJump2Cls(PracticeDrawActivity.class));
        list.add(new ItemData().setName("PracticePaint").setJump2Cls(PracticePaintActivity.class));
        list.add(new ItemData().setName("customViewTest").setJump2Cls(CustomViewActivity.class));
        return list;
    }

    class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

        List<ItemData> list;

        public MainAdapter(List<ItemData> list) {
            this.list = list;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(View.inflate(MainActivity.this, android.R.layout.simple_list_item_1, null));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, final int position) {
            holder.txt.setText(list.get(position).getName());
            holder.txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, list.get(position).getJump2Cls()));
                }
            });
        }

        @Override
        public int getItemCount() {
            return list != null ? list.size() : 0;
        }

        class MainViewHolder extends RecyclerView.ViewHolder {

            TextView txt;

            public MainViewHolder(View itemView) {
                super(itemView);
                txt = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }
    }


    private class ItemData {
        private String name;
        private Class jump2Cls;

        public String getName() {
            return name;
        }

        public ItemData setName(String name) {
            this.name = name;
            return this;
        }

        public Class getJump2Cls() {
            return jump2Cls;
        }

        public ItemData setJump2Cls(Class jump2Cls) {
            this.jump2Cls = jump2Cls;
            return this;
        }
    }

}
