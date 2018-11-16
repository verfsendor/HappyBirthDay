package com.test.demos.happybirthday.data;
import android.util.Log;

import com.google.gson.Gson;
import com.test.demos.happybirthday.LogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Strange implements Serializable {
    private String key;
    private List<PositionBean> positionBeans;
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

    public List<PositionBean> getPositionBeans() {
        return positionBeans;
    }

    public void setPositionBeans(List<PositionBean> positionBeans) {
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

    public static Strange clone(Strange strange){
        Strange x = new Strange();
        x.key = strange.key;
        x.scalue = strange.scalue;
        x.startXScalue = strange.startXScalue;
        x.startYScalue = strange.startYScalue;
        return x;
    }

    public static void LogoutStrange(Strange strange){
        if(strange == null){
            return;
        }

        if(strange.getPositionBeans().size() > 400){
            for(int i=0; i < strange.getPositionBeans().size(); i += 400){
                Strange strange1 = Strange.clone(strange);
                int positon = (i + 400) >= strange.getPositionBeans().size() ? (strange.getPositionBeans().size() - 1) : (i + 400);
                strange1.setPositionBeans(strange.getPositionBeans().subList(i,positon));
                Log.v("verf","输出 " + i + " " + positon + " " + strange1.getPositionBeans().size());
                LogUtil.v("verf","新添加的字体 : " + new Gson().toJson(strange1));
            }
        }else {
            LogUtil.v("verf","新添加的字体 : " + new Gson().toJson(strange));
        }
    }
}
