package com.test.demos.happybirthday.activity.scence;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.test.demos.happybirthday.DrawFinsih;
import com.test.demos.happybirthday.R;
import com.test.demos.happybirthday.serivce.ShowService;
import com.test.demos.happybirthday.view.AutoDrawTextView;

/**
 * 通过IntentService来实现,测试具体实现
 */
public class  SenceActivityTwo extends AppCompatActivity {
    AutoDrawTextView autoDrawTextView;
    ShowService showService;

    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(final ComponentName name, final IBinder service) {
            Log.v("verf","绑定成功");
            ShowService.MyBinder myBinder= (ShowService.MyBinder) service;
            if(myBinder != null) {
                showService = myBinder.mMyService;
            }
        }

        @Override
        public void onServiceDisconnected(final ComponentName name) {
            Log.v("verf","解除绑定");
            showService = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sence);
        autoDrawTextView = (AutoDrawTextView)findViewById(R.id.show_view);
        Intent intent = new Intent(SenceActivityTwo.this, ShowService.class);
        startService(intent);
        autoDrawTextView.setBg(true);//
        bindService(intent,mServiceConnection,Context.BIND_AUTO_CREATE);//%d东奕@256,#ffffff,40,0,50,50@%
        final String txt = "%d奕@256,#ffffff,10,0,17,17@%" + "%d生@256,#ffffff,25,0,20,20@%" + "%d日@256,#ffffff,40,0,20,20@%" + "%d快@256,#ffffff,58,0,20,20@%" +"%d乐@256,#ffffff,73,0,21,21@%"
                + "%d东奕@256,#ffffff,0,10,100,100@%";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(200);
                    if(showService != null){
                        //     * %日出@100,#234566,0,0,50,50@%
                        showService.set(autoDrawTextView, txt, new DrawFinsih() {
                            @Override
                            public void finish() {
                                startActivity(new Intent(SenceActivityTwo.this,SenceActivityThree.class));
                            }
                        });
                    }else {
                        Toast.makeText(SenceActivityTwo.this,"尚未建立绑定",Toast.LENGTH_LONG).show();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("kkk","showActivity2 ondestory");
        try {
            unbindService(mServiceConnection);
            stopService(new Intent(SenceActivityTwo.this,ShowService.class));
        } catch (Exception e) {
            Toast.makeText(SenceActivityTwo.this,"请先绑定服务再执行解绑",Toast.LENGTH_LONG).show();
        }
    }

}
