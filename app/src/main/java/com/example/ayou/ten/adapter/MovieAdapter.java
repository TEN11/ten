package com.example.ayou.ten.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayou.ten.Event.EventConfig;
import com.example.ayou.ten.Event.HideEvent;
import com.example.ayou.ten.R;
import com.example.ayou.ten.bean.MovieBean;
import com.example.ayou.ten.bean.MovieContentBean;
import com.example.ayou.ten.config.NetConfig;
import com.example.ayou.ten.utils.ObservableScrollView;
import com.example.ayou.ten.utils.RetrofitUtils;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AYOU on 2017/5/16.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements View.OnScrollChangeListener {

    private Context context;
    private LayoutInflater inflater;
    private List<MovieContentBean> contentData;
    private List<MovieBean.ResultBean> data;
    private RecyclerView mRV;


    public MovieAdapter(Context context, List<MovieBean.ResultBean> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }

    }

    public void addRes(List<MovieBean.ResultBean> data) {
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        int id = data.get(position).getId();
        Call<MovieContentBean> call = RetrofitUtils.getNet().getMovieContentData(id);
        call.enqueue(new Callback<MovieContentBean>() {
            @Override
            public void onResponse(Call<MovieContentBean> call, Response<MovieContentBean> response) {
                MovieContentBean body = response.body();
                if (body != null) {
                    contentData = new ArrayList<MovieContentBean>();
                    contentData.add(body);
                }
                if (contentData != null) {
                    //电影内容
                    MovieContentBean movieContentBean = contentData.get(0);
                    holder.movieItemTitle.setText(movieContentBean.getTitle());
                    Picasso.with(context).load(NetConfig.PATH + "/" + movieContentBean.getImageforplay()).into(holder.movieItemImgTop);
                    holder.movieItemText1.setText(movieContentBean.getText1());
                    holder.movieItemInfo.setText(String.format("作者:%s | 阅读量:%s", movieContentBean.getAuthor(), movieContentBean.getTimes()));
                    Picasso.with(context).load(NetConfig.PATH + "/" + movieContentBean.getImage1()).into(holder.movieItemImgCenter1);
                    holder.movieItemText2.setText(movieContentBean.getText2());
                    holder.movieItemRealTitle.setText(movieContentBean.getRealtitle());
                    Picasso.with(context).load(NetConfig.PATH + "/" + movieContentBean.getImage2()).into(holder.movieItemImgCenter2);
                    holder.movieItemText345.setText(movieContentBean.getText3() + movieContentBean.getText4() + movieContentBean.getText5());
                    Picasso.with(context).load(NetConfig.PATH + "/" + movieContentBean.getImage3()).into(holder.movieItemImgBottom1);
                    Picasso.with(context).load(NetConfig.PATH + "/" + movieContentBean.getImage4()).into(holder.movieItemImgBottom2);
                    holder.movieItemAuthor.setText(movieContentBean.getAuthor());
                    holder.movieItemAuthorbrief.setText(movieContentBean.getAuthorbrief());
                }
            }

            @Override
            public void onFailure(Call<MovieContentBean> call, Throwable t) {

            }
        });


        holder.movieItemSv.setScrollViewChangeListener(this);


    }
    //scrollview的滑动监听
    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Log.i("jzq", "scrollY=="+scrollY+"oldScrollY=="+oldScrollY);
        HideEvent event = new HideEvent(EventConfig.IS_HIDE);
        //向上滑  oldScrollY < scrollY  true
        //向下滑  oldScrollY > scrollY  false
        event.setUP(oldScrollY<scrollY);
        EventBus.getDefault().post(event);


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
        @BindView(R.id.movie_item_sv)
        ObservableScrollView movieItemSv;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
