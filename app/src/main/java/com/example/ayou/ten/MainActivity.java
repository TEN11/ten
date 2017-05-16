package com.example.ayou.ten;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;

import com.example.ayou.ten.fragment.ActrialFragment;
import com.example.ayou.ten.fragment.MovieFragment;
import com.example.ayou.ten.fragment.PersonalFragment;
import com.example.ayou.ten.fragment.PicFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_movie)
    RadioButton mainMovie;
    @BindView(R.id.main_artical)
    RadioButton mainArtical;
    @BindView(R.id.main_pic)
    RadioButton mainPic;
    @BindView(R.id.main_personal)
    RadioButton mainPersonal;

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

        initView();
    }

    private void initView() {
        mainMovie.setChecked(true);

    }


    @OnClick({R.id.main_movie, R.id.main_artical, R.id.main_pic, R.id.main_personal})
    public void onClick(View view) {

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        if (movieFragment!=null){
            transaction.hide(movieFragment);
        }
        if (actrialFragment != null){
            transaction.hide(actrialFragment);
        }
        if (picFragment!=null){
            transaction.hide(picFragment);
        }
        if (personalFragment != null){
            transaction.hide(personalFragment);
        }

        switch (view.getId()) {
            case R.id.main_movie:
                if (movieFragment == null){
                    movieFragment = new MovieFragment();
                    transaction.add(R.id.main_fl,movieFragment);
                }
                transaction.show(movieFragment);
                break;
            case R.id.main_artical:
                if (actrialFragment == null){
                    actrialFragment = new ActrialFragment();
                    transaction.add(R.id.main_fl,actrialFragment);
                }
                transaction.show(actrialFragment);
                break;
            case R.id.main_pic:
                if (picFragment == null){
                    picFragment = new PicFragment();
                    transaction.add(R.id.main_fl,picFragment);
                }
                transaction.show(picFragment);
                break;
            case R.id.main_personal:
                if (personalFragment == null){
                    personalFragment = new PersonalFragment();
                    transaction.add(R.id.main_fl,personalFragment);
                }
                transaction.show(personalFragment);
                break;
        }
        transaction.commit();
    }
}
