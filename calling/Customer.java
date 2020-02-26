package com.aaa.calling;

/**
 * @ClassName Consumer
 * @Author Adam
 * @Date Create in 2020/2/26  21:50
 * @Description TODO
 */
public class Customer implements Runnable {
    private Center center;

    public Customer(Center center) {
        this.center = center;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            center.consume();
        }
    }

}

