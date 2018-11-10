package com.test.demos.happybirthday;

import android.app.Application;

import com.test.demos.happybirthday.character.CharacterManager;
import com.test.demos.happybirthday.data.DataManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CharacterManager.init();
        DataManager.init(this);
    }
}
