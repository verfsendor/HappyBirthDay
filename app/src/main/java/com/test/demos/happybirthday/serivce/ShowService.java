package com.test.demos.happybirthday.serivce;
import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.test.demos.happybirthday.data.DataManager;
import com.test.demos.happybirthday.view.AutoDrawTextView;

public class ShowService extends IntentService {
     private AutoDrawTextView autoDrawTextView;
     private String drawTxt = "";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ShowService() {
        super("someName");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.v("verf","ShowService onHandleIntent out*******************************************************" );
        while (true){
            Log.v("verf","ShowService setstring out" );
            setString();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public ShowService mMyService;

        public MyBinder() {
            mMyService = ShowService.this;
        }

        public ShowService getmMyService() {
            return mMyService;
        }
    }

    private void setString(){
        if(autoDrawTextView != null && drawTxt != null && drawTxt.length() > 0) {
            Log.v("verf","ShowService setstring " + Thread.currentThread().getName());
            DataManager.sendString(autoDrawTextView, drawTxt);
            autoDrawTextView = null;
            drawTxt = "";
        }
    }

    public void set(AutoDrawTextView view, String string){
        Log.v("verf","ShowService set " + view + " " + string);
        autoDrawTextView = view;
        drawTxt = string;
    }

}
