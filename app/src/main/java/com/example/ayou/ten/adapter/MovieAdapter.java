package com.example.ayou.ten.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayou.ten.R;
import com.example.ayou.ten.bean.MovieContentBean;
import com.example.ayou.ten.config.NetConfig;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by AYOU on 2017/5/16.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<MovieContentBean> contentData;

    private RecyclerView mRV;


    public MovieAdapter(Context context,List<MovieContentBean> contentData) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        if (contentData == null) {
            this.contentData = new ArrayList<>();
        } else {
            this.contentData = contentData;
        }

    }

    public void addRes(MovieContentBean bean){
        if (contentData != null){
            this.contentData.add(bean);
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return contentData==null?0:contentData.size();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (contentData != null){
            //电影内容
            MovieContentBean movieContentBean = contentData.get(position);
            holder.movieItemTitle.setText(movieContentBean.getTitle());
            Picasso.with(context).load(NetConfig.PATH+"/"+movieContentBean.getImageforplay()).into(holder.movieItemImgTop);
            holder.movieItemText1.setText(movieContentBean.getText1());
            holder.movieItemInfo.setText(String.format("作者:%s | 阅读量:%s",movieContentBean.getAuthor(),movieContentBean.getTimes()));
            Picasso.with(context).load(NetConfig.PATH+"/"+movieContentBean.getImage1()).into(holder.movieItemImgCenter1);
            holder.movieItemText2.setText(movieContentBean.getText2());
            holder.movieItemRealTitle.setText(movieContentBean.getRealtitle());
            Picasso.with(context).load(NetConfig.PATH+"/"+movieContentBean.getImage2()).into(holder.movieItemImgCenter2);
            holder.movieItemText345.setText(movieContentBean.getText3()+movieContentBean.getText4()+movieContentBean.getText5());
            Picasso.with(context).load(NetConfig.PATH+"/"+movieContentBean.getImage3()).into(holder.movieItemImgBottom1);
            Picasso.with(context).load(NetConfig.PATH+"/"+movieContentBean.getImage4()).into(holder.movieItemImgBottom2);
            holder.movieItemAuthor.setText(movieContentBean.getAuthor());
            holder.movieItemAuthorbrief.setText(movieContentBean.getAuthorbrief());

        }

    }





    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_item_imgTop)
        ImageView movieItemImgTop;
        @BindView(R.id.movie_item_title)
        TextView movieItemTitle;
        @BindView(R.id.movie_item_info)
        TextView movieItemInfo;
        @BindView(R.id.movie_item_text1)
        TextView movieItemText1;
        @BindView(R.id.movie_item_imgCenter1)
        ImageView movieItemImgCenter1;
        @BindView(R.id.movie_item_text2)
        TextView movieItemText2;
        @BindView(R.id.movie_item_realTitle)
        TextView movieItemRealTitle;
        @BindView(R.id.movie_item_imgCenter2)
        ImageView movieItemImgCenter2;
        @BindView(R.id.movie_item_text345)
        TextView movieItemText345;
        @BindView(R.id.movie_item_imgBottom1)
        ImageView movieItemImgBottom1;
        @BindView(R.id.movie_item_imgBottom2)
        ImageView movieItemImgBottom2;
        @BindView(R.id.movie_item_author)
        TextView movieItemAuthor;
        @BindView(R.id.movie_item_authorbrief)
        TextView movieItemAuthorbrief;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
