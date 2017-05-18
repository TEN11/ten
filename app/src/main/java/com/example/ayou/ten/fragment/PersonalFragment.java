package com.example.ayou.ten.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayou.ten.R;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

/**
 * Created by AYOU on 2017/5/16.
 */

public class PersonalFragment extends BaseFragment implements View.OnClickListener {
    private ImageView mLogin;
    private TextView mFavorvite;
    private TextView mFont;
    private TextView mAbout;
    private TextView mFeedback;
    private UMAuthListener umAuthListener;

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


        setLogin();

        mLogin.setOnClickListener(this);
        mFont.setOnClickListener(this);
        mFavorvite.setOnClickListener(this);
        mAbout.setOnClickListener(this);
        mFeedback.setOnClickListener(this);
    }
    //三方登陆
    private void setLogin() {
        umAuthListener = new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //授权开始的回调
            }

            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                Toast.makeText(getActivity().getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(getActivity().getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_login:
                if (!UMShareAPI.get(getActivity()).isInstall(getActivity(), SHARE_MEDIA.WEIXIN)) {
                    return;
                }
                UMShareAPI.get(getActivity()).getPlatformInfo(getActivity(), SHARE_MEDIA.QQ, umAuthListener);

                break;
            case R.id.personal_favorite:
                startActivity(new Intent(getActivity(), FavoriteActivity.class));
                break;
            case R.id.personal_font:
                startActivity(new Intent(getActivity(), FontActivity.class));
                break;
            case R.id.personal_about:
                startActivity(new Intent(getActivity(), AboutUsActivity.class));
                break;
            case R.id.personal_feedback:
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;

        }
    }
}
