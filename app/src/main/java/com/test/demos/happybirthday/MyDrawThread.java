package com.test.demos.happybirthday;

import android.os.HandlerThread;

import java.util.logging.Handler;

public class MyDrawThread extends HandlerThread {
    public MyDrawThread(String name) {
        super(name);
    }
}
