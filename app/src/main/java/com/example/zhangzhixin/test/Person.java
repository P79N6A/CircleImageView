package com.example.zhangzhixin.test;

import android.util.Log;

public class Person {
    String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static int  add(int a, int b){
        int c=a+b;
        System.out.println("开始");
        return c;
    }
}
