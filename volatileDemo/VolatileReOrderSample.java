package com.aaa.volatileDemo;

/**
 * @ClassName VolatileReOrderSample
 * @Author Adam
 * @Date Create in 2020/3/2  22:38
 * @Description TODO
 *     分析：cpu 指令重排演示
 */
public class VolatileReOrderSample {
    private static int x = 0 ,y = 0 ,a = 0 , b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (;;){
            i++;
            a = 0; b = 0;
            x = 0; y = 0;
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    shortWait(100000);
                    a = 1;
                    x = b;
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            t1.start();
            t1.join();
            t2.start();
            t2.join();

            String  str = "第" + i + "次（"+ x +","+ y +")";
            if (x==0 && y==0){
                System.err.println(str);
                break;
            }else {
                System.out.println(str);
            }
        }
    }
    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        }while (start + interval >=end);
    }
}
