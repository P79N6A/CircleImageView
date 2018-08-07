//package com.example.zhangzhixin.test.Activity;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.view.Window;
//import android.widget.Button;
//
//import com.example.zhangzhixin.test.MyCallback;
//import com.example.zhangzhixin.test.R;
//import com.google.android.cameraview.CameraView;
//
//import junit.framework.Test;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.Buffer;
//
//public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
//    private Button mSure;
//    private Button mCancel;
//    private CameraView mCameraView;
//    private StringBuilder builder;
//    public  MyCallback myCallback;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.google_cameraview_layout);
//        mSure = findViewById(R.id.sure);
//        mCancel = findViewById(R.id.cancel);
//        mCameraView = findViewById(R.id.camera);
//        mSure.setOnClickListener(this);
//        mCancel.setOnClickListener(this);
//        mCameraView.addCallback(new CameraView.Callback() {
//            @Override
//            public void onPictureTaken(CameraView cameraView, byte[] data) {
//                File file = savePic(data);
//                if (file != null) {
//                    setResult(546,getIntent().putExtra("imgPath",file.getAbsolutePath()));
//                    finish();
//                }
//            }
//        });
//        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA}, 3);
//        }
//
//    }
//
//    private File savePic(byte[] bytes) {
//        BufferedOutputStream bufferedOutputStream = null;
//        try {
//            File file = new File(getImgPath());
//            if (!file.exists()) {
//                if (file.createNewFile()) {
//                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
//                    bufferedOutputStream.write(bytes, 0, bytes.length);
//                    bufferedOutputStream.flush();
//                    bufferedOutputStream.close();
//                    return file;
//                }
//            }
//        } catch (Exception e) {
//            try {
//                bufferedOutputStream.close();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//            return null;
//        }
//        return null;
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mCameraView.start();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (mCameraView.isCameraOpened()) {
//            mCameraView.stop();
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.sure:
//                getImg();
//                break;
//            case R.id.cancel:
//                cancelSave();
//                break;
//
//        }
//    }
//
//    private void getImg() {
//        if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA}, 3);
//        } else {
//            if (mCameraView.isCameraOpened()) {
//                mCameraView.takePicture();
//            }
//        }
//    }
//
//    private void cancelSave() {
//        Log.e("cancel", "click");
//    }
//
//    private String getImgPath() {
//        if (builder == null) {
//            builder = new StringBuilder();
//        }
//        builder.delete(0, builder.length());
//        builder.append(getExternalCacheDir().getAbsoluteFile())
//                .append("/")
//                .append(System.currentTimeMillis())
//                .append(".jpg");
//
//        return builder.toString();
//    }
//
//    public void setMyCallback(MyCallback myCallback) {
//        this.myCallback = myCallback;
//    }
//}
