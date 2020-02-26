package com.aaa.calling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CallNum
 * @Author Adam
 * @Date Create in 2020/2/26  21:53
 * @Description TODO
 */
public class CallNum {
    public static void main(String[] args) throws InterruptedException {
        //创建服务中心,如一个银行的营业厅
        Center center = new Center();
        ExecutorService exec = Executors.newCachedThreadPool();
        //模拟产生服务人员
        Producer producer = new Producer(center);
        //模拟产生N多客户
        Customer consumer = new Customer(center);
        exec.execute(producer);
        //模拟10名客户
        for (int i = 0; i < 10; i++) {
            exec.execute(consumer);
        }
        TimeUnit.SECONDS.sleep(10);
        exec.shutdown();
        //如有疑问,请加QQ群：135430763共同学习！
    }
}
