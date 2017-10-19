package com.crazystone.me.customview.practice6;

import com.crazystone.me.customview.base.BaseActivity;
import com.crazystone.me.customview.entity.BaseFragmentEntity;
import com.crazystone.me.customview.utils.PracticeDrawFragmentFactory;

import java.util.List;

/**
 * Created by crazy_stone on 17-10-14.
 */

public class PracticeBaseAnimationActivity extends BaseActivity {
    @Override
    protected List<BaseFragmentEntity> getDatas() {
        return PracticeDrawFragmentFactory.getPractice6List();
    }
}
