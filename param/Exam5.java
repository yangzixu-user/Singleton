package com.aaa.param;

/**
 * @ClassName Exam5
 * @Author Adam
 * @Date Create in 2020/2/29  12:51
 * @Description TODO
 */
public class Exam5 {
    static int s; //成员变量  类变量
    int i; //成员变量 实例变量
    int j; //成员变量 实例变量
    {
        int i = 1; //非静态代码块中的局部变量i
        i++;
        j++;
        s++;
    }
    public void test(int j) { //形参 j 局部变量
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) { //形参 args 局部变量
        Exam5 obj1 = new Exam5(); //obj1 局部变量
        Exam5 obj2 = new Exam5(); //obj2 局部变量
        obj1.test(10);
        obj1.test(20);
        obj2.test(30);
        System.out.println(obj1.i + "," + obj1.j + "," + obj1.s);
        System.out.println(obj2.i + "," + obj2.j + "," + obj2.s);
    }

    /**
     * 总结：
     *  1.就近原则
     *  2.变量的分类
     *      成员变量：类变量 实例变量
     *          类中方法外
     *              带static的叫类变量
     *              不带static的叫实例变量
     *      局部变量
     *          方法体{}中，形参，代码块{}中
     *
     *    局部变量个成员变量的区别？
     *      1.声明的位置不同
     *          局部变量：方法体{}中 形参、代码块
     *          成员变量：类中方法外
     *              类变量：有static修饰
     *              实例变量：没有static修饰
     *      2.修饰符
     *          局部变量：final
     *          成员变量：public protected private final static ovlatile 等
     *      3.储存位置
     *          局部变量  栈
     *          实例对象：堆
     *          类变量：方法区
     *       4.作用域不同：
     *           局部变量：从声明处开始，到所示的}结束
     *           实例变量：在当前类中"this"(有时this. 可以缺省），在其他类中“对象名”访问
     *           类变量：在当前类中“类名”（有时类名.可以省略），在其他类中“类名."或者”对象名."访问
     *      5.生命周期
     *          局部变量：每一个线程，每一个生命周期执行的都是新的生命周期（是随着线程的销毁而销毁）
     *          实例变量：随着对象的创建而初始化，随着对象的收回而消亡，每一个对象的实例都是独立的
     *          类变量：随着类的初始化而初始化，随着类的卸载而消亡，该类的所有对象的类变量是共享的
     *              成员变量是有默认值得  局部变量没有
     *       6.当成员变量与xxx变量重名时，如何区分：
     *              1.局部变量与实例对象重名时
     *                  在实例变量前面加“this”
     *              2.当局部变量与类变量重名时
     *                  在类变量前面添加“类名”
     *  3.非静态代码块的执行：每次创建实例对象都会执行
     *
     *  4.方法调用规则：调用一次执行一次
     */
}
