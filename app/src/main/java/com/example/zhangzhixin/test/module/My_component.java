package com.example.zhangzhixin.test.module;

import android.content.Context;

import com.example.zhangzhixin.test.SecondActivity;

import dagger.Component;

@Component(modules = Person_module.class)
public interface My_component {
    void inject(SecondActivity secondActivity);
}
