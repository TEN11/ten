package com.example.ayou.ten.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.ayou.ten.R;
import com.example.ayou.ten.adapter.ActrialAdapter;
import com.example.ayou.ten.bean.ActrialBean;
import com.example.ayou.ten.utils.PagingScrollHelper;
import com.example.ayou.ten.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AYOU on 2017/5/16.
 */

public class ActrialFragment extends BaseFragment {

    private ImageView mDate1;
    private ImageView mDate2;
    private ImageView mWeek;
    private ImageView mMonth;
    private RecyclerView mRv;
    private ActrialAdapter adapter;
//    private List<actrialContentBean> data;
//    private List<actrialBean.ResultBean> actrialData = new ArrayList<>();

    //横向分页滑动
    private PagingScrollHelper scrollHelper = new PagingScrollHelper();
    private LinearLayoutManager hLinearLayoutManager = null;
    
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_artical;
    }

    @Override
    protected void initView() {

        mDate1 = (ImageView) findViewById(R.id.actrial_date1);
        mDate2 = (ImageView) findViewById(R.id.actrial_date2);
        mWeek = (ImageView) findViewById(R.id.actrial_week);
        mMonth = (ImageView) findViewById(R.id.actrial_month);
        mRv = (RecyclerView) findViewById(R.id.actrial_rv);

        hLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        adapter = new ActrialAdapter(getActivity(),null);
        mRv.setAdapter(adapter);
        //分页横向滑动
        scrollHelper.setUpRecycleView(mRv);
        RecyclerView.LayoutManager layoutManager = hLinearLayoutManager;
        if (layoutManager != null) {
            mRv.setLayoutManager(layoutManager);
            scrollHelper.updateLayoutManger();
        }

        initData();

        
    }
    //获取美文数据
    private void initData() {
        Call<ActrialBean> call = RetrofitUtils.getNet().getActrialData();
        call.enqueue(new Callback<ActrialBean>() {
            @Override
            public void onResponse(Call<ActrialBean> call, Response<ActrialBean> response) {
                ActrialBean body = response.body();
                adapter.addRes(body.getResult());
            }

            @Override
            public void onFailure(Call<ActrialBean> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
