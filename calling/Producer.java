package com.aaa.calling;

/**
 * @ClassName Producer
 * @Author Adam
 * @Date Create in 2020/2/26  21:52
 * @Description TODO
 */
public class Producer implements Runnable {
    private Center center;

    public Producer(Center center) {
        this.center = center;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            //产生客户
            center.produce();
        }
    }
}
