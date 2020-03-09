package com.aaa.volatileDemo;

/**
 * @ClassName VolatileAtomicTest
 * @Author Adam
 * @Date Create in 2020/3/2  19:08
 * @Description TODO
 *      volatile 保证可见性和有序性  不保证原子性
 *     同时--->  线程A(先拿到lock） num =0
 *              read--> load--> use--> assign--> store(lock)-->write-->unlock-->main memory (num = 1)
 *
 *              线程B num = 0
 *                  因为A线性拿到了lock（保证缓存的一致性--B线程的会监听（cpu的嗅探机制）发现A修改了主内存中的值）
 *                      把本线程工作内存中的值失效 重新-->read -->load -->use
 *              read--> load--> use--> assign-->(工作内存中的值就会失效)
 *          解决办法就是  在方法上添加synchronized
 */
public class VolatileAtomicTest {

    public static volatile int num = 0;

//    public static  void increase(){
//        num ++;
//    }
    public static synchronized void increase(){
        num ++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        increase();
                    }
                }
            });
            threads[i].start();
        }
        for (Thread t :threads ) {
            t.join();
        }
        System.out.println(num);
    }
}
