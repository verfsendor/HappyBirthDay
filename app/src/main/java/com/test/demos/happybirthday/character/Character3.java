package com.test.demos.happybirthday.character;
import com.google.gson.Gson;

public class Character3 {
    /**
     * 字体：朱 生 日 乐
     */
    public static String txt1 = "";
    public static String txt2 = "";

    public static void init(){
        StrangesBean bean1 = new Gson().fromJson(txt1, StrangesBean.class);
        if(bean1 != null){
            CharacterManager.addStranges(bean1.getStrangeArrayList());
        }
        StrangesBean bean2 = new Gson().fromJson(txt2, StrangesBean.class);
        if(bean2 != null){
            CharacterManager.addStranges(bean2.getStrangeArrayList());
        }
    }

}
