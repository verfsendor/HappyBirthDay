package com.test.demos.happybirthday.data;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.test.demos.happybirthday.LogUtil;
import com.test.demos.happybirthday.MyDrawThread;
import com.test.demos.happybirthday.character.CharacterManager;
import com.test.demos.happybirthday.view.AutoDrawTextView;

import java.util.ArrayList;

/**
 * %日出@100,#234566,0,0,50,50@%开始开工上课就是公开课我其实就是这样觉得的但是我真的不知道怎么办法呀
 */
public class DataManager {
    public static Strange testStrange;
    public static ArrayList<Strange> datas = new ArrayList<>();
    public static Strange strange;
    public static boolean wait = false;
    private static String showStranges ="";
    private static boolean addShowStranges;
    public static MyDrawThread myDrawThread = new MyDrawThread("myDrawThread");
    public static int windowWidth;
    public static int windowHeight;


    public static int scalueX = 70;
    public static int scalueY = 70;

    public static void init(Context context){
        myDrawThread.start();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        windowWidth = dm.widthPixels;         // 屏幕宽度（像素）
        windowHeight = dm.heightPixels;       // 屏幕高度（像素）
    }

    public static void addPoint(PositionBean bean){
        if(strange == null){
            strange = new Strange();
        }
        strange.addPosition(bean);
    }

    public static void addStrange(String key){
        if(strange != null){
            strange.setKey(key);
            for(Strange strange : datas){
                if(strange.getKey().equals(key)){
                    datas.remove(strange);
                }
            }
            datas.add(strange);
            testStrange = strange;
            Strange.LogoutStrange(strange);
        }
        CharacterManager.addStrange(strange);
        strange = new Strange();
    }

    public static void deleteStrange(){
        strange = new Strange();
    }

    public static void sendString(AutoDrawTextView view, String str){
        Log.v("verf","子线程接收到数据啦 "  + Thread.currentThread().getName() + " " + str);
        for(int i = 0; i < str.length(); i ++){
            if(str.charAt(i) == '%'&& showStranges.length() == 0){
                addShowStranges = true;
                continue;
            }
            if(str.charAt(i) == '%'&& addShowStranges){
                addShowStranges = false;
                searchKey(view, showStranges);
                showStranges = "";
                continue;
            }
            if(addShowStranges){
                showStranges += ("" + str.charAt(i));
            }else {
                searchKey(view,"" + str.charAt(i));
            }
        }
    }
    /**
     * %日出@100,#234566,0,0,50,50@%
     */
    public static void searchKey(AutoDrawTextView view,String key){
        Log.v("verf","子线程开始搜索啦 "  + Thread.currentThread().getName() + " " + key);
        String searStr = key;
        String color = "#ffffff";
        int alpha = 100;
        int startx = -1;
        int starty = -1;
        int scaluex = scalueX;
        int scaluey = scalueY;
        if(key.length() > 1 && key.contains("@")){
           String [] strings = key.split("@");
           searStr = strings[0];
           if(strings != null && strings.length >= 2 && strings[1].length() > 0 && strings[1].contains(",")){
               String [] parms = strings[1].split(",");
               Log.v("verf","解析结果1 " + parms.length);
               if(parms != null && parms.length == 6){
                   color = parms[1];
                   alpha = Integer.parseInt(parms[0]);
                   startx = Integer.parseInt(parms[2]);
                   starty = Integer.parseInt(parms[3]);
                   scaluex = Integer.parseInt(parms[4]);
                   scaluey = Integer.parseInt(parms[5]);
                   setDemison(scalueX,scalueY);
                   Log.v("verf","解析结果2 " + color + " " + alpha + " " + startx+ " " + starty + " " + scaluex + " " + scaluey);
               }
           }
        }
        view.setPaint(alpha,color);
        Strange strange = CharacterManager.getStrange(searStr);
        if("/n".equals(searStr)){
            PositionBean positionBean = new PositionBean(0,0);
            positionBean.setAction("换行");
            view.showPoint(positionBean);
            return;
        }
        if(strange == null || strange.getPositionBeans() == null){
            return;
        }
        Log.v("verf","子线程开始绘画啦 "  + Thread.currentThread().getName() + " " + key);
        for(int i = 0; i < strange.getPositionBeans().size(); i ++) {
            try {
                while (wait) {
                    Thread.currentThread().sleep(1);
                }
                PositionBean clone = PositionBean.clone(strange.getPositionBeans().get(i));
                if(i == 0){
                    clone.setCharacterStart(true);
                }
                clone.setStartx(startx);
                clone.setStarty(starty);
                clone.setScaluex(scaluex);
                clone.setScaluey(scaluey);
                view.showPoint(clone);
                DataManager.wait = true;
                Log.v("kkk","sending " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void setTest(AutoDrawTextView view){
        Log.v("verf","子线程开始搜索啦 "  + Thread.currentThread().getName() + " ");
        String color = "#000000";
        int alpha = 100;
        int startx = -1;
        int starty = -1;
        int scaluex = scalueX;
        int scaluey = scalueY;

        view.setPaint(alpha,color);
        Strange strange = testStrange;
        if(strange == null || strange.getPositionBeans() == null){
            return;
        }
        Log.v("verf","子线程开始绘画啦 ");
        for(int i = 0; i < strange.getPositionBeans().size(); i ++) {
            try {
                while (wait) {
                    Thread.currentThread().sleep(1);
                }
                PositionBean clone = PositionBean.clone(strange.getPositionBeans().get(i));
                if(i == 0){
                    clone.setCharacterStart(true);
                }
                clone.setStartx(startx);
                clone.setStarty(starty);
                clone.setScaluex(scaluex);
                clone.setScaluey(scaluey);
                view.showPoint(clone);
                DataManager.wait = true;
                Log.v("kkk","sending " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setDemison(int x, int y){
        scalueX = x;
        scalueY = y;
    }
}
