package com.example.ayou.ten.config;

import com.example.ayou.ten.bean.MovieBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by AYOU on 2017/5/16.
 */

public interface netConfig {

    public static final String PATH = "http://api.shigeten.net/api";

    //影评电影
    @GET("/api/Critic/GetCriticList")
    Call<MovieBean> getMovieData();

    //电影内容


}
