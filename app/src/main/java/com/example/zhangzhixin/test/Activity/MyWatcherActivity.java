package com.example.zhangzhixin.test.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.zhangzhixin.test.module.MyObeserve;
import com.example.zhangzhixin.test.module.MyObservable;

public class MyWatcherActivity extends AppCompatActivity {
    private MyObeserve myObeserve;
    private MyObservable observable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myObeserve=new MyObeserve();
        observable=new MyObservable();

    }

    @Override
    protected void onResume() {
        super.onResume();
        observable.addObserver(myObeserve);
        observable.setchange();
    }
}
