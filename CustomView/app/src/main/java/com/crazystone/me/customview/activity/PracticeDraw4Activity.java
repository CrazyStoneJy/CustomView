package com.crazystone.me.customview.activity;

import com.crazystone.me.customview.base.BaseActivity;
import com.crazystone.me.customview.entity.BaseFragmentEntity;
import com.crazystone.me.customview.utils.PracticeDrawFragmentFactory;

import java.util.List;

/**
 * Created by crazy_stone on 17-8-4.
 */

public class PracticeDraw4Activity extends BaseActivity {
    @Override
    protected List<BaseFragmentEntity> getDatas() {
        return PracticeDrawFragmentFactory.getDraw4List();
    }
}
