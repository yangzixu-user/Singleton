package com.aaa.test;

/**
 * @ClassName YapHaoTest
 * @Author Adam
 * @Date Create in 2020/2/26  20:08
 * @Description TODO
 */
public class YapHaoTest {


    public static void main(String[] args) {
        YaoHao yaoHao = new YaoHao();
        /*if (null == yaoHao.getName()){
            yaoHao.setName("yangzixu");
        }
        System.out.println(yaoHao.getName());
        yaoHao.setName("yangzixu02");
        System.out.println(yaoHao.getName());*/

        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                if (null == yaoHao.getName()){
                    yaoHao.setName("yangzixu");
                }else {
                    yaoHao.setName("yangzixu"+i);
                }
                System.out.println(yaoHao.getName());
            }
        },"yang1").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                if (null == yaoHao.getName()){
                    yaoHao.setName("yangzixu");
                }else {
                    yaoHao.setName("yangzixu"+i);
                }
                System.out.println(yaoHao.getName());
            }
        },"yang2").start();
        new Thread(()->{
            for (int i = 0; i < 20; i++) {
                if (null == yaoHao.getName()){
                    yaoHao.setName("yangzixu");
                }else {
                    yaoHao.setName("yangzixu"+i);
                }
                System.out.println(yaoHao.getName());
            }
        },"yang3").start();  new Thread(()->{
            for (int i = 0; i < 20; i++) {
                if (null == yaoHao.getName()){
                    yaoHao.setName("yangzixu");
                }else {
                    yaoHao.setName("yangzixu"+i);
                }
                System.out.println(yaoHao.getName());
            }
        },"yang4").start();  new Thread(()->{
            for (int i = 0; i < 20; i++) {
                if (null == yaoHao.getName()){
                    yaoHao.setName("yangzixu");
                }else {
                    yaoHao.setName("yangzixu"+i);
                }
                System.out.println(yaoHao.getName());
            }
        },"yang5").start();


    }
}

