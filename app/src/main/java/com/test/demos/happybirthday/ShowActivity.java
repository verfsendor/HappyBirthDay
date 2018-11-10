package com.test.demos.happybirthday;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.demos.happybirthday.data.DataManager;
import com.test.demos.happybirthday.data.PositionBean;
import com.test.demos.happybirthday.view.AutoDrawTextView;

public class ShowActivity extends AppCompatActivity {
    AutoDrawTextView autoDrawTextView;
    Button show;
    TextView tvTxt;
    Thread thread = new Thread(){
        PositionBean bean;
        @Override
        public void run() {
            super.run();
            if(DataManager.datas != null){
                for(int i = 0; i < DataManager.datas.size(); i ++){
                    final String key = DataManager.datas.get(i).getKey();
                    tvTxt.post(new Runnable() {
                        @Override
                        public void run() {
                            tvTxt.setText(key);
                        }
                    });
                    for(int j = 0; j < DataManager.datas.get(i).getPositionBeans().size(); j ++){
                        bean = DataManager.datas.get(i).getPositionBeans().get(j);
                        try {
                            while (DataManager.wait){
                                sleep(10);
                            }
                            autoDrawTextView.showPoint(PositionBean.clone(bean));
                            DataManager.wait = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show);
        show = (Button)findViewById(R.id.btn_show);
        autoDrawTextView = (AutoDrawTextView)findViewById(R.id.show_view);
        tvTxt = (TextView)findViewById(R.id.tv_txt);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!thread.isAlive()){
                    thread.start();
                }
            }
        });
    }
}
