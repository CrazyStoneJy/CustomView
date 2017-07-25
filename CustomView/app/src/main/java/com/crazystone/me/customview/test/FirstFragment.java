package com.crazystone.me.customview.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazystone.me.customview.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by crazy_stone on 17-7-24.
 */

public class FirstFragment extends Fragment {

    TextView txt_click, txt_content, txt_activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = View.inflate(getContext(), R.layout.fragment_first, null);
        txt_click = (TextView) contentView.findViewById(R.id.txt_first_click);
        txt_content = (TextView) contentView.findViewById(R.id.txt_first_content);
        txt_activity = (TextView) contentView.findViewById(R.id.txt_send_activity);
        txt_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new FirstEvent("first event"));
            }
        });
        txt_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new SecondActivityEvent("message from first fragment"));
            }
        });

        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SecondEvent event) {
        String message = event.getMessage();
        txt_content.setText(message);
    }

}
