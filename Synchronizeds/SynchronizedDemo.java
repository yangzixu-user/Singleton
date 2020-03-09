package com.aaa.Synchronizeds;
/**
 * 一定要花功夫去研究业务逻辑(三年之内写的都是业务逻辑)
 * 在研究公司的业务逻辑的时候，一定要跟进和提升java基础
 * 哪一门语言其实都是相同的，只要精通一门语言，其他的计算机都是类似
 *  推荐看《Java编程思想》（每次看这本书都对java基础有很多的提升）
 *
 */

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SynchronizedDemo
 * @Author Adam
 * @Date Create in 2020/3/3  19:24
 * @Description TODO
 *      当当线程需要处理并发的时候，但是有些时候不能一块处理
 *      （下订单）--->但是java中的所有的方法和类全都是并行执行的--->Synchronized
 *
 *
 */
public class SynchronizedDemo {
    /**
     * Synchronized来修饰静态方法
     *      静态方法跟对象没有关系，并且静态方法会进入共享缓存中
     *      它会直接属于这个类--->这个类加载的时候这个方法一定会被加载
     *
     *      锁的用法就是让所有的线程实现串行执行，让线程相当于排队，不再一起执行
     *
     *      静态的是一个一个的执行，非静态的是一起执行的（因为非静态的需要创建对象）--->创建了五个对象
     *      由类创建的对象，之间是没有关系的，也就是互不干涉的
     *
     *
     *
     */
    public  static void accessResource1(){
        synchronized (SynchronizedDemo.class){
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("线程名称"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * synchronized 修饰非静态的方法
     */
    public synchronized void accessResource2(){
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("线程名称"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用synchronized修饰代码块
     *  使用synchronized修饰代码块的时候，这里的this代表了什么？
     *      这里的this在上一次课程中代表的当前线程
     *      今天这个this代表了SynchronizedDemo类
     *      上一次课程中synchronized写载了run()方法中，代表了当前线程所要运行的业务逻辑
     *      一旦synchronized写在run()方法中的时候，就会改变把锁定的对象改变成当前线程。
     *      今天这里的this代表类当前本身区(不对)
     *
     *
     *      有个疑问：
     *          synchronized修饰代码块，写在静态方法中该如何去写？
     *              ！！！结论：synchronized永远不能修饰静态方法中的代码（前提是括号里面写的是this）
     *
     *            正确结论：
     *              this代表了这个类所产生的对象
     *              静态方法不属于对象，属于类。也就是说对象中根本没有这个静态方法，所以会报错
     *              但是当把这个this变成锁住类的时候，在静态方法中就不会报错
     *              也就是说当静态方法来用synchronized关键字的时候，只有在括号里写类.class，不能写this
     *
     *              当括号找括号中写类.class的时候，这个时候即代表类又代表类创建出来的所有对象。
     *              面试架构师三个点：
     *                  1.如何在静态方法中使用synchronized修饰代码块：括号中要使用类.class,不能使用this
     *                  2.括号中this代表了什么：this代表了类所创建出的对象
     *                  3.括号中类.class代表的又是什么：类.class代表的是 类和类所创建的所有对象
     *
     *
     */
    public  void accessResource3(){
        synchronized (this){
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("线程名称"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 锁了类和锁this的区别：
     *     使用synchronized修饰代码块
     *      锁这个this代表对象
     *      锁这个类 即代表了对象又代表了类
     *
     *      Class/Clazz--->其实是所有对象的底层（包括Object）
     *          换句话说所有的类都可以强转成Class对象（在反射中体现）(有一个对象就叫Class)
     *
     *      其实SynchronizedDemo就是一个Class的类型（.java--->.class--->classloader--->Class类型的对象）
     *      那么也就是说当我区锁synchronizedDemo的时候，就相当于把这个类锁住了
     *      包括这个类所产生的所有的对象也会一起锁住
     *
     *      问题:类把自己锁住有什么作用
     *          每一个请求都是一个线程：每一个线程都又是通过对象/类创建出来的
     *          就相当于把所有的线程都锁住了，不再让这些线程实现并行执行，让他们排队，串行执行
     *          锁住方法是绝对不可以的？
     *              因为每一个对象的方法都是独立的，锁住这个方法只是单纯的锁住了当前方法（java没有这种规则）
     *
     *      synchronized括号中的具体含义是什么？
     *          具体含义就是锁住
     *
     *       在没有synchronized的时候
     *          其实java中自带的有一个对象叫monitor
     *              也就是说通过公这个类创建出的对象都会有一个属于自己的monitor对象
     *
     *              User user1 = new User();
     *              user1--->monitor
     *              User user2 = new User();
     *              user2--->monitor
     *
     *              这个monitor其实就是java对象的锁（是java自动给它分配的，不需要程序员去做它的的工作）
     *              大家又把这个monitor叫作“对象锁”或者“内置锁”
     *              monitor的作用就是：确保了每个对象都是独立的，互相之间不干扰
     *
     *              User user1 = new User();
     *              user1.setName("zhangsan");
     *              User user2 = new User();
     *              user2.setName("lisi");
     *
     *              monitor的作用就是
     *              user1.getName("zhangsan")--->所获取到的必须是zhangsan，绝对不是lisi
     *                  如果获取到的是lisi：这就叫作线程穿线
     *              这个就是为了防止线程的穿线，防止线程不安全
     *
     *             架构师面试题：
     *                  1.什么是对象锁：在生成对象的时候java自动为每一个对象分配一个属于自己monitor
     *                  2.对象锁具体指的是什么： monitor
     *                  3.作用是什么：确保每个对象都是独立的，防止线程穿线
     *
     *                  其实monitor就是一个监听器，来监控对象是否生成，当对象生成之后这个monitor就会一直跟着这个对象
     *
     *                  eg：就想每个明星都有一个follow PD（跟拍）这个follow PD就是monitor
     *
     *               架构师面试题：
     *                  monitor是如何实现给对象加锁的呢
     *                  计数器
     */
    public void accessResource4(){
        synchronized (SynchronizedDemo.class){
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("线程名称"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * @Author Adam
     * @Description
     *      使用synchronized修饰代码块
     *          现在把睡眠时间改为2分钟--->借助jdk自带工具来分析线程堆栈问题
     *          找到jdk安装目录--->bin问价夹--->jconsole.exe
     *
     *          应为java是一个解释性的语言--->java在文件经过编译后成为class文件，这个时候class文件不能执行运行在（windows，Mac，Linux
     *          需要具备一个环境--->JVM虚拟机环境（把这个class文件解析成计算机可以识别的指令）
     *          jstack ---> java stack
     *          必须先知道当前运行的Pid  7128
     *
     *          这个时候我们发现，线程和线程之间是独立的，互不干涉，并且当线程去占有对象的时候（在同一时间一个线程只能占用一个对象）
     *
     *          这个就是synchronized--->排他性（加上Synchronized之后线程会一个一个的执行）
     *
     *          javap反编译指令（就是把class文件反编译成二进制文件--->应为反编译出来的看不懂（只能看懂JVM是如何解释这个文件的））
     *
     *          通过这个命令 javap -v 来反编译
     *
     *
     *
     * @Parme: []
     * @return : void
     * @Date: 2020/3/4 20:19
     */
    public void accessResource5(){
        synchronized (SynchronizedDemo.class){
            try {
                TimeUnit.MINUTES.sleep(2);
                System.out.println("线程名称"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    


    public static void main(String[] args) {
        /**
         * 测试静态方法
         */
        /*for (int i = 0; i < 5; i++) {
            //这种写法就相当于SynchronizedDemo.accessResource1
            new Thread(SynchronizedDemo::accessResource1).start();
        }*/
        /**
         * 测试非静态方法
         */
        /*for (int i = 0; i < 5; i++) {
            //这种写法就相当于SynchronizedDemo.accessResource1
            SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
            new Thread(synchronizedDemo::accessResource4).start();
        }*/
         long youngMemory = Runtime.getRuntime().maxMemory();
        System.out.println(youngMemory/1024+"k");

       /* System.out.println(youngMemory/1024+"k");
        byte[]  bytes = new byte[1024*1024*80];*/
        /**
         * 测试非静态代码块
         */

      //  SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
      /*  for (int i = 0; i < 5; i++) {
            new Thread(synchronizedDemo::accessResource5).start();
        }*/


    }

}
