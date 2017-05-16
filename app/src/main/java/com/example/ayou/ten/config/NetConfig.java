package com.example.ayou.ten.config;

import com.example.ayou.ten.bean.MovieBean;
import com.example.ayou.ten.bean.MovieContentBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by AYOU on 2017/5/16.
 */

public interface NetConfig {

    String PATH = "http://api.shigeten.net";

    //影评电影
    @GET("/api/Critic/GetCriticList")
    Call<MovieBean> getMovieData();

    //电影内容
    @GET("/api/Critic/GetCriticContent")
    Call<MovieContentBean> getMovieContentData(@Query("id") int id);

}
