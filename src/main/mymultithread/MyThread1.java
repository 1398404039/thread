package main.mymultithread;

/**
 * @author: ALWZ
 * @create: 2018-01-28 17:00
 * @description: 通过继承Thread来创建线程
 **/
public class MyThread1 extends Thread {
    private int i;

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(this.getName() + "------------" + i);
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName());
            new MyThread1().start();
        }
    }
}
