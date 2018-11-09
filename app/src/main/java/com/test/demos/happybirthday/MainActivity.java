package com.test.demos.happybirthday;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.test.demos.happybirthday.data.DataManager;
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
        next = (Button)findViewById(R.id.btn_next);
        key = (EditText)findViewById(R.id.edit_key);
        drawRecorderView = (DrawRecorderView)findViewById(R.id.record_view);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.addStrange(key.getText().toString().trim());
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.addStrange(key.getText().toString().trim());
                drawRecorderView.refreshRecord();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
            }
        });
    }

}
