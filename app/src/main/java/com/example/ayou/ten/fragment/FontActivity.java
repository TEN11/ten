package com.example.ayou.ten.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ayou.ten.BaseActivity;
import com.example.ayou.ten.MainActivity;
import com.example.ayou.ten.R;
import com.example.ayou.ten.utils.SharePrefrencesConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FontActivity extends BaseActivity {

    @BindView(R.id.font_back)
    ImageView fontBack;
    @BindView(R.id.font_rl)
    RelativeLayout fontRl;
    @BindView(R.id.font_content)
    TextView fontContent;
    @BindView(R.id.font_rbs)
    RadioButton fontRbs;
    @BindView(R.id.font_rbm)
    RadioButton fontRbm;
    @BindView(R.id.font_rbl)
    RadioButton fontRbl;
    @BindView(R.id.font_rg)
    RadioGroup fontRg;
    private float textsize;

    private boolean isChange;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        ButterKnife.bind(this);

        SharedPreferences preferences = getSharedPreferences(SharePrefrencesConfig.CHANGE,MODE_PRIVATE);
        state = preferences.getInt("state",1);


        switch (state){
            case 1:
                fontRg.check(R.id.font_rbs);
                break;
            case 2:
                fontRg.check(R.id.font_rbm);
                break;
            case 3:
                fontRg.check(R.id.font_rbl);
                break;
        }


    }

    @OnClick({R.id.font_rbs, R.id.font_rbm, R.id.font_rbl, R.id.font_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.font_rbs:
                textsize = 1.0f;
                state = 1;
                break;
            case R.id.font_rbm:
                textsize = 1.2f;
                state = 2;
                break;
            case R.id.font_rbl:
                textsize = 1.5f;
                state = 3;
                break;
            case R.id.font_back:
                finish();
                isChange = true;
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

        if (!isChange) {
            SharedPreferences preferences = getSharedPreferences(SharePrefrencesConfig.CHANGE,MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putInt("state",state);
            edit.commit();
            //改变字体大小
            Resources res = getResources();
            Configuration config = res.getConfiguration();
            config.fontScale = textsize;
            res.updateConfiguration(config, res.getDisplayMetrics());
            recreate();
        }
    }



}
