package com.example.zhangzhixin.test.module;

import android.util.Log;

import java.util.Observable;

public class MyObservable extends Observable {

  public void setchange(){
      setChanged();
      Log.e("this is zzx speaking ","can l help you?");
      notifyObservers();
  }


}
