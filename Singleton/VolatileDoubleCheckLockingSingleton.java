package com.aaa.Singleton;

import java.sql.Connection;

/**
 * @ClassName VolatileDoubleCheckLockingSingleton
 * @Author Adam
 * @Date Create in 2020/2/26  18:23
 * @Description TODO
 *      指令重排 双重检测+锁+懒汉
 */
public class VolatileDoubleCheckLockingSingleton {

    int a = 20;
    int b = 30;
    Connection conn;
    /**
     * 1.构造方法私有化
     */
    private VolatileDoubleCheckLockingSingleton(){
        a = 200;//1.第一步被加载
        b = 300;//2.第二步被加载
        //为了实现懒加载，不浪费服务器资源，要在被创建的时候加载，那也就是说我需要在我的构造方法中加载
       // conn = new Connection();调用知己写的工具
    }
    //new VolatileDoubleCheckLockingSingleton();//第三步创建对象--->也有可能这一步被先执行了。
    /**
     *  根据指令重排规则（happends-before）会打乱执行顺序（指令重排）
     *  执行顺序变成了312
     *   在我去实现ORM框架的时候，我需要做数据库连接（这一定是个单例0
     *   假设根据指令重排规则--->创建对象先先被加载了--->在加载初始化数据库连接--->在VolatileDoubleCheckLockingSingleton
     *   中并没有初始化连接，也就是说我的connection依然是null；
     *   这时候去连接数据库的---->直接报空指针（指令重拍原因）
     *
     *
     *   因为在java机制中有可能会打乱执行顺序--->有可能创建对象先执行，再执行给connection赋值的操作
     *   这个时候一旦使用到对象中的connection属性了，则会发现这个属性依然是null，所以报空指针；
     */
    /**
     * 2.创建内部对象（对象不能实例化）
     *      把volatile这个关键字添加到这一行，发生返回值得连锁效应，导致
     *      new VolatileDoubleCheckLockingSingleton()的位置也不参与指令重排，所以就可以随意的执行
     */
    private volatile  static VolatileDoubleCheckLockingSingleton instance = null;

    /**
     * 3.给外部提供获取对象的方法
     */
    public static VolatileDoubleCheckLockingSingleton getInstance(){
        /**
         * 这里非常有研究
         *      判断---->判断对象是否为null，如果为null，则创建对象，如果不为空则直接返回对象。
         */
        if (null == instance){
            synchronized (VolatileDoubleCheckLockingSingleton.class){
                /**
                 * 再一次判断对象是否为null
                 */
                if (null == instance){
                    instance = new VolatileDoubleCheckLockingSingleton();
                }
            }
        }
        return instance;
    }

    /**
     * f分析：双重检测+锁
     *      线程一定是安全的
     *      效率很高(因为锁的是代码块，哪里创建对象就锁哪里）
     *      一定是懒加载
     *
     *
     *   分析volatile+double-check-locking模式
     *
     *      volatile:的作用就是让被volatile标识的代码不参与指令重排，让代码的位置永远不允许发生改变
     *      貌似这种办法已经很完美了，
     *
     *      线程一定是安全的（懒汉＋双重检测--->一定是线程安全的）
     *      懒加载也一定是
     *      效率；那这种模式和mybatis对比，发现mybatis比自己写的ORM快300倍以上
     *          经过拿mybatis源码发现--->使用的不是懒汉，市面上所有的主流框架都不再用懒汉和饿汉了
     *          是一种holder，一种是枚举 这是目前最流行的两种
     */

}
