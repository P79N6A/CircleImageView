package com.example.zhangzhixin.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MyScrollActivity extends AppCompatActivity {
    private ScrollView mScroll;
    private LinearLayout mLl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_scroll_layout);
        mScroll = findViewById(R.id.my_scroll);
        mLl = findViewById(R.id.ll);
        for(int i=0;i<40;i++){
            TextView textView=new TextView(this);
            textView.setText("my scroll "+i);
            textView.setTextSize(30);
            mLl.addView(textView);
        }
    }
}
