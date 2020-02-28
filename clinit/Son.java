package com.aaa.clinit;

/**
 * @ClassName Son
 * @Author Adam
 * @Date Create in 2020/2/28  23:00
 * @Description TODO
 *
 *  子类初始化<clinit></clinit>
 *     (1) j = method()静态变量显示赋值的代码
 *     (2) 子类的静态代码块
 *
 *  先初始化父类：(5) (1)
 *  初始化子类 (10) (6)
 *
 *  子类实例化方法：<init></init>
 *    (1）super(最前） (9) (3) (2)
 *    (2) i = test(); 非静态变量显示赋值代码 (9)
 *    (3) 子类非静态代码块 (8)
 *    (4) 子类构造方法（最后） (7)
 *
 *
 *    应为创建了两个Son对象，因此实例化方法<init>执行了两次
 *     (9) (3) (2) (9) (8) (7)
 */
public class Son extends Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.println("(6)");
    }
    Son(){
        //super(); 写或不写都在，在子类的构造器中一定会调用父类的构造器
        System.out.println("(7)");
    }
    {
        System.out.println("(8)");
    }
    public int test() {
        System.out.println("(9)");
        return 1;
    }
    public static int method(){
        System.out.println("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        //5 1 10 6 9 3 2 9 8 7
        System.out.println("******************************");
        Son s2 = new Son();
        //9 3 2 9 8 7
    }
    /**
     * 总结：
     *  类的初始化和实例化需要注意哪些方面
     *   一.类初始化过程
     *     1:一个类要创建实例（new...) 需要先加载并初始化该类
     *      a:main方法所在的类要先加载和初始化
     *      b:子类初始化要先初始化父类
     *      c:一个类初始化执行的就是<clinit>()方法
     *          clinit方法由静态类变量显示赋值的代码和静态代码块组成，
     *          静态类变量显示赋值的代码和静态代码块是由上而下顺序执行
     *          <clinit>()方法只执行一次
     *   二.实例初始化过程
     *      1：实例初始化就是执行《init》方法
     *          a：init方法可能有多个，有几个构造器就有几个init方法
     *          b：init方法由非静态实例变量显示赋值的代码和非静态代码块、和构造器组成
     *          c：非静态变量实例显示赋值代码和非静态代码块按从上到下顺序执行，构造器最后执行（super放在首位执行）
     *          d：每次创建实例对象，调用对应的构造器，执行的就是对应的init方法
     *              init方法执行的首行是super()或super(实参列表) 即对应父类的init方法
     *   三.方法的重写(Override)
     *      1.那些方法不可以被重写
     *          final修饰的方法
     *          static修饰的方法
     *          private等子类找中不可见的方法
     *      2.对象的多态性
     *          如果子类重写了父类的方法，那么通过子类对象调用的一定是子类重写过的代码
     *          非静态方法默认调用的对象是this
     *          this对象在构造器或者说《init》方法中就是正在创建的对象
     *
     */



}
