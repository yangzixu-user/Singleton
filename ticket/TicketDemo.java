package com.aaa.ticket;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName TicketDemo
 * @Author Adam
 * @Date Create in 2020/2/27  16:54
 * @Description TODO
 *      模拟医院排队窗口程序
 *      会涉及到并发，涉及到并发就意味着涉及到线程
 *      要保证线程的绝对安全（所谓的线程安全，就是要保证所有服务器的上的数据一致性）
 *
 *
 */
public class TicketDemo extends Thread {

    //1.首先先创建排号的索引---->被喊道的号
    private static int index;

    public static Object object = new Object();
    /**
     * 根据代码的严谨性，在医院挂号的时候，还有可能会挂专家号（还的有最大访问值）
     * 这个值通常来说不允许改变
     * 2.定义一个最大的访问值（通常在这使用静态常亮）
     */
    private static final Integer MAX=50;

    @Override
    public void run() {
        //.业务逻辑
        /**
         *3.实现简单的业务逻辑
         *  循环条件是index小于等于最大值时会自动进入循环，否者跳出循环
         *  这个index就比较有意识  index++ 和 ++index的区别
         *
         *  ！！控制台输出的规则--->首先先对基本类型进行计算--->然后在对对象类型/包装类型进行计算--->输出控制台
         *      java的机制规则
         *          先获取到index(null)--->压缩到内存中--->计算后面的运算符++(先乘除后加减）index(0) --->输出
         *
         *
         */
         /*while (index <= MAX) {
            System.out.println("预约到的号码是:" + (index++));
            *//*try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }*//*
        }*/
        /**
         * 使用上锁的方法来解决问题
         *     1. 锁住当前对象this
         *          发现使用this不行，但是使用本类和静态的Object都可以
         *          this--->代表的是本身对象（即：当前执行的线程）--->有可能资源被抢夺走--->锁就换人了--->上一个线程会记录位置--->再次输出的时候就可能会出现重号
         *     2.锁TicketDemo对象
         *          、TicketDemo--->直接把整个线程锁住了--->只要通过这个类创建出来的线程都锁住--->所有的线程都会进入同步模式（串行模式）
         *              只要和有钱有关的，以严谨为主不考虑效率
         *      3.Object--->所有类的父类都是Object--->锁这个Object就相当于锁住了所有子类包含当前本身类--->相当于锁的也是TicketDome类
         *         静态是在项目启动的时候就会被加载，这个时候Object对象就已经有了
         *         非静态的是在被调用的时候才会被加载--->换句话说如果使用的非静态的--->当前线程已经创建出来了，执行到这一步才有了Object对象
         *         这个时候再去锁Object就无效了，因为TicketDome对象已经有了，所以不会起作用了
         */

        synchronized (object){
                while (index<= MAX){
                    System.out.println(Thread.currentThread().getName()+"********* 预约到的号码是："+index++);
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        /**
         * 经过测试，发现很严重的问题，这种现象称之为重号
         *      第一个问题：
         *          只是变量的原值性问题
         *          这个问题就处在index上，因为index是私有变量，是不共享的，当你去new对象的时候，一下子会new出来四个，也就是说index属于对象不属于类
         *          不属于类就不会跟着类在加载类的时候计入内存，所以不共享，只有添加了static把这个对象归属于类，也会跟着类加载进JVM内存中（共享缓存，二级缓存）
         *          这个时候视乎看似没有什么问题
         *           假设把MAX的最大值由10增加到500--->当MAX增加，导致线程相互争夺资源比较严重。cpu性能就会下降，也就意味着当高并发来临时会占用系统的
         *           大量资源，就会导致整个服务器的性能下降，在这些线程争夺资源的途中导致重复争夺的情况--->所以会发生重号
         *       第二个问题：
         *          重号现象
         *       第三个问题：
         *          在请求来临时，线程会非常积极（区抢夺资源）就会导致一个现象叫作挑号
         *          比如现在已经预约到11号了--->直接跳到13号  12号没有喊到
         *       第四个问题：
         *          当请求越来越多，服务器性能下降，会直接导致线程的执行效率，有可能会超过最大值（MAX）的值
         *          可能就会出行现在喊得号是501--->我们开发人员很难测到，一般情况下通过测试工具进行统计
         *       第五个问题：
         *          出现乱号的情况，比如现在是号码是51 下一个号码直接跳到29
         *          这个用户就有非常不好的体验
         *     这就是目咱们面临的几大问题
         *          跳号、重号、乱号、超过最大号
         *
         *        解决办法：
         *          非常简答--->加锁(synchronized)
         *
         *
         *
         */

    }


    public static void main(String[] args) {
        TicketDemo t1 = new TicketDemo();
        TicketDemo t2 = new TicketDemo();
        TicketDemo t3 = new TicketDemo();
        TicketDemo t4 = new TicketDemo();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
