package com.crazystone.me.customview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.practice6.PracticeBaseAnimationActivity;
import com.crazystone.me.customview.practice_draw.PracticeDrawActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        startActivity(new Intent(this, XferModeActivity.class));


    }

    private void init() {

        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new MainAdapter(getDatas()));

    }

    private List<ItemData> getDatas() {
        List<ItemData> list = new ArrayList<>();
        list.add(new ItemData().setName("PracticeDraw").setJump2Cls(PracticeDrawActivity.class));
        list.add(new ItemData().setName("PracticePaint").setJump2Cls(PracticePaintActivity.class));
//        list.add(new ItemData().setName("EventBusTest").setJump2Cls(EventBusTestActivity.class));
        list.add(new ItemData().setName("PracticeDrawText").setJump2Cls(DrawTextActivity.class));
        list.add(new ItemData().setName("PracticeDraw4").setJump2Cls(PracticeDraw4Activity.class));
        list.add(new ItemData().setName("CalendarView").setJump2Cls(CalendarViewActivity.class));
        list.add(new ItemData().setName("MatrixTest").setJump2Cls(MatrixTestActivity.class));
        list.add(new ItemData().setName("PracticeBaseAnimation").setJump2Cls(PracticeBaseAnimationActivity.class));
        list.add(new ItemData().setName("WaveView").setJump2Cls(WaveViewActivity.class));
        list.add(new ItemData().setName("CallForPaper").setJump2Cls(CallforPaperActivity.class));
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

        String getName() {
            return name;
        }

        ItemData setName(String name) {
            this.name = name;
            return this;
        }

        Class getJump2Cls() {
            return jump2Cls;
        }

        ItemData setJump2Cls(Class jump2Cls) {
            this.jump2Cls = jump2Cls;
            return this;
        }
    }

}
