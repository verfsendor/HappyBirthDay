package com.test.demos.happybirthday.data;

import android.util.Log;

import java.io.Serializable;

public class PositionBean implements Serializable {
    private float x;
    private float y;
    private boolean start;
    private boolean characterStart;
    private String action;
    private long time;
    private int startx = -1;
    private int starty = -1;
    private int scaluex = -1;
    private int scaluey = -1;

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

    public boolean isCharacterStart() {
        return characterStart;
    }

    public void setCharacterStart(boolean characterStart) {
        this.characterStart = characterStart;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getX() {
        if(scaluex > 0){
            return x * scaluex/100;
        }
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        if(scaluey > 0){
            return y * scaluey/100;
        }
        return y;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public int getStartx() {
        return DataManager.windowWidth * startx /100;
    }

    public void setStartx(int startx) {
        this.startx = startx;
    }

    public int getStarty() {
        return DataManager.windowHeight * starty /100;
    }

    public void setStarty(int starty) {
        this.starty = starty;
    }

    public int getScaluex() {
        return scaluex;
    }

    public void setScaluex(int scaluex) {
        this.scaluex = scaluex;
    }

    public int getScaluey() {
        return scaluey;
    }

    public void setScaluey(int scaluey) {
        this.scaluey = scaluey;
    }
}
