package com.test.demos.happybirthday.character;

import android.util.Log;

import com.google.gson.Gson;
import com.test.demos.happybirthday.LogUtil;
import com.test.demos.happybirthday.data.Strange;

import java.util.ArrayList;
import java.util.HashMap;

public class CharacterManager {
    private static HashMap<String, Strange> strangeHashMap;
//    private static StrangesBean strangesBean;
    private static Strange lastStrange;
    public static void init(){
        strangeHashMap = new HashMap<>();
//        strangesBean = new StrangesBean();
        Character1.init();
        Character2.init();
        Character3.init();
        Character4.init();
    }

    public static void addStrange(Strange strange){
        Log.v("verf","添加strange " );
        if(strange == null){
            return;
        }
        if(lastStrange != null && lastStrange.getKey().equals(strange.getKey())){
            Log.v("verf","添加strange key相等 " + strange.getPositionBeans().size() + " " + lastStrange.getPositionBeans().size());
            lastStrange.getPositionBeans().addAll(strange.getPositionBeans());
            strange = lastStrange;
            strangeHashMap.remove(strange.getKey());
        }else {
            lastStrange = strange;
        }
        Log.v("verf","添加Strange " + strange.getKey() + " " + strange.getPositionBeans().size());
//        strangesBean.getStrangeArrayList().add(strange);
        strangeHashMap.put(strange.getKey(),strange);
    }

    public static void addStranges(ArrayList<Strange> stranges){
        for(Strange strange : stranges){
            strangeHashMap.put(strange.getKey(), strange);
        }
    }

    public static Strange getStrange(String key){
        Strange strange = strangeHashMap.get(key);
        if(strange != null){
            return strange;
        }
        Log.v("verf","字体库找不到 " + key );
        return null;
    }

    public static void addString(String txt){
        StrangesBean bean = new Gson().fromJson(txt, StrangesBean.class);
        if(bean != null){
            Log.v("verf","添加stranges啦");
            addStranges(bean.getStrangeArrayList());
            return;
        }

    }

    public static void addStrangeString(String txt){
        Log.v("verf","添加strange啦1");
        Strange strange = new Gson().fromJson(txt, Strange.class);
        if(strange != null){
            Log.v("verf","添加strange啦2");
            addStrange(strange);
        }
    }
}
