package com.example.ayou.ten.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by AYOU on 2017/5/16.
 */

public abstract class BaseFragment extends Fragment {

    protected View layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout = inflater.inflate(getLayoutID(),container,false);

        return layout;
    }

    protected abstract int getLayoutID();

    protected abstract void initView();

    protected View findViewById(int id){
        return layout.findViewById(id);
    }


}
