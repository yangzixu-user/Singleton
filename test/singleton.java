package com.aaa.test;

import java.io.IOException;
import java.util.Properties;

/**
 * @ClassName singleton
 * @Author Adam
 * @Date Create in 2020/2/28  16:33
 * @Description TODO
 */
public class singleton {

    public static singleton INSTANCE;
    private String info;
    static {
      try{
          Properties pro = new Properties();
          pro.load(singleton.class.getClassLoader().getResourceAsStream("singleton.properties"));
          INSTANCE = new singleton(pro.getProperty("Info"));
      }catch (Exception e){
          e.printStackTrace();
      }

    }
    private singleton(String info){
        this.info=info;

    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "singleton{" +
                "info='" + info + '\'' +
                '}';
    }


    public static void main(String[] args) {
        singleton s = singleton.INSTANCE;
        System.out.println(s);
    }
}
