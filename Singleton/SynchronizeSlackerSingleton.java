package com.aaa.Singleton;

/**
 * @ClassName SynchronizeSingleton
 * @Author Adam
 * @Date Create in 2020/2/26  17:44
 * @Description TODO
 *      同步+懒汉模式
 */
public class SynchronizeSlackerSingleton {
    /**
     * 1.构造方法私有化
     */
    private SynchronizeSlackerSingleton(){}

    /**
     * 2.创建内部私有对象（这个对象是不能实例化的）
     */
    private static SynchronizeSlackerSingleton instance = null;

    /**
     *
     * 3.给外界提供一个获取对象的方法
     */
    public synchronized static SynchronizeSlackerSingleton getInstance(){
        /**
         * 这里非常有研究
         *      判断--->判断instance是否为null，如果为null，就创建对象，如果不为null直接返回对象
         */
        if (null == instance){
            instance = new SynchronizeSlackerSingleton();
        }
        return instance;
    }

    /**
     * 分析同步+懒汉模式
     *      1.线程安全：肯定安全，使用了synchronized 同步机制
     *      2.懒加载：懒加载  使用的是懒汉模式
     *      3.效率：效率肯定不高，非常低
     *          因为使用的同步锁机制，只要有一个线程进入方法，其他线程 就需要在方法外排队等待。
     *          这样非常影响效率
     *          当使用synchronized的时候，使用的是串行执行
     *              串行执行：
     *                  多个程序相互之间都是独立的
     *                  qq 微信 IDEA
     *                  当打开qq聊天的时候，必须关闭qq才能打开IDEA---->关闭IDEA---->才能打开微信
     *                  也就是说一次只能打开一个程序
     *                  一个程序没有结束前，另一个程序不能开始，要一个一个的
     *               并行执行：
     *                  所有的程序都可以一起执行，不用等上一个程序执行完毕，另一个程序才开始执行
     *                 并行的效率一定是高于串行的（并行的时间可以叠加，效率高，串行时间不能叠加，效率低）
     *           改进措施：在这里是锁方法，改进：只锁可能啊存在线程不安全的哪一行，
     *              双重检测+锁机制（DCL） Double Check Locking
     *
     *
     */

}
