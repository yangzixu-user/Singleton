package com.aaa.Singleton;

/**
 * @ClassName SlackerSingleton
 * @Author Adam
 * @Date Create in 2020/2/26  15:47
 * @Description TODO
 *      懒汉模式
 *          也就是懒加载，什么时候用到什么时候加载，用不到则一直不加载
 */
public class SlackerSingleton {
    /**
     * 1.构造方法私有化
     */
    private SlackerSingleton(){}

    /**
     * 2.创建私有的内部对象（对象不能实例化）
     */
    private static SlackerSingleton instance = null;

    /**
     * 3.给外部提供获取对象的方法
     */
    public static SlackerSingleton getInstance(){
        /**
         * 这里非常有研究
         *  判断--->instance是否为null，如果为null创建对象，反之直接返回对象
         */
        if (null == instance){
            instance = new SlackerSingleton();
        }
            return  instance;
    }
    /**
     * 分析懒汉模式：
     *      1.线程是否安全
     *          线程可能不安全，可能会创建出多个对象
     *             解决办法：同步+懒汉模式（synchronized)
     *      2.懒加载
     *          一定是懒加载，因为对象是在被调用的时候才去创建的
     *      3.性能
     *          性能比较好，效率比较高
     *
     */
}
