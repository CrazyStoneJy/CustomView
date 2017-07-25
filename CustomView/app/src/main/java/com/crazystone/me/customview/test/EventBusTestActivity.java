package com.crazystone.me.customview.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.crazystone.me.customview.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by crazy_stone on 17-7-24.
 */

public class EventBusTestActivity extends AppCompatActivity {

    TextView txt_jump, txt_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_event_bus);
        EventBus.getDefault().register(this);
        txt_jump = (TextView) findViewById(R.id.txt_event_bus_jump);
        txt_content = (TextView) findViewById(R.id.txt_eventbus_content);
        txt_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventBusTestActivity.this, SecondActivity.class));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false, priority = 0)
    public void onEvent(Event event) {
        String message = event.getMessage();
        txt_content.setText(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
