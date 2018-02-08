package main.mymultithread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author: ALWZ
 * @create: 2018-01-28 17:21
 * @description: 使用Callable和Future来创建线程
 **/
public class MyThread3 {
    public static void main(String[] args) {
        /*Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int i = 0;
                for (; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "------------" + i);
                }
                return i;
            }
        };*/
        FutureTask<Integer> task = new FutureTask<>(() -> {
            int a = (int) ((Math.random()) * 100);
            int b = (int) ((Math.random()) * 100);
            System.out.println(Thread.currentThread().getName() + "------a:" + a + "===b:" + b);
            return a + b;
        });

        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "------------" + i);
            new Thread(task).start();
        }
        try {
            System.out.println("子线程的返回值：" + task.get());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
