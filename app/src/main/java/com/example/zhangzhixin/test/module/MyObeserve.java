package com.example.zhangzhixin.test.module;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;

public class MyObeserve  implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        Log.e("l can received"," l want a juice");
    }
}
