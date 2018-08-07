package com.example.zhangzhixin.test.module;

import dagger.Module;
import dagger.Provides;

@Module
public class Person_module {
    private String name;
    private String age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Provides
    public  Person_module getInstance(){
        return new Person_module();
    }
}
