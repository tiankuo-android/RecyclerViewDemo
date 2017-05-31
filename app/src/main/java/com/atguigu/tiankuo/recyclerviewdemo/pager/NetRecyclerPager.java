package com.atguigu.tiankuo.recyclerviewdemo.pager;

import android.view.View;

import com.atguigu.tiankuo.recyclerviewdemo.R;
import com.atguigu.tiankuo.recyclerviewdemo.fragment.BaseFragment;

import java.util.ArrayList;

public class NetRecyclerPager extends BaseFragment {

    private ArrayList<String> datas;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.activity_net_recycler_pager, null);
        return null;
    }

    @Override
    public void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i <100; i++) {
            datas.add("Content"+ i);
        }
    }


}
