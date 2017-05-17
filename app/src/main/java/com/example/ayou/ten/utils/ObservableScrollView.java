package com.example.ayou.ten.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by AYOU on 2017/5/17.
 */

public class ObservableScrollView extends ScrollView {
    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private OnScrollChangeListener changeListener = null;

    public void setScrollViewChangeListener(OnScrollChangeListener changeListener){
        this.changeListener = changeListener;

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (changeListener != null){
            changeListener.onScrollChange(this,l,t,oldl,oldt);
        }
    }
}
