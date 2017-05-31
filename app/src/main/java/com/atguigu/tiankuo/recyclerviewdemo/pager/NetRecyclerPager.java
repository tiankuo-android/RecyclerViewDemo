package com.atguigu.tiankuo.recyclerviewdemo.pager;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.tiankuo.recyclerviewdemo.R;
import com.atguigu.tiankuo.recyclerviewdemo.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NetRecyclerPager extends BaseFragment {

    private ArrayList<String> datas;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.tv_nomedia)
    TextView tvNomedia;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.activity_net_recycler_pager, null);
        ButterKnife.bind(this, view);
        return null;
    }

    @Override
    public void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i <100; i++) {
            datas.add("Content"+ i);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
