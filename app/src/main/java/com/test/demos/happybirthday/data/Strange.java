package com.test.demos.happybirthday.data;
import java.io.Serializable;
import java.util.ArrayList;

public class Strange implements Serializable {
    private String key;
    private ArrayList<PositionBean> positionBeans;

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
}
