package com.test.demos.happybirthday.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.test.demos.happybirthday.R;
import com.test.demos.happybirthday.serivce.ShowService;
import com.test.demos.happybirthday.serivce.ShowTestService;
import com.test.demos.happybirthday.view.AutoDrawTextView;

/**
 * 通过IntentService来实现
 */
public class ShowActivity3 extends AppCompatActivity {
    AutoDrawTextView autoDrawTextView;
    ShowTestService showService;

    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(final ComponentName name, final IBinder service) {
            Log.v("verf","绑定成功");
            ShowTestService.MyBinder myBinder= (ShowTestService.MyBinder) service;
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
        Intent intent = new Intent(ShowActivity3.this, ShowTestService.class);
        startService(intent);
        bindService(intent,mServiceConnection,Context.BIND_AUTO_CREATE);
        findViewById(R.id.btn_bind).setVisibility(View.GONE);
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showService != null){
                    showService.setTest(autoDrawTextView);
                }else {
                    Toast.makeText(ShowActivity3.this,"尚未建立绑定",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("kkk","showActivity2 ondestory");
        try {
            unbindService(mServiceConnection);
            stopService(new Intent(ShowActivity3.this,ShowTestService.class));
        } catch (Exception e) {
            Toast.makeText(ShowActivity3.this,"请先绑定服务再执行解绑",Toast.LENGTH_LONG).show();
        }
    }

}
