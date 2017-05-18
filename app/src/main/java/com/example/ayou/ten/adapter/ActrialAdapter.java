package com.example.ayou.ten.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ayou.ten.Event.EventConfig;
import com.example.ayou.ten.Event.HideEvent;
import com.example.ayou.ten.R;
import com.example.ayou.ten.bean.ActrialBean;
import com.example.ayou.ten.bean.ActrialContentBean;
import com.example.ayou.ten.utils.ObservableScrollView;
import com.example.ayou.ten.utils.RetrofitUtils;

import org.greenrobot.eventbus.EventBus;

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

public class ActrialAdapter extends RecyclerView.Adapter<ActrialAdapter.ViewHolder> implements View.OnScrollChangeListener {

    private List<ActrialBean.ResultBean> data;
    private Context context;
    private LayoutInflater inflater;

    private List<ActrialContentBean> contentData;

    public ActrialAdapter(Context context, List<ActrialBean.ResultBean> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        if (data == null) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }
    }

    public void addRes(List<ActrialBean.ResultBean> data) {
        if (data != null) {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.actrial_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        int id = data.get(position).getId();
        Call<ActrialContentBean> call = RetrofitUtils.getNet().getActrialContentData(id);
        call.enqueue(new Callback<ActrialContentBean>() {
            @Override
            public void onResponse(Call<ActrialContentBean> call, Response<ActrialContentBean> response) {
                ActrialContentBean body = response.body();
                if (body != null) {
                    contentData = new ArrayList<ActrialContentBean>();
                    contentData.add(body);
                    if (contentData != null) {
                        ActrialContentBean bean = contentData.get(0);
                        holder.actrialItemTitle.setText(bean.getTitle());
                        holder.actrialItemInfo.setText(String.format("作者:%s | 阅读量:%s", bean.getAuthor(), bean.getTimes()));
                        holder.actrialItemSummary.setText(bean.getSummary());
                        holder.actrialItemText.setText(bean.getText());
                        holder.actrialItemAuthor.setText(bean.getAuthor());
                        holder.actrialItemAuthorbrief.setText(bean.getAuthorbrief());
                    }
                }
            }

            @Override
            public void onFailure(Call<ActrialContentBean> call, Throwable t) {

            }
        });

            holder.articalSv.setScrollViewChangeListener(this);

    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

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
        @BindView(R.id.actrial_item_title)
        TextView actrialItemTitle;
        @BindView(R.id.actrial_item_info)
        TextView actrialItemInfo;
        @BindView(R.id.actrial_item_summary)
        TextView actrialItemSummary;
        @BindView(R.id.actrial_item_text)
        TextView actrialItemText;
        @BindView(R.id.actrial_item_author)
        TextView actrialItemAuthor;
        @BindView(R.id.actrial_item_authorbrief)
        TextView actrialItemAuthorbrief;
        @BindView(R.id.artical_sv)
        ObservableScrollView articalSv;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
