package com.crazystone.me.customview.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.crazystone.me.customview.R;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class BaseFragment extends Fragment {

    public static final String BUNDLE_TITLE_NAME = "titleName";
    public static final String BUNDLE_LAYOUT_RES = "layoutRes";

    private int layoutRes;

    public static Fragment newInstance(@LayoutRes int layoutRes) {
        Fragment fragment = new BaseFragment();
        Bundle bundle = new Bundle();
//        bundle.putString(BUNDLE_TITLE_NAME, title);
        bundle.putInt(BUNDLE_LAYOUT_RES, layoutRes);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = View.inflate(getContext(), R.layout.fragment_practice_draw, null);
        getArgs();
        ViewStub viewStub = (ViewStub) contentView.findViewById(R.id.practice_test_viewstub);
        viewStub.setLayoutResource(layoutRes);
        viewStub.inflate();
        return contentView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void getArgs() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            layoutRes = bundle.getInt(BUNDLE_LAYOUT_RES, 0);
        }
    }
}
