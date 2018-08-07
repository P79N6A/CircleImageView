package com.example.zhangzhixin.test.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.zhangzhixin.test.R;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

public class WeexActivity extends AppCompatActivity implements IWXRenderListener {
    private WXSDKInstance mWXSDKinstance;
    private Button mBtn;
    private LinearLayout mWeexLl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weex_layout_test);
        mBtn = findViewById(R.id.get_weex);
        mWeexLl = findViewById(R.id.weex_container);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWXSDKinstance = new WXSDKInstance(WeexActivity.this);
                mWXSDKinstance.registerRenderListener(WeexActivity.this);
                mWXSDKinstance.render("weex",
                        WXFileUtils.loadAsset("index.js",WeexActivity.this),
                        null, null, -1,-1,WXRenderStrategy.APPEND_ASYNC);
            }
        });
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
          mWeexLl.addView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {
        Toast.makeText(WeexActivity.this,"js file load success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        if(mWXSDKinstance!=null) {
            mWXSDKinstance.onActivityResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mWXSDKinstance!=null) {
            mWXSDKinstance.onActivityStop();
        }
    }
}
