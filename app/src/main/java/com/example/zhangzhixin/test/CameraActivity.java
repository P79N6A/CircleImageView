package com.example.zhangzhixin.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.File;

public class CameraActivity extends AppCompatActivity {

    private Button mBtn;
    private ImageView mResult;
    private String imgFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_test);
        mBtn = findViewById(R.id.go_to_camera);
        mResult = findViewById(R.id.result);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(CameraActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CameraActivity.this, new String[]{Manifest.permission.CAMERA}, 3);
                } else {
                    getpic();
                }
            }
        });
    }

    public String getPath() {
        String str = getExternalCacheDir().getAbsolutePath();
        imgFile = str + "/" + System.currentTimeMillis() + ".jpg";
        return imgFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            mResult.setImageBitmap(BitmapFactory.decodeFile(imgFile));

    }

    public void getpic() {
        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
        String tempFilePath = getPath();
////                FileUtil.deleteFile(tempFilePath);
//                i.putExtra("output", Uri.fromFile(new File(tempFilePath)));
//                startActivityForResult(i, 546);
//                i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        File file = new File(tempFilePath);
        Uri contentUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".fileProvider", file);
        i.putExtra("output", contentUri);
        startActivityForResult(i, 546);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 3) {
            getpic();

        }
    }
}
