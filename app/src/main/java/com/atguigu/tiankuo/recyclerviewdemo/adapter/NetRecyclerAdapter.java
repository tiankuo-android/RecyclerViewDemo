package com.atguigu.tiankuo.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.tiankuo.recyclerviewdemo.R;
import com.atguigu.tiankuo.recyclerviewdemo.domain.NetAudioBean;
import com.atguigu.tiankuo.recyclerviewdemo.util.Utils;
import com.squareup.picasso.Picasso;

import org.xutils.x;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class NetRecyclerAdapter extends RecyclerView.Adapter{

    private final Context mContext;
    private final List<NetAudioBean.ListBean> datas;

    public NetRecyclerAdapter(Context mContext, List<NetAudioBean.ListBean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    private static final int TYPE_VIDEO = 0;
    private static final int TYPE_IMAGE = 1;
    private static final int TYPE_TEXT = 2;
    private static final int TYPE_GIF = 3;
    private static final int TYPE_AD = 4;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return initViewHolder(viewType);
    }

    private RecyclerView.ViewHolder initViewHolder(int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View convertView = null;
        switch (viewType) {
            case TYPE_VIDEO:
                convertView = View.inflate(mContext, R.layout.all_video_item, null);
                viewHolder = new VideoHoder(convertView);

                break;
            case TYPE_IMAGE:
                convertView = View.inflate(mContext, R.layout.all_image_item, null);
                viewHolder = new ImageHolder(convertView);
                break;
            case TYPE_TEXT:
                convertView = View.inflate(mContext, R.layout.all_text_item, null);
                viewHolder = new TextHolder(convertView);
                break;
            case TYPE_GIF:
                convertView = View.inflate(mContext, R.layout.all_gif_item, null);
                viewHolder = new GifHolder(convertView);

                break;
            case TYPE_AD:
                convertView = View.inflate(mContext, R.layout.all_ad_item, null);
                viewHolder = new ADHolder(convertView);
                break;
        }
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_VIDEO) {
            VideoHoder videoHoder = (VideoHoder) holder;
            videoHoder.setData(datas.get(position));
        } else if (getItemViewType(position) == TYPE_IMAGE) {
            ImageHolder imageHolder = (ImageHolder) holder;
            imageHolder.setData(datas.get(position));
        } else if (getItemViewType(position) == TYPE_TEXT) {
            TextHolder textHolder = (TextHolder) holder;
            textHolder.setData(datas.get(position));
        } else if (getItemViewType(position) == TYPE_GIF) {
            GifHolder gifHolder = (GifHolder) holder;
            gifHolder.setData(datas.get(position));
        } else {
            ADHolder adHolder = (ADHolder) holder;
            adHolder.setData(datas.get(position));
        }


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class VideoHoder extends BaseViewHolder {
        Utils utils;
        TextView tvContext;
        JCVideoPlayerStandard jcvVideoplayer;
        TextView tvPlayNums;
        TextView tvVideoDuration;
        ImageView ivCommant;
        TextView tvCommantContext;

        public VideoHoder(View convertView) {
            super(convertView);
            tvContext = (TextView) convertView.findViewById(R.id.tv_context);
            utils = new Utils();
            tvPlayNums = (TextView) convertView.findViewById(R.id.tv_play_nums);
            tvVideoDuration = (TextView) convertView.findViewById(R.id.tv_video_duration);
            ivCommant = (ImageView) convertView.findViewById(R.id.iv_commant);
            tvCommantContext = (TextView) convertView.findViewById(R.id.tv_commant_context);
            jcvVideoplayer = (JCVideoPlayerStandard) convertView.findViewById(R.id.jcv_videoplayer);
        }
        public void setData(NetAudioBean.ListBean mediaItem) {
            super.setData(mediaItem);

            tvContext.setText(mediaItem.getText() + "_" + mediaItem.getType());

            boolean setUp = jcvVideoplayer.setUp(
                    mediaItem.getVideo().getVideo().get(0), JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    "");
            if (setUp) {
                Picasso.with(mContext)
                        .load(mediaItem.getVideo().getThumbnail().get(0))
                        .placeholder(R.drawable.video_default)
                        .error(R.drawable.video_default)
                        .into(jcvVideoplayer.thumbImageView);
            }
            tvPlayNums.setText(mediaItem.getVideo().getPlaycount() + "次播放");
            tvVideoDuration.setText(utils.stringForTime(mediaItem.getVideo().getDuration() * 1000) + "");

        }
    }

    private class ImageHolder extends RecyclerView.ViewHolder {
        public ImageHolder(View convertView) {
            super(convertView);
        }

        public void setData(NetAudioBean.ListBean listBean) {

        }
    }

    private class TextHolder extends RecyclerView.ViewHolder {
        public TextHolder(View convertView) {
            super(convertView);
        }

        public void setData(NetAudioBean.ListBean listBean) {

        }
    }

    private class GifHolder extends RecyclerView.ViewHolder {
        public GifHolder(View convertView) {
            super(convertView);
        }

        public void setData(NetAudioBean.ListBean listBean) {

        }
    }

    private class ADHolder extends RecyclerView.ViewHolder {
        public ADHolder(View convertView) {
            super(convertView);
        }

        public void setData(NetAudioBean.ListBean listBean) {

        }
    }

    private class BaseViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHeadpic;
        TextView tvName;
        TextView tvTimeRefresh;
        ImageView ivRightMore;
        ImageView ivVideoKind;
        TextView tvVideoKindText;
        TextView tvShenheDingNumber;
        TextView tvShenheCaiNumber;
        TextView tvPostsNumber;
        LinearLayout llDownload;

        public BaseViewHolder(View convertView) {
            super(convertView);

            ivHeadpic = (ImageView) convertView.findViewById(R.id.iv_headpic);
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            tvTimeRefresh = (TextView) convertView.findViewById(R.id.tv_time_refresh);
            ivRightMore = (ImageView) convertView.findViewById(R.id.iv_right_more);

            ivVideoKind = (ImageView) convertView.findViewById(R.id.iv_video_kind);
            tvVideoKindText = (TextView) convertView.findViewById(R.id.tv_video_kind_text);
            tvShenheDingNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_ding_number);
            tvShenheCaiNumber = (TextView) convertView.findViewById(R.id.tv_shenhe_cai_number);
            tvPostsNumber = (TextView) convertView.findViewById(R.id.tv_posts_number);
            llDownload = (LinearLayout) convertView.findViewById(R.id.ll_download);
        }

        public void setData(NetAudioBean.ListBean mediaItem) {
            if (mediaItem.getU() != null && mediaItem.getU().getHeader() != null && mediaItem.getU().getHeader().get(0) != null) {
                x.image().bind(ivHeadpic, mediaItem.getU().getHeader().get(0));
            }
            if (mediaItem.getU() != null && mediaItem.getU().getName() != null) {
                tvName.setText(mediaItem.getU().getName() + "");
            }

            tvTimeRefresh.setText(mediaItem.getPasstime());

            List<NetAudioBean.ListBean.TagsBean> tagsEntities = mediaItem.getTags();
            if (tagsEntities != null && tagsEntities.size() > 0) {
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < tagsEntities.size(); i++) {
                    buffer.append(tagsEntities.get(i).getName() + " ");
                }
                tvVideoKindText.setText(buffer.toString());
            }

            tvShenheDingNumber.setText(mediaItem.getUp());
            tvShenheCaiNumber.setText(mediaItem.getDown() + "");
            tvPostsNumber.setText(mediaItem.getForward() + "");

        }
    }



}
