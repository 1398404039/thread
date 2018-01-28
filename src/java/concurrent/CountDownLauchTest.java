package java.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLauchTest {
    public static int num=0;
    public static void main(String[] argw){

        long begin=System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            String str=String.format("*****index:%s ************** 测试打印数据 *****  当前时间：%s",i, System.currentTimeMillis());
            String str1=String.format("*****index:%s ************** 测试打印数据 *****  当前时间：%s",i, System.currentTimeMillis());
            System.out.println(str);
            System.out.println(str1);
        }
        long end=System.currentTimeMillis();
        System.out.println("总时长花了："+(end-begin));
        /*CountDownLatch countDownLatch=new CountDownLatch(20);
        long begin=System.currentTimeMillis();
        for(int i=0;i<10;i++){
            Thread thread2=new TestThread2(i,countDownLatch);thread2.start();
            TestThread1 thread1=new TestThread1(i,countDownLatch);new Thread(thread1).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(num);
        long end=System.currentTimeMillis();
        System.out.println("总时长花了："+(end-begin));*/
    }
}

class TestThread1 implements Runnable{
    private int count;
    private CountDownLatch latch;
    public TestThread1(int count,CountDownLatch latch){
        this.count=count;
        this.latch=latch;
    }
    @Override
    public void run() {
        System.out.println(String.format("className:%s *****index:%s ************** 测试打印数据 *****  当前时间：%s","TestThread1",count,System.currentTimeMillis()));
        CountDownLauchTest.num++;
        latch.countDown();
    }
}

class TestThread2 extends Thread{
    private int count;
    private CountDownLatch latch;
    public TestThread2(int count,CountDownLatch latch){
        this.count=count;
        this.latch=latch;
    }
    public void run(){
        System.out.println(String.format("className:%s *****index:%s ************** 测试打印数据 *****  当前时间：%s","TestThread2",count,System.currentTimeMillis()));
        CountDownLauchTest.num++;
        latch.countDown();
    }
}
