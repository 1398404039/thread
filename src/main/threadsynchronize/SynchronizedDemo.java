package main.threadsynchronize;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;

/**
 * @author: 0
 * @create: 2018-01-29 10:17
 * @description: synchronized同步机制
 **/
public class SynchronizedDemo {
    public static int num = 0;
    public static Set<Integer> set = new HashSet<>(10000);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            new AddDemo1(latch).start();
        }
        latch.await();
        System.out.println(set.size());

        for (int i : set) {
            System.out.println(i);
        }
    }
}

//class AddDemo extends Thread{
//    private CountDownLatch latch;
//    private int index;
//
//    public AddDemo( CountDownLatch latch,int index){
//        this.latch=latch;
//        this.index=index;
//    }
//    @Override
//    public void run(){
//        int result=SynchronizedDemo.num.incrementAndGet();
//        SynchronizedDemo.array.lazySet(index,result);
//        latch.countDown();
//    }
//}

class AddDemo1 extends Thread {
    private CountDownLatch latch;

    public AddDemo1(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        synchronized (this) {
            SynchronizedDemo.num++;
            SynchronizedDemo.set.add(SynchronizedDemo.num);
            latch.countDown();
        }
    }
}
