package com.test.demos.happybirthday.character;

import android.util.Log;

import com.google.gson.Gson;
import com.test.demos.happybirthday.LogUtil;
import com.test.demos.happybirthday.data.Strange;

import java.util.ArrayList;
import java.util.HashMap;

public class CharacterManager {
    private static HashMap<String, Strange> strangeHashMap;
    private static StrangesBean strangesBean;
    public static void init(){
        strangeHashMap = new HashMap<>();
        strangesBean = new StrangesBean();
        Character1.init();
    }

    public static void addStrange(Strange strange){
        if(strange == null){
            return;
        }
        strangesBean.getStrangeArrayList().add(strange);
        LogUtil.v("verf","所有的数据：***** " + strangesBean.getStrangeArrayList().size() + " "
                + strangesBean.getStrangeArrayList().get(strangesBean.getStrangeArrayList().size() - 1).getKey() + " "
                + new Gson().toJson(strangesBean));
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
}
