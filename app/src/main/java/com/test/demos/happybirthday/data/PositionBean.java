package com.test.demos.happybirthday.data;

import java.io.Serializable;

public class PositionBean implements Serializable {
    private float x;
    private float y;
    private boolean start;
    private long time;
    public PositionBean(float x, float y){
        this.x = x;
        this.y = y;
    }

    public static PositionBean clone(PositionBean bean){
        PositionBean positionBean = new PositionBean(bean.getX(),bean.getY());
        positionBean.setTime(bean.getTime());
        positionBean.setStart(bean.isStart());
        return positionBean;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}
