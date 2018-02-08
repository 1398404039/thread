package main.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: lwz
 * @description: countDownLauth 使用demo
 * @date: 11:01 2018/1/30
 */
public class CountDownLauchDemo {
    public static void main(String[] args) throws InterruptedException {
        final int count=100;
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            threadPool.submit(new Thread(new ThreadTest2(latch)));
//            new ThreadTest(latch).start();
        }
        latch.await();
        Thread.sleep(2000);
        System.out.println("结束了---------"+ThreadTest2.index + "--------" + System.currentTimeMillis());
    }
}


class ThreadTest extends Thread {
    private CountDownLatch latch;
    public static int index = 0;

    public ThreadTest(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        index++;
        System.out.println(this.getName() + "----------" + index + "---------" + System.currentTimeMillis());
        latch.countDown();
    }
}

class ThreadTest2 implements Runnable {
    private CountDownLatch latch;
    public static int index = 0;

    public ThreadTest2(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        index++;
        System.out.println(Thread.currentThread().getName() + "----------" + index + "---------" + System.currentTimeMillis());
        latch.countDown();
    }
}
