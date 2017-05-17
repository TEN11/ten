package com.example.ayou.ten.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.ayou.ten.R;
import com.example.ayou.ten.adapter.MovieAdapter;
import com.example.ayou.ten.bean.MovieBean;
import com.example.ayou.ten.utils.PagingScrollHelper;
import com.example.ayou.ten.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AYOU on 2017/5/16.
 */

public class MovieFragment extends BaseFragment {
    private ImageView mDate1;
    private ImageView mDate2;
    private ImageView mWeek;
    private ImageView mMonth;
    private RecyclerView mRv;
    private MovieAdapter adapter;

    //横向分页滑动
    private PagingScrollHelper scrollHelper = new PagingScrollHelper();
    private LinearLayoutManager hLinearLayoutManager = null;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initView() {
        mDate1 = (ImageView) findViewById(R.id.movie_date1);
        mDate2 = (ImageView) findViewById(R.id.movie_date2);
        mWeek = (ImageView) findViewById(R.id.movie_week);
        mMonth = (ImageView) findViewById(R.id.movie_month);
        mRv = (RecyclerView) findViewById(R.id.movie_rv);

        hLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        adapter = new MovieAdapter(getActivity(),null);
        mRv.setAdapter(adapter);
        //分页横向滑动
        scrollHelper.setUpRecycleView(mRv);
        RecyclerView.LayoutManager layoutManager = hLinearLayoutManager;
        if (layoutManager != null) {
            mRv.setLayoutManager(layoutManager);
            scrollHelper.updateLayoutManger();
        }
        initMovieData();




    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initMovieData() {
        Call<MovieBean> call = RetrofitUtils.getNet().getMovieData();
        call.enqueue(new Callback<MovieBean>() {
            @Override
            public void onResponse(Call<MovieBean> call, Response<MovieBean> response) {
                MovieBean body = response.body();
                if (body!=null){
                    adapter.addRes(body.getResult());
                }
            }

            @Override
            public void onFailure(Call<MovieBean> call, Throwable t) {

            }
        });

    }




}
