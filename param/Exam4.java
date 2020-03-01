package com.aaa.param;

import java.util.Arrays;

/**
 * @ClassName Exam4
 * @Author Adam
 * @Date Create in 2020/2/29  11:03
 * @Description TODO
 *  考点：
 *      1.java中方法的参数机制
 *          a:形参是基本数据类型
 *              传递数值
 *          b:实参是引用数据类型
 *              传递地址
 *              特殊的类型：String、包装类等对象不可变性
 *      2.String 包装类等对象的不可变性
 *
 *      方法内的局部变量存在栈帧中：
 *          栈帧;
 *              本地变量 出参入参 方法方法内引用
 *              栈操作 出栈入栈记录
 *              栈帧数据 类文件  classLoader--->Class(类模板）
 *      对象内的属性(成员变量) 方在堆内存中的Eden区
 */
public class Exam4 {
    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 200;
        int[] arr = {1,2,3,4,5};
        MyData my = new MyData();

        change(i,str,num,arr,my);

        System.out.println("i =  " + i);
        System.out.println("str =  " + str);
        System.out.println("num =  " + num);
        System.out.println("arr =  " + Arrays.toString(arr));
        System.out.println("my.a = " + my.a);
    }
    public static void change(int j , String s , Integer n , int[] a , MyData m){
        j += 1;
        s += "world";
        n += 1;
        a[0] += 1;
        m.a += 1;
    }

}
    class MyData{
     int a = 10;
    }


