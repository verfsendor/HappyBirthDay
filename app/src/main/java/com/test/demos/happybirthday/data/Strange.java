package com.test.demos.happybirthday.data;
import java.io.Serializable;
import java.util.ArrayList;

public class Strange implements Serializable {
    private String key;
    private ArrayList<PositionBean> positionBeans;
    private float scalue = -1;
    private float startXScalue = -1;
    private float startYScalue = -1;

    public Strange(){
        positionBeans = new ArrayList<>();
        key = "";
    }

    public void addPosition(PositionBean bean){
        if(positionBeans == null){
            positionBeans = new ArrayList<>();
        }
        positionBeans.add(bean);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<PositionBean> getPositionBeans() {
        return positionBeans;
    }

    public void setPositionBeans(ArrayList<PositionBean> positionBeans) {
        this.positionBeans = positionBeans;
    }

    public float getScalue() {
        return scalue;
    }

    public void setScalue(float scalue) {
        this.scalue = scalue;
    }

    public float getStartXScalue() {
        return startXScalue;
    }

    public void setStartXScalue(float startXScalue) {
        this.startXScalue = startXScalue;
    }

    public float getStartYScalue() {
        return startYScalue;
    }

    public void setStartYScalue(float startYScalue) {
        this.startYScalue = startYScalue;
    }
}
