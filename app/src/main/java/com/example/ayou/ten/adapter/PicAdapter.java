package com.example.ayou.ten.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayou.ten.R;
import com.example.ayou.ten.bean.PicBean;
import com.example.ayou.ten.bean.PicContentBean;
import com.example.ayou.ten.config.NetConfig;
import com.example.ayou.ten.utils.RetrofitUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AYOU on 2017/5/17.
 */

public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {

    private List<PicBean.ResultBean> data;
    private List<PicContentBean> picData;
    private Context context;
    private LayoutInflater inflater;

    public PicAdapter(Context context, List<PicBean.ResultBean> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }

    public void addRes(List<PicBean.ResultBean> data) {
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pic_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        int id = data.get(position).getId();
        Call<PicContentBean> call = RetrofitUtils.getNet().getPicContentData(id);
        call.enqueue(new Callback<PicContentBean>() {
            @Override
            public void onResponse(Call<PicContentBean> call, Response<PicContentBean> response) {
                PicContentBean body = response.body();
                if (body != null) {
                    picData = new ArrayList<PicContentBean>();
                    picData.add(body);
                }
                if (picData != null) {
                    PicContentBean bean = picData.get(0);
                    Picasso.with(context).load(NetConfig.PATH + "/" + bean.getImage1()).into(holder.picItemImg);
                    holder.picItemTitle.setText(bean.getTitle());
                    holder.picItemInfo.setText(bean.getAuthorbrief());
                    holder.picItemText.setText(bean.getText1());
                    holder.picItemText2.setText(bean.getText2());
                }

            }

            @Override
            public void onFailure(Call<PicContentBean> call, Throwable t) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pic_item_img)
        ImageView picItemImg;
        @BindView(R.id.pic_item_title)
        TextView picItemTitle;
        @BindView(R.id.pic_item_info)
        TextView picItemInfo;
        @BindView(R.id.pic_item_text)
        TextView picItemText;
        @BindView(R.id.pic_item_text2)
        TextView picItemText2;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
