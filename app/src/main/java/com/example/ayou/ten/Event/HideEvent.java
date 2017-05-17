package com.example.ayou.ten.Event;

/**
 * Created by AYOU on 2017/5/17.
 */

public class HideEvent extends BaseEvent {
    private boolean isUP;

    public boolean isUP() {
        return isUP;
    }

    public void setUP(boolean UP) {
        isUP = UP;
    }

    public HideEvent(int what) {
        super(what);
    }
}
