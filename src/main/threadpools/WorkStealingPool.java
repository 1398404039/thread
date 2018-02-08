package main.threadpools;

import java.util.concurrent.*;

public class WorkStealingPool {
    public static int flag = 0;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int count=10000;
        ExecutorService workStealingPool1= Executors.newWorkStealingPool();
        ExecutorService workStealingPool2= Executors.newWorkStealingPool(2);


        CountDownLatch latch = new CountDownLatch(count);
        Long begin=System.currentTimeMillis();
        for (int i = 0;i < count;i++) {
//            new Thread(new PrintTask(i,latch)).start();
//            workStealingPool2.execute(new PrintTask(i,latch));
            System.out.println(i+"-------------"+workStealingPool2.submit(new DataTask(i,latch)).get());;
        }
        latch.await();
        Long end=System.currentTimeMillis();
        System.out.println("耗时：--------------"+ flag + "--------" + (end - begin));
    }
}

class PrintTask implements Runnable{
    private CountDownLatch latch;
    private int index;

    public PrintTask(int index,CountDownLatch latch){
        this.index = index;
        this.latch = latch;
    }

    @Override
    public void run(){
        synchronized ((Integer)WorkStealingPool.flag){
            WorkStealingPool.flag++;
        }
        System.out.println(Thread.currentThread().getName()+"---------" + index + "-----" + WorkStealingPool.flag) ;
        latch.countDown();
    }
}

class DataTask implements Callable<Integer>{
    private CountDownLatch latch;
    private int index;

    public DataTask(int index,CountDownLatch latch){
        this.index = index;
        this.latch = latch;
    }

    @Override
    public Integer call() throws Exception {
        latch.countDown();
        return index;
    }
}
