package com.atguigu.tiankuo.recyclerviewdemo.pager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.tiankuo.recyclerviewdemo.R;
import com.atguigu.tiankuo.recyclerviewdemo.adapter.NetRecyclerAdapter;
import com.atguigu.tiankuo.recyclerviewdemo.domain.NetAudioBean;
import com.atguigu.tiankuo.recyclerviewdemo.fragment.BaseFragment;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.atguigu.tiankuo.recyclerviewdemo.R.id.recyclerview;

public class NetRecyclerPager extends BaseFragment {

    private ArrayList<String> datas;
    private String NET_AUDIO_URL = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";
    private NetRecyclerAdapter myAdapter;


    @Bind(recyclerview)
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
        super.initData();
        getDataFromNet();
    }

    private void getDataFromNet() {
        RequestParams reques = new RequestParams(NET_AUDIO_URL);
        Log.e("TAG","reques--------" + reques);
        x.http().get(reques, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("onError==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
            }
        });
    }

    private void processData(String result) {
        NetAudioBean netAudioBean = new Gson().fromJson(result, NetAudioBean.class);
        List<NetAudioBean.ListBean> datas = netAudioBean.getList();
        String text = datas.get(0).getText();
        if (datas != null && datas.size() > 0) {
            //有数据
            Log.e("TAG","------有数据-----");
            tvNomedia.setVisibility(View.GONE);
            //设置适配器
            myAdapter = new NetRecyclerAdapter(context, datas);
            recyclerView.setAdapter(myAdapter);

        } else {
            Log.e("TAG","------没有数据-----");
            tvNomedia.setVisibility(View.VISIBLE);
        }
        StaggeredGridLayoutManager staggeredGridLayoutManager =  new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);


    }
    private List<NetAudioBean.ListBean> parsedJson(String json) {
        NetAudioBean netAudioBean = new Gson().fromJson(json, NetAudioBean.class);
        return netAudioBean.getList();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
