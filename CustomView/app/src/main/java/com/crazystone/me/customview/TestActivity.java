package com.crazystone.me.customview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazystone.me.customview.practice.ColorFilterView;
import com.crazystone.me.customview.utils.ColorMatrixs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crazy_stone on 17-7-18.
 */

public class TestActivity extends Activity {

    RecyclerView recyclerView;
    ColorFilterView colorFilterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        colorFilterView = (ColorFilterView) findViewById(R.id.colorFilterView);
        initData();
    }

    private void initData() {

        List<String> list = new ArrayList<>();
        list.add("half");
        list.add("prime");
        list.add("gray");
        list.add("reverse");
        list.add("old");
        list.add("black");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new ListRecyclerViewAdapter(this, list));
    }

    private class ListRecyclerViewAdapter extends RecyclerView.Adapter<ListRecyclerViewAdapter.ListViewHolder> {

        private List<String> list;
        private Context context;

        public ListRecyclerViewAdapter(Context context, List<String> list) {
            this.list = list;
            this.context = context;
        }

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = View.inflate(context, android.R.layout.simple_list_item_1, null);
            return new ListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ListViewHolder holder, int position) {
            holder.txt.setText(list.get(position));
            final String str = list.get(position);
            holder.txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("half".equals(str)) {
                        colorFilterView.setStyle(ColorMatrixs.HALF);
                    } else if ("prime".equals(str)) {
                        colorFilterView.setStyle(ColorMatrixs.PRIME);
                    } else if ("gray".equals(str)) {
                        colorFilterView.setStyle(ColorMatrixs.GRAY);
                    } else if ("reverse".equals(str)) {
                        colorFilterView.setStyle(ColorMatrixs.REVERSE);
                    } else if ("old".equals(str)) {
                        colorFilterView.setStyle(ColorMatrixs.OLD);
                    } else if ("black".equals(str)) {
                        colorFilterView.setStyle(ColorMatrixs.BLACK_WHITE);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list != null ? list.size() : 0;
        }

        class ListViewHolder extends RecyclerView.ViewHolder {

            TextView txt;

            public ListViewHolder(View itemView) {
                super(itemView);
                txt = (TextView) itemView.findViewById(android.R.id.text1);
            }
        }


    }
}
