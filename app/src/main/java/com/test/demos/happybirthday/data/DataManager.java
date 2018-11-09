package com.test.demos.happybirthday.data;

import android.util.Log;

import com.google.gson.Gson;
import com.test.demos.happybirthday.LogUtil;

import java.util.ArrayList;

public class DataManager {
    public static ArrayList<Strange> datas = new ArrayList<>();
    private static Strange strange;
    public static boolean wait = false;
    public static final float SCALE = 2;

    public static void addPoint(PositionBean bean){
        if(strange == null){
            strange = new Strange();
        }
        strange.addPosition(bean);
    }

    public static void addStrange(String key){
        if(strange != null){
            strange.setKey(key);
            datas.add(strange);
            String str = new Gson().toJson(datas.get(datas.size() - 1));
            LogUtil.v("verf","新添加的字体 ： " + str);
        }
    }

    public static void deleteStrange(){
        strange = new Strange();
    }



}
