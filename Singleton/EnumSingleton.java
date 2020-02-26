package com.aaa.Singleton;

/**
 * @ClassName EnumSingleton
 * @Author Adam
 * @Date Create in 2020/2/26  23:20
 * @Description TODO
 */
public enum EnumSingleton {
    /**
     * 1.定义一个枚举类--->这个INSTANCE只会在EnumSingleton被加载的时候实例化一次
     * 也就是说INSTANCE就代表了EnumSingleton这个对象
     */
    INSTANCE,
    INIT;

    /**
     * 2.给外部提供获取对象的方法
     *  当单例需要些业务逻辑的时候---去加载connection的时候，才会需要用到下面的方法
     *  如果没有业务逻辑的时候，根本不需要向外部提供方法，因为enume本来就是public的
     * @return
     */
   /* public static EnumSingleton getInstance(){
        return INSTANCE;
    }*/

    /**
     * 分析枚举
     *      这是最优秀的，这种方式是最聪明的方式，而且mybatis也是使用的这种模式
     *      这种模式的性能是最高的
     *      可以把枚举理解成一个常量
     *      《Effective java》--->枚举的单例模式讲解的非常透彻
     *
     *      线程安全：因为枚举的内部机制，线程一定是安全的，因为枚举只会被实例化一次
     *      效率：因为是java自带的，效率坑定高，也不需要写任何代码进行一步一步的执行，只是一个变量，效率是最高的
     *      懒加载;肯定不是懒加载，因为枚举就和静态常量一样，在类被加载的时候自动跟着类加载进内存了
     *
     */
}
