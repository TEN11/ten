package com.example.ayou.ten.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.ayou.ten.R;

/**
 * Created by AYOU on 2017/5/16.
 */

public class PersonalFragment extends BaseFragment {
    private ImageView mLogin;
    private TextView mFavorvite;
    private TextView mFont;
    private TextView mAbout;
    private TextView mFeedback;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initView() {
        mLogin = (ImageView) findViewById(R.id.personal_login);
        mFavorvite = (TextView) findViewById(R.id.personal_favorite);
        mFont = (TextView) findViewById(R.id.personal_font);
        mAbout = (TextView) findViewById(R.id.personal_about);
        mFeedback = (TextView) findViewById(R.id.personal_feedback);


    }
}
