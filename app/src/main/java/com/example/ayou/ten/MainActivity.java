package com.example.ayou.ten;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ayou.ten.Event.EventConfig;
import com.example.ayou.ten.Event.HideEvent;
import com.example.ayou.ten.fragment.ActrialFragment;
import com.example.ayou.ten.fragment.MovieFragment;
import com.example.ayou.ten.fragment.PersonalFragment;
import com.example.ayou.ten.fragment.PicFragment;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.main_movie)
    RadioButton mainMovie;
    @BindView(R.id.main_artical)
    RadioButton mainArtical;
    @BindView(R.id.main_pic)
    RadioButton mainPic;
    @BindView(R.id.main_personal)
    RadioButton mainPersonal;
    @BindView(R.id.main_rg)
    RadioGroup mainRg;
    @BindView(R.id.main_fenxiang)
    ImageView mainFenxiang;

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private MovieFragment movieFragment;
    private ActrialFragment actrialFragment;
    private PicFragment picFragment;
    private PersonalFragment personalFragment;
    private UMShareListener umShareListener;
    private UMWeb web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainRg.setOnCheckedChangeListener(this);

        mainRg.check(R.id.main_movie);

        initShare();

        mainFenxiang.setOnClickListener(this);

    }
    //三方分享
    private void initShare() {
        web = new UMWeb("http://www.baidu.com");
        web.setDescription("百度");
        web.setTitle("百度");
//        new ShareAction(this).withMedia(web).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener).share();

        //分享开始的回调
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Log.i("plat", "platform" + platform);

                Toast.makeText(MainActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(MainActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
                if (t != null) {
                    Log.i("throw", "throw:" + t.getMessage());
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
//                Toast.makeText(MainActivity.this, platform + " 分享取消", Toast.LENGTH_SHORT).show();
            }
        };
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        if (movieFragment != null) {
            transaction.hide(movieFragment);
        }
        if (actrialFragment != null) {
            transaction.hide(actrialFragment);
        }
        if (picFragment != null) {
            transaction.hide(picFragment);
        }
        if (personalFragment != null) {
            transaction.hide(personalFragment);
        }
        switch (checkedId) {

            case R.id.main_movie:
                if (movieFragment == null) {
                    movieFragment = new MovieFragment();
                    transaction.add(R.id.main_fl, movieFragment);
                } else {
                    transaction.show(movieFragment);
                }
                mainFenxiang.setVisibility(View.VISIBLE);
                break;
            case R.id.main_artical:
                if (actrialFragment == null) {
                    actrialFragment = new ActrialFragment();
                    transaction.add(R.id.main_fl, actrialFragment);
                } else {
                    transaction.show(actrialFragment);
                }
                mainFenxiang.setVisibility(View.VISIBLE);
                break;
            case R.id.main_pic:
                if (picFragment == null) {
                    picFragment = new PicFragment();
                    transaction.add(R.id.main_fl, picFragment);
                } else {
                    transaction.show(picFragment);
                }
                mainFenxiang.setVisibility(View.VISIBLE);
                break;
            case R.id.main_personal:
                if (personalFragment == null) {
                    personalFragment = new PersonalFragment();
                    transaction.add(R.id.main_fl, personalFragment);
                } else {
                    transaction.show(personalFragment);
                }
                mainFenxiang.setVisibility(View.GONE);
                break;
        }
        transaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void TabHide(HideEvent event) {
        if (event.WHAT == EventConfig.IS_HIDE) {
            boolean up = event.isUP();
            if (up) {//向上滑 隐藏底部tab 和 分享按钮
                mainRg.setVisibility(View.GONE);
                mainFenxiang.setVisibility(View.GONE);
            } else {
                mainRg.setVisibility(View.VISIBLE);
                mainFenxiang.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void onClick(View v) {
//        Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        new ShareAction(MainActivity.this).withText("hello")
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN,SHARE_MEDIA.SINA)
                .setCallback(umShareListener).open();
    }
}
