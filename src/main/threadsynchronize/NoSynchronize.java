package main.threadsynchronize;

import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @author: 0
 * @create: 2018-01-29 9:10
 * @description: 演示没有线程同步会造成的线程安全问题之一
 **/
public class NoSynchronize {
    public static int num = 0;
    public static Set<Integer> set=new HashSet<>(10000);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(10000);
        for (int i=0;i<10000;i++){
            new Add(latch).start();
        }
        latch.await();
        System.out.println(set.size());
    }
}
class Add extends Thread{
    private CountDownLatch latch;

    public Add( CountDownLatch latch){
        this.latch=latch;
    }
    @Override
    public void run(){
        NoSynchronize.num+=1;
        NoSynchronize.set.add(NoSynchronize.num);
        latch.countDown();
    }
}


