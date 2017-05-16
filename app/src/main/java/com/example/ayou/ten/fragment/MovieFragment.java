package com.example.ayou.ten.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.ayou.ten.R;

/**
 * Created by AYOU on 2017/5/16.
 */

public class MovieFragment extends BaseFragment {
    private ImageView mDate1;
    private ImageView mDate2;
    private ImageView mWeek;
    private ImageView mMonth;
    private RecyclerView mRv;

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
    }
}
