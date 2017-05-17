package com.example.ayou.ten;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ayou.ten.Event.EventConfig;
import com.example.ayou.ten.Event.HideEvent;
import com.example.ayou.ten.fragment.ActrialFragment;
import com.example.ayou.ten.fragment.MovieFragment;
import com.example.ayou.ten.fragment.PersonalFragment;
import com.example.ayou.ten.fragment.PicFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainRg.setOnCheckedChangeListener(this);

        mainRg.check(R.id.main_movie);

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
                mainFenxiang.setVisibility(View.GONE );
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
    public void TabHide(HideEvent event){
        if (event.WHAT == EventConfig.IS_HIDE){
            boolean up = event.isUP();
            if (up){//向上滑 隐藏底部tab 和 分享按钮
                mainRg.setVisibility(View.GONE);
                mainFenxiang.setVisibility(View.GONE);
            }else {
                mainRg.setVisibility(View.VISIBLE);
                mainFenxiang.setVisibility(View.VISIBLE);
            }
        }
    }

}
