package com.aaa.Singleton;

import jdk.internal.dynalink.beans.StaticClass;

/**
 * 时刻谨记：
 *      你知道的东西非常少
 *      别开不起任何人
 *      保证自己足够优秀
 *
 */

/**
 *  饿汉模式：
 *      无论外部有没有调用，或者项目中有没有用到，第一时间都会把对象创建出来，等着调用
 *
 */
public class HungerSingleton {
    /**
     * 1.重写这个类的构造方法，并把这个构造方法私有化。
     */
    private HungerSingleton(){

    }

    /**
     * 2.创建对象(初始化自己的对象)
     *      一定要把创建对象这段代码私有化
     */
    private static HungerSingleton instance = new HungerSingleton();

    /**
     * 3.给外界提供一种获取的方式
     *      为什么说一定要给一下方法添加一个static关键字
     *      一旦把方法标识为静态的，就意味着这个方法不在属于对象了，而是直接属于类
     *      换句话说这个方法并不会随着对象的生成的创建，就会随着类在被编译的时候加载，这些使用static所标识的方法和属性
     *      就直接会被加载
     *      类在项目启动的时候就会被编译--->换句话说在项目启动的时候这些方法和属性就会被加载
     *      换句话说使用饿汉模式，在项目启动的时候对象已经创建好了
     *
     *      第二步一定是在第一步之前执行的，因为标识了static，在类加载的时候就会被加载，也就是说在项目启动的时候对象就已经有了
     *      构造方法是在类编译之后才会去加载！！！
     *
     *      不添加static的情况下，是永远也调不到的，因为调用方法之前必须要new对象，但是构造方法私有化了‘
     *      所以就添加了static--->然后把这个方法归属与类
     *
     */
    public  static  HungerSingleton gitInstance(){
        return instance;
    }
    /**
     * 分析饿汉模式(从多线程的角度分析)
     *      1.线程是否安全
     *          所谓的线程安全就是整个数据（整个获取到的对象）从始至终是否一致
     *          饿汉模式一定是线程安全的
     *      2.执行效率是否高
     *          (1) 如果项目中使用的饿汉模式非常多也就意味着，当项目启动时就会创建出非常多的对象、。
     *              这些对象都在内存中，就造成了很多没有用到的对象占用资源，导致项目运行效率变慢
     *              这也就是在做架构的时候，项目中一定不能使用饿汉模式，（虽然饿汉模式简单）
     *          (2) 实际开发中，整个单例中是需要些业务逻辑的，大量的业务逻辑写完后，也会随着类加载到JVM内存中
     *          就会有大量的内存被占用，导致项目运行效率变慢
     *      3.是否懒加载/延迟加载
     *          这个条件直接不符合
     *          所谓的懒加载就是用的时候才会去加载，不用的时候不会去加载
     *          因为饿汉模式在项目运行的时候就把对象创建出来了---->不用懒加载
     */


}
