package com.test.demos.happybirthday.activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.test.demos.happybirthday.R;
import com.test.demos.happybirthday.ShowActivity;
import com.test.demos.happybirthday.character.StrangesBean;
import com.test.demos.happybirthday.data.Constants;
import com.test.demos.happybirthday.data.DataManager;
import com.test.demos.happybirthday.data.Strange;
import com.test.demos.happybirthday.view.DrawRecorderView;

public class MainActivity extends AppCompatActivity {

    Button save;
    Button reset;
    Button next;
    EditText key;
    DrawRecorderView drawRecorderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        save = (Button)findViewById(R.id.btn_save);
        reset = (Button)findViewById(R.id.btn_reset);
        next = (Button)findViewById(R.id.btn_show);
        key = (EditText)findViewById(R.id.edit_key);
        drawRecorderView = (DrawRecorderView)findViewById(R.id.record_view);
        drawRecorderView.setBgColor(true);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.addStrange(key.getText().toString().trim());
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawRecorderView.refreshRecord();
                DataManager.strange = null;
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("verf","下一页");
                startActivity(new Intent(MainActivity.this, ShowActivity2.class));
            }
        });
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("verf","下一页");
                drawRecorderView.refreshRecord();
                key.setText("");
                DataManager.strange = null;
            }
        });
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.addStrange(key.getText().toString().trim());
                startActivity(new Intent(MainActivity.this, ShowActivity3.class));
            }
        });
    }

}
