package com.atguigu.tiankuo.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.tiankuo.recyclerviewdemo.R;
import com.atguigu.tiankuo.recyclerviewdemo.domain.NetAudioBean;
import com.atguigu.tiankuo.recyclerviewdemo.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class NetRecyclerAdapter extends RecyclerView.Adapter{

    private final Context mContext;
    private final List<NetAudioBean.ListBean> datas;

    private static final int TYPE_VIDEO = 0;
    private static final int TYPE_IMAGE = 1;
    private static final int TYPE_TEXT = 2;
    private static final int TYPE_GIF = 3;
    private static final int TYPE_AD = 4;

    public NetRecyclerAdapter(Context mContext, List<NetAudioBean.ListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return initViewHolder(viewType);

    }

    private RecyclerView.ViewHolder initViewHolder(int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View convertView = null;
        switch (viewType) {
            case TYPE_VIDEO://视频
                convertView = View.inflate(mContext, R.layout.all_video_item, null);
                viewHolder = new VideoHoder(convertView);

                break;
//            case TYPE_IMAGE://图片
//                convertView = View.inflate(mContext, R.layout.all_image_item, null);
//                viewHolder = new ImageHolder(convertView);
//                break;
//            case TYPE_TEXT://文字
//                convertView = View.inflate(mContext, R.layout.all_text_item, null);
//                viewHolder = new TextHolder(convertView);
//                break;
//            case TYPE_GIF://gif
//                convertView = View.inflate(mContext, R.layout.all_gif_item, null);
//                viewHolder = new GifHolder(convertView);
//
//                break;
//            case TYPE_AD://软件广告
//                convertView = View.inflate(mContext, R.layout.all_ad_item, null);
//                viewHolder = new ADHolder(convertView);
//                break;
        }
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_VIDEO) {
            VideoHoder videoHoder = (VideoHoder) holder;
            videoHoder.setData(datas.get(position));
//        } else if (getItemViewType(position) == TYPE_IMAGE) {
//            ImageHolder imageHolder = (ImageHolder) holder;
//            imageHolder.setData(datas.get(position));
//        } else if (getItemViewType(position) == TYPE_TEXT) {
//            TextHolder textHolder = (TextHolder) holder;
//            textHolder.setData(datas.get(position));
//        } else if (getItemViewType(position) == TYPE_GIF) {
//            GifHolder gifHolder = (GifHolder) holder;
//            gifHolder.setData(datas.get(position));
//        } else {
//            ADHolder adHolder = (ADHolder) holder;
//            adHolder.setData(datas.get(position));
       }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class VideoHoder extends RecyclerView.ViewHolder {
        Utils utils;
        TextView tvContext;
        JCVideoPlayerStandard jcvVideoplayer;
        TextView tvPlayNums;
        TextView tvVideoDuration;
        ImageView ivCommant;
        TextView tvCommantContext;

        VideoHoder(View convertView) {
            super(convertView);
//            super(convertView);
            //中间公共部分 -所有的都有
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            utils = new Utils();
            tvPlayNums = (TextView) convertView.findViewById(R.id.tv_play_nums);
            tvVideoDuration = (TextView) convertView.findViewById(R.id.tv_video_duration);
            ivCommant = (ImageView) convertView.findViewById(R.id.iv_commant);
            tvCommantContext = (TextView) convertView.findViewById(R.id.tv_commant_context);
            jcvVideoplayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv_videoplayer);
        }

        public void setData(NetAudioBean.ListBean listBean) {

            tvContext.setText(listBean.getText() + "_" + listBean.getType());
            boolean setUp = jcvVideoplayer.setUp(
                    listBean.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    "");
            if (setUp) {
                Picasso.with(mContext)
                        .load(listBean.getVideo().getThumbnail().get(0))
                        .placeholder(R.drawable.video_default)
                        .error(R.drawable.video_default)
                        .into(jcvVideoplayer.thumbImageView);
            }
            tvPlayNums.setText(listBean.getVideo().getPlaycount() + "次播放");
            tvVideoDuration.setText(utils.stringForTime(listBean.getVideo().getDuration() * 1000) + "");

        }
    }
}
