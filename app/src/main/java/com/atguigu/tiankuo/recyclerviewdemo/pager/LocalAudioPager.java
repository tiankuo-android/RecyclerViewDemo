package com.atguigu.tiankuo.recyclerviewdemo.pager;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.tiankuo.recyclerviewdemo.fragment.BaseFragment;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class LocalAudioPager extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;

    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("本地音乐的内容");
    }
}
