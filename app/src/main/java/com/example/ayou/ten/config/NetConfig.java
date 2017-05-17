package com.example.ayou.ten.config;

import com.example.ayou.ten.bean.ActrialBean;
import com.example.ayou.ten.bean.ActrialContentBean;
import com.example.ayou.ten.bean.MovieBean;
import com.example.ayou.ten.bean.MovieContentBean;
import com.example.ayou.ten.bean.PicBean;
import com.example.ayou.ten.bean.PicContentBean;

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

    //美文
    @GET("/api/Novel/GetNovelList")
    Call<ActrialBean> getActrialData();

    //美文详情
    @GET("/api/Novel/GetNovelContent")
    Call<ActrialContentBean> getActrialContentData(@Query("id") int id);

    //美图
    @GET("/api/Diagram/GetDiagramList")
    Call<PicBean> getPicData();

    //美图详情
    @GET("/api/Diagram/GetDiagramContent")
    Call<PicContentBean> getPicContentData(@Query("id") int id);

}
