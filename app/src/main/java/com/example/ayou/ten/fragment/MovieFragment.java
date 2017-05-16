package com.example.ayou.ten.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.ayou.ten.R;
import com.example.ayou.ten.adapter.MovieAdapter;
import com.example.ayou.ten.bean.MovieBean;
import com.example.ayou.ten.bean.MovieContentBean;
import com.example.ayou.ten.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.List;

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
    private List<MovieContentBean> data;
    private List<MovieBean.ResultBean> movieData = new ArrayList<>();

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

        adapter = new MovieAdapter(getActivity(),null);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRv.setLayoutManager(manager);
        mRv.setAdapter(adapter);


        initMovieData();

    }

    private void initMovieData() {
        Call<MovieBean> call = RetrofitUtils.getNet().getMovieData();
        call.enqueue(new Callback<MovieBean>() {
            @Override
            public void onResponse(Call<MovieBean> call, Response<MovieBean> response) {
                MovieBean body = response.body();
                movieData.addAll(body.getResult());
                initContentData(movieData);
            }

            @Override
            public void onFailure(Call<MovieBean> call, Throwable t) {

            }
        });

    }

    private void initContentData(List<MovieBean.ResultBean> movieData) {
        for (int i = 0; i < movieData.size(); i++) {
            int id = movieData.get(i).getId();

            initContent(id);

        }
    }

    private void initContent(int id) {
        Call<MovieContentBean> call = RetrofitUtils.getNet().getMovieContentData(id);
        call.enqueue(new Callback<MovieContentBean>() {
            @Override
            public void onResponse(Call<MovieContentBean> call, Response<MovieContentBean> response) {
                MovieContentBean body = response.body();
                adapter.addRes(body);

            }

            @Override
            public void onFailure(Call<MovieContentBean> call, Throwable t) {

            }
        });
    }


}
