package com.example.ayou.ten.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.ayou.ten.R;
import com.example.ayou.ten.adapter.PicAdapter;
import com.example.ayou.ten.bean.PicBean;
import com.example.ayou.ten.utils.PagingScrollHelper;
import com.example.ayou.ten.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AYOU on 2017/5/16.
 */

public class PicFragment extends BaseFragment {

    private ImageView mDate1;
    private ImageView mDate2;
    private ImageView mWeek;
    private ImageView mMonth;
    private RecyclerView mRv;
    private PicAdapter adapter;

    //横向分页滑动
    private PagingScrollHelper scrollHelper = new PagingScrollHelper();
    private LinearLayoutManager hLinearLayoutManager = null;



    @Override
    protected int getLayoutID() {
        return R.layout.fragment_pic;
    }

    @Override
    protected void initView() {
        mDate1 = (ImageView) findViewById(R.id.pic_date1);
        mDate2 = (ImageView) findViewById(R.id.pic_date2);
        mWeek = (ImageView) findViewById(R.id.pic_week);
        mMonth = (ImageView) findViewById(R.id.pic_month);
        mRv = (RecyclerView) findViewById(R.id.pic_rv);

        hLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        adapter = new PicAdapter(getActivity(),null);
        mRv.setAdapter(adapter);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        //分页横向滑动
        scrollHelper.setUpRecycleView(mRv);
        initData();
    }

    private void initData() {
        Call<PicBean> call = RetrofitUtils.getNet().getPicData();
        call.enqueue(new Callback<PicBean>() {
            @Override
            public void onResponse(Call<PicBean> call, Response<PicBean> response) {
                PicBean body = response.body();
                if (body!=null){
                    adapter.addRes(body.getResult());
                }
            }
            @Override
            public void onFailure(Call<PicBean> call, Throwable t) {

            }
        });
    }

}
