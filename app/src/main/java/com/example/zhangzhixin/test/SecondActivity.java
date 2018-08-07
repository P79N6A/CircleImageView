package com.example.zhangzhixin.test;

import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangzhixin.test.module.Person_module;
import com.example.zhangzhixin.test.ui.MyViewPager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.inject.Inject;


public class SecondActivity extends AppCompatActivity {
    private Button mBtn;
    private TextView mText;
    @Inject
    public Person_module module;
    private MyViewPager mPager;
    private File file;
    private File[] fileList;
    private Button mbtn;
    private ImageView mResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        mbtn = findViewById(R.id.trigger);
        mResult = findViewById(R.id.result);
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    File file1=fileList[fileList.length-2];
                    File output =new File("/storage/emulated/0/Android/data/me.ele.youcai.restaurant/photoCache/mytest.jpg");
                    if(!output.exists()){
                        output.createNewFile();
                        Log.e("create file",""+output.exists());
                    }
                    FileInputStream fileInputStream = new FileInputStream(file1);
                    BufferedInputStream inputStream=new BufferedInputStream(fileInputStream);

                    FileOutputStream fileOutputStream = new FileOutputStream(output);
                    BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);

                    byte [] bytes=new byte[200];
                    while (inputStream.read(bytes,0,bytes.length)!=-1) {
                        bufferedOutputStream.write(bytes,0,bytes.length);
                        Log.e("l am  write ", "");
                    }
                    mResult.setImageBitmap(BitmapFactory.decodeFile(output.getAbsolutePath()));
                } catch (FileNotFoundException e) {
                    Log.e("error ", "" + e.getMessage());

                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("error ", "" + e.getMessage());
                    mBtn.setText(e.getMessage());
                    e.printStackTrace();
                }
                catch (Exception e){
                    Log.e("exception is ", "" + e.getMessage());

                }
            }
        });
//        mPager = findViewById(R.id.list_container);
//        ViewPager  pager = findViewById(R.id.list_container);
//        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
//            @Override
//            public Fragment getItem(int position) {
//                return null;
//            }
//
//            @Override
//            public int getCount() {
//                return 0;
//            }
//
//        });
          file =new File("/storage/emulated/0/Android/data/me.ele.youcai.restaurant/photoCache");
          if(file.isDirectory()){
              Log.e("l can read the directory", "yeah");
              fileList=file.listFiles();
              for(int i=0;i<fileList.length;i++){
                  Log.e("the file name is", ""+fileList[i].getAbsolutePath()+"the name is"+fileList[i].getName());

              }

          }


//        DaggerMy_component.builder()
//                .person_module(new Person_module())
//                .build()
//                .inject(this);
//        module.setAge("20");
//        module.setName("aaa");
//        Toast.makeText(this,module.getName()+module.getAge(),Toast.LENGTH_SHORT).show();
//        EventBus.getDefault().post(new MessaeEvent("this is second Acitivity speaking"));

//        mBtn = findViewById(R.id.btn);
//        mText = findViewById(R.id.value);
//        mBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String str= (String) mText.getText();
//                StringBuilder stringBuilder=new StringBuilder(str+"password");
//                stringBuilder.replace(str.indexOf("username"),str.indexOf("username")+str.length(),"<sig>success</sig>");
//                Toast.makeText(SecondActivity.this,""+stringBuilder.toString(),Toast.LENGTH_SHORT).show();
//                mText.setText(str);
//            }
//        });
    }
}
