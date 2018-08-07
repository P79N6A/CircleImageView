//package com.example.zhangzhixin.test.Activity;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Matrix;
//import android.media.ExifInterface;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//
//import com.example.zhangzhixin.test.R;
//
//import java.io.IOException;
//
//import static android.Manifest.permission.CAMERA;
//
//public class TestCameraActivity extends AppCompatActivity {
//    private ImageView mResult;
//    private Button mBtn;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test_camera_layout);
//        mResult = findViewById(R.id.result);
//        mBtn = findViewById(R.id.go_camera);
//        mBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                    Intent intent = new Intent(TestCameraActivity.this, CameraActivity.class);
//                    startActivityForResult(intent, 1);
//                } else {
//                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 0X0011);
//                }
//            }
//        });
//
//
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
//        Log.e("activity result", "get");
//        if (resultCode == 546) {
//            String filePath = (String) data.getExtras().get("imgPath");
//            if (!filePath.equals("")) {
//                try {
//                    ExifInterface exifInterface = new ExifInterface(filePath);
//                    int degree = 0;
//                    int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//                    switch (orientation) {
//                        case ExifInterface.ORIENTATION_ROTATE_90:
//                            degree = 90;
//                            break;
//                        case ExifInterface.ORIENTATION_ROTATE_180:
//                            degree = 180;
//                            break;
//                        case ExifInterface.ORIENTATION_ROTATE_270:
//                            degree = 270;
//                            break;
//                    }
//                    Matrix matrix = new Matrix();
//                    matrix.postRotate(degree);
//                    BitmapFactory.Options options = new BitmapFactory.Options();
//                    options.inJustDecodeBounds = true;
//                    BitmapFactory.decodeFile(filePath, options);
//                    options.inSampleSize =getinSampleSize(options,mResult.getWidth(),mResult.getHeight());
//                    options.inJustDecodeBounds =false;
//                    Bitmap bitmap =BitmapFactory.decodeFile(filePath,options);
//                    Bitmap bitmap1=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
//                    if(bitmap!=null) {
//                        bitmap.recycle();
//                    }
//                    mResult.setImageBitmap(bitmap1);
////                    bitmap.recycle();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//    }
//
//    public int  getinSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
//        int outWidth = options.outWidth;
//        int outHeight = options.outHeight;
//        int inSample = 1;
//        if(outWidth>reqWidth || outHeight>reqHeight){
//            final int halfwidth = outWidth/2;
//            final int halfheight = outHeight /2;
//            while((halfheight/inSample)>reqHeight&&halfwidth/inSample>reqWidth){
//                inSample *=2;
//            }
//        }
//        return inSample;
//
//    }
//}
