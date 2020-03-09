package com.aaa.volatileDemo;

/**
 * @ClassName YapHaoTest
 * @Author Adam
 * @Date Create in 2020/2/26  20:08
 * @Description TODO
 *  volatile 的作用数据缓存一致性 store lock writ unlock   可以提高并发效率
 *      当汇编lock指令执行时 会触发cpu总线（嗅探机制）  发现其他线程中工作内存中的数据修store后
 *          会使自己线程工作内存中的值失效
 */
public class Volatile {

    public volatile static boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("waiting data...");
                while(!initFlag){
                }
                System.out.println("*************success");
            }
        }).start();

        Thread.sleep(2000);


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("prepareing data...");
                initFlag=true;
                System.out.println("prepare data end...");
            }
        }).start();
    }

}

