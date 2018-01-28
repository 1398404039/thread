package main.mymultithread;

/**
 * @author: ALWZ
 * @create: 2018-01-28 17:16
 * @description: 通过实现Runnable接口创建线程类
 **/
public class MyThread2 implements Runnable{

    private int i;
    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "------------" + i);
        }
    }

    public static void main(String[] args){
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName());
            MyThread2 th = new MyThread2();
            new Thread(th).start();
        }
    }
}
