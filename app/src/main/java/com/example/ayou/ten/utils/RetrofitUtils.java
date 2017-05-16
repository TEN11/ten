package com.example.ayou.ten.utils;

import com.example.ayou.ten.config.NetConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AYOU on 2017/5/16.
 */

public class RetrofitUtils {

    public static NetConfig getNet(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetConfig.PATH)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NetConfig netConfig = retrofit.create(NetConfig.class);
        return netConfig;
    }
}
