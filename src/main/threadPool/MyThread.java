package main.threadPool;

import main.utils.DateUtils;
import main.utils.ThreadTestUtils1;

public class MyThread {
    public static void main(String[] args){
        long begin=System.currentTimeMillis();
        for(int i=0;i<10;i++){
            String str=String.format("*****index:%s ************** 测试打印数据 *****  当前时间：%s",i, DateUtils.getTimeNow());
            String str1=String.format("*****index:%s ************** 测试打印数据 *****  当前时间：%s",i, DateUtils.getTimeNow());
            System.out.println(str);
            System.out.println(str1);
        }
        long end=System.currentTimeMillis();
        System.out.println(end-begin);
        /*System.out.println(DateUtils.getTimeNow());
        long begin=System.currentTimeMillis();
        for(int i=0;i<10;i++){
            Thread thread2=new TestThread2(i);thread2.start();
            TestThread1 thread1=new TestThread1(i);new Thread(thread1).start();
        }
        long end=System.currentTimeMillis();
        System.out.println(end-begin);*/
    }
}

class TestThread1 implements Runnable{
    private int count;
    public TestThread1(int count){
        this.count=count;
    }
    @Override
    public void run() {
        ThreadTestUtils1.printTest("TestThread1",count);
    }
}

class TestThread2 extends Thread{
    private int count;
    public TestThread2(int count){
        this.count=count;
    }
    public void run(){
        ThreadTestUtils1.printTest("TestThread2",count);
    }
}
