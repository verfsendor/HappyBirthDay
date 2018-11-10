package com.test.demos.happybirthday.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.test.demos.happybirthday.R;
import com.test.demos.happybirthday.data.Constants;
import com.test.demos.happybirthday.data.DataManager;
import com.test.demos.happybirthday.view.AutoDrawTextView;

public class ShowActivity1 extends AppCompatActivity {
    AutoDrawTextView autoDrawTextView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        autoDrawTextView = (AutoDrawTextView)findViewById(R.id.show_view);
        final Handler handler = new Handler(DataManager.myDrawThread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String string = msg.getData().getString(Constants.SHOW_STR,"");
                DataManager.sendString(autoDrawTextView,string);
            }
        };
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString(Constants.SHOW_STR,"朱元璋朱元璋朱元璋朱元璋朱元璋");
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
    }
}