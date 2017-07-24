package com.crazystone.me.customview.utils;

import com.crazystone.me.customview.R;
import com.crazystone.me.customview.entity.BaseFragmentEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crazy_stone on 17-7-19.
 */

public class PracticeDrawFragmentFactory {

    public static List<BaseFragmentEntity> getBaseDrawDatas() {
        List<BaseFragmentEntity> list = new ArrayList<>();
        list.add(new BaseFragmentEntity().setTitle("draw_Color").setLayoutRes(R.layout.view_practice_draw_color));
        list.add(new BaseFragmentEntity().setTitle("draw_Circle").setLayoutRes(R.layout.view_practice_draw_cirlce));
        list.add(new BaseFragmentEntity().setTitle("draw_Rect").setLayoutRes(R.layout.view_practice_draw_rect));
        list.add(new BaseFragmentEntity().setTitle("draw_Path").setLayoutRes(R.layout.view_practice_draw_path));
        list.add(new BaseFragmentEntity().setTitle("draw_Arc").setLayoutRes(R.layout.view_practice_draw_arc));
        list.add(new BaseFragmentEntity().setTitle("draw_Line").setLayoutRes(R.layout.view_practice_draw_line));
        list.add(new BaseFragmentEntity().setTitle("draw_Point").setLayoutRes(R.layout.view_practice_draw_point));
        list.add(new BaseFragmentEntity().setTitle("draw_Oval").setLayoutRes(R.layout.view_practice_draw_oval));
        list.add(new BaseFragmentEntity().setTitle("draw_Round_Rect").setLayoutRes(R.layout.view_practice_draw_round_rect));
        list.add(new BaseFragmentEntity().setTitle("draw_Histogram").setLayoutRes(R.layout.view_practice_histogram));
        list.add(new BaseFragmentEntity().setTitle("draw_PieChart").setLayoutRes(R.layout.view_practice_draw_pie_chart));
        return list;
    }

    public static List<BaseFragmentEntity> getPaintDatas() {
        List<BaseFragmentEntity> list = new ArrayList<>();
        list.add(new BaseFragmentEntity().setTitle("linear_gradient").setLayoutRes(R.layout.view_practice_linear_gradient));
        list.add(new BaseFragmentEntity().setTitle("radial_gradient").setLayoutRes(R.layout.view_practice_radial_gradient));
        list.add(new BaseFragmentEntity().setTitle("sweep_gradient").setLayoutRes(R.layout.view_practice_sweep_gradient));
        list.add(new BaseFragmentEntity().setTitle("bitmap_shader").setLayoutRes(R.layout.view_practice_bitmap_shader));
        list.add(new BaseFragmentEntity().setTitle("compose_shader").setLayoutRes(R.layout.view_practice_compose_shader));
        list.add(new BaseFragmentEntity().setTitle("lighting_color_filter").setLayoutRes(R.layout.view_practice_light_color_filter));
        list.add(new BaseFragmentEntity().setTitle("ColorMatrixColorFilter").setLayoutRes(R.layout.view_practice_color_matrix_color_filter));
        list.add(new BaseFragmentEntity().setTitle("xfermode").setLayoutRes(R.layout.view_practice_xfermode));
        list.add(new BaseFragmentEntity().setTitle("strokeCap").setLayoutRes(R.layout.view_practice_stroke_cap));
        list.add(new BaseFragmentEntity().setTitle("strokeJoin").setLayoutRes(R.layout.view_practice_stroke_join));
        list.add(new BaseFragmentEntity().setTitle("stroke_miter").setLayoutRes(R.layout.view_practice_stroke_miter));
        list.add(new BaseFragmentEntity().setTitle("path_effect").setLayoutRes(R.layout.view_practice_path_effect));
        list.add(new BaseFragmentEntity().setTitle("shadow_layer").setLayoutRes(R.layout.view_practice_shadow_layer));
        list.add(new BaseFragmentEntity().setTitle("mask_filter").setLayoutRes(R.layout.view_practice_mask_filter));
        list.add(new BaseFragmentEntity().setTitle("FillPath").setLayoutRes(R.layout.view_practice_fill_path));
        list.add(new BaseFragmentEntity().setTitle("textPath").setLayoutRes(R.layout.view_practice_text_path));
        return list;
    }

    public static List<BaseFragmentEntity> getCustomViewList() {
        List<BaseFragmentEntity> list = new ArrayList<>();
        list.add(new BaseFragmentEntity().setTitle("BezierTestView").setLayoutRes(R.layout.view_bezier_test));
        list.add(new BaseFragmentEntity().setTitle("CircleBezierCurve").setLayoutRes(R.layout.view_cirlce_bezier));
        list.add(new BaseFragmentEntity().setTitle("HexagonView").setLayoutRes(R.layout.view_hexagram));
        list.add(new BaseFragmentEntity().setTitle("RadarView").setLayoutRes(R.layout.view_radar));
        list.add(new BaseFragmentEntity().setTitle("xfermodeGroup").setLayoutRes(R.layout.view_xfermode_group));
        list.add(new BaseFragmentEntity().setTitle("YinYangFish").setLayoutRes(R.layout.view_yin_yang_fish));
        return list;
    }


}
