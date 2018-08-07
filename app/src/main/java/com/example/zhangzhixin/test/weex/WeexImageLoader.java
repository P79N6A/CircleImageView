package com.example.zhangzhixin.test.weex;


import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;

import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;


import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WeexImageLoader implements IWXImgLoaderAdapter {
    private OkHttpClient client = new OkHttpClient();
    @Override
    public void setImage(final String url, final ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Request builder = new Request.Builder().url(url).build();
                try {
                    Response response = client.newCall(builder).execute();
                    final byte[] bytes = response.body().bytes();
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            view.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }
}

