package com.example.ayou.ten.Event;

/**
 * Created by AYOU on 2017/5/18.
 */

public class FontEvent extends BaseEvent {
    private float textsize;

    public float getTextsize() {
        return textsize;
    }

    public void setTextsize(float textsize) {
        this.textsize = textsize;
    }

    public FontEvent(int what) {
        super(what);
    }
}
