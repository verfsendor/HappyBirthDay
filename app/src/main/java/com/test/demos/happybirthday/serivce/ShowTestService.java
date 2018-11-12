package com.test.demos.happybirthday.serivce;
import android.app.IntentService;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.test.demos.happybirthday.data.DataManager;
import com.test.demos.happybirthday.view.AutoDrawTextView;

public class ShowTestService extends IntentService {
     private AutoDrawTextView autoDrawTextView;
     private String drawTxt = "";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     */
    public ShowTestService() {
        super("someName");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.v("verf","ShowTestService onHandleIntent out*******************************************************" );
        while (true){
            setString();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public ShowTestService mMyService;

        public MyBinder() {
            mMyService = ShowTestService.this;
        }

        public ShowTestService getmMyService() {
            return mMyService;
        }
    }

    private void setString(){
        if(autoDrawTextView != null) {
            Log.v("verf","ShowService setstring " + Thread.currentThread().getName());
            DataManager.setTest(autoDrawTextView);
            autoDrawTextView = null;
        }
    }

    public void set(AutoDrawTextView view, String string){
        Log.v("verf","ShowService set " + view + " " + string);
        autoDrawTextView = view;
        drawTxt = string;
    }

    public void setTest(AutoDrawTextView view){
        Log.v("verf","ShowTestService setTest");
        autoDrawTextView = view;
    }
}
