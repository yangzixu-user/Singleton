package com.aaa.test;

/**
 * @ClassName TestStep
 * @Author Adam
 * @Date Create in 2020/2/29  11:59
 * @Description TODO
 */
public class TestStep {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int a = f(100);
        long end = System.currentTimeMillis();
        System.out.println(a);
        System.out.println(end - start);
    }
    //实现f(n):求n步台阶，一共有多少中走法
    public static int f(int n){

        if (n<1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 ||  n ==2) {
                return n;
        }
        return f(n-2) + f(n-1);
    }

}
