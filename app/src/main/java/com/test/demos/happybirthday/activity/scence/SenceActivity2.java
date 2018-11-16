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
public class SenceActivity2 extends AppCompatActivity {
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
        setContentView(R.layout.activity_show);
        autoDrawTextView = (AutoDrawTextView)findViewById(R.id.show_view);
        Intent intent = new Intent(SenceActivity2.this, ShowService.class);
        startService(intent);
        autoDrawTextView.setBg(true);
        bindService(intent,mServiceConnection,Context.BIND_AUTO_CREATE);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(200);
                    if(showService != null){
                        //     * %日出@100,#234566,0,0,50,50@%
                        showService.set(autoDrawTextView, "可爱的阿奕宝贝:%/n% 生日%happy%！想了很久很久还是不知道该送什么,东东准备了好多天,想来还是写个%app%吧,独家研制的会自己写字的,这个上面的每一个字都是我一个一个录数据到字体库里面再播放出来的.然后花费了好多脑细胞和两个周末和%10%几个午休和晚上的时间,祝我的阿奕永远开心快乐,东东会永远陪着你.", new DrawFinsih() {
                            @Override
                            public void finish() {
                                startActivity(new Intent(SenceActivity2.this,SenceActivity.class));
                            }
                        });
                    }else {
                        Toast.makeText(SenceActivity2.this,"尚未建立绑定",Toast.LENGTH_LONG).show();
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
            stopService(new Intent(SenceActivity2.this,ShowService.class));
        } catch (Exception e) {
            Toast.makeText(SenceActivity2.this,"请先绑定服务再执行解绑",Toast.LENGTH_LONG).show();
        }
    }

}
