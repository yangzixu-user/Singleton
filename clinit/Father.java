package com.aaa.clinit;

/**
 * @ClassName Father
 * @Author Adam
 * @Date Create in 2020/2/28  23:00
 * @Description TODO
 *
 *  父类的初始化<clinit></clinit>
 *    (1) j = method()静态变量显示赋值的代码
 *    (2) 父类的静态代码块
 *
 *  父类实例化方法：<init></init>
 *    (1）super(最前）
 *    (2) i = test(); 非静态变量显示赋值代码
 *    (3) 父类非静态代码块
 *    (4) 父类构造方法（最后）
 *
 *    非静态方法前面有一个默认的对象（this）
 *    this在构造器（或<init>)它表示的是正在创建的对象
 *    因为这里正在创建Son对象 所以test()执行的是子类重写的代码( 面向对象的多态)
 *
 *    这里的i= test() 执行的子类重写test()方法
 *
 *
 */
public class Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.println("(1)");
    }
    Father(){
        System.out.println("(2)");
    }
    {
        System.out.println("(3)");
    }
    public int test() {
        System.out.println("(4)");
        return 1;
    }
    public static int method(){
        System.out.println("(5)");
        return 1;
    }
}
