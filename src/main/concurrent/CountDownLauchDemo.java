package main.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: lwz
 * @description: countDownLauth 使用demo
 * @date: 11:01 2018/1/30
 */
public class CountDownLauchDemo {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        CountDownLatch latch = new CountDownLatch(list.size());
        for (int i = 0; i < list.size(); i++) {
            new ThreadTest(latch, list.get(i)).start();
        }
        latch.await();
        System.out.println("结束了---------" + System.currentTimeMillis());
    }
}


class ThreadTest extends Thread {
    private CountDownLatch latch;
    private int index;

    public ThreadTest(CountDownLatch latch, int index) {
        this.latch = latch;
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + "----------" + index + "---------" + System.currentTimeMillis());
        latch.countDown();
    }
}
