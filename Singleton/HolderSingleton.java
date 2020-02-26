package com.aaa.Singleton;

/**
 * @ClassName Holder
 * @Author Adam
 * @Date Create in 2020/2/26  22:45
 * @Description TODO
 *      所谓的Holder模式:
 *          使用的内部类的形式，把声明对象这一行方到内部类中来实现，
 *      根据内部类的原则从而避免了加锁机制
 *          内部类原则：所有的内部类都是延时加载的，也就是说会在第一次使用的时候加载，并且只加载一次，
 */
public class HolderSingleton {
    /**
     * 1.构造方法私有化
     */
    private HolderSingleton (){}

    /**
     * 无论是静态内部类还是非静态内部类，好处就是会随着本类加载的时候会被生明
     * HolderSingleton类是在项目运行的时候会被加载--->也就意味着内部类也会在项目运行的时候被声明
     * 当项目运行的时候内部类会被声明，创建了new HolderSingleton对象，而这个内部类是私有的，外部还调不到
     *
     *
     * ！！！刚才我所说的内部部类是在项目运行的时候加载  ---->这句话不严谨
     *  因为内部类是在项目运行的时候被声明，并没有实例化（加载是分为两个阶段的--->1.声明（编译.class)---->2.实例化）
     *
     *  内部类其实是在调用的额时候才进行实例化的，并不是直接就会实例化了
     *   也就是说在执行holder.instance这段代码的时候，内部类才会实例化！！！
     */


    /**
     * 2.声明一个私有的类
     */
    private static class holder{
        //3.把创建对象的过程放到私有的内部类中。
        private static HolderSingleton instance = new HolderSingleton();
    }

    /**
     * 提供一个供外界获取对象的方法
     * @return
     */
    public static HolderSingleton getInstance(){
        return holder.instance;
    }

    /**
     * 分析holder
     *  线程一定是安全的（因为使用的是私有的内部类，外部根本访问不到，所以对象才只被创建一次。
     *  懒加载：因为内部类的原理（内部类只有在调用的时候才会被初始化（实例化）这是个完美的懒加载
     *  效率：肯定会高（因为所有的东西都没有上锁，那么效率会更高（因为没有上锁，不会有串行执行）
     *  init方法（Tomcat源码中）--->使用的就是Holder的这种机制
     */
}
