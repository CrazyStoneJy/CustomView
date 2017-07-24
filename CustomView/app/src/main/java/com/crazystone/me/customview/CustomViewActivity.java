package com.crazystone.me.customview;

import com.crazystone.me.customview.base.BaseActivity;
import com.crazystone.me.customview.entity.BaseFragmentEntity;
import com.crazystone.me.customview.utils.PracticeDrawFragmentFactory;

import java.util.List;

/**
 * Created by crazy_stone on 17-7-21.
 */

public class CustomViewActivity extends BaseActivity {
    @Override
    protected List<BaseFragmentEntity> getDatas() {
        return PracticeDrawFragmentFactory.getCustomViewList();
    }
}
