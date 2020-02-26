package com.aaa.test;

import com.aaa.Singleton.HungerSingleton;

public class HungerTest {
    public static void main(String[] args) {
        for (int i = 0 ; i < 20 ;i++){
           new Thread(()->{
               System.out.println(Thread.currentThread().getName()+ HungerSingleton.gitInstance());
           },"aaa").start();
        }
    }
}
