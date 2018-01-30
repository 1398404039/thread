package main.concurrent;


import sun.java2d.SurfaceDataProxy;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author: 0
 * @create: 2018-01-30 10:34
 * @description: Fork/Join并发框架demo
 **/
public class ForkJoinDemo extends RecursiveTask<Integer> {

    //阈值
    private static final int THRESHOLD = 5;
    /**
     * 起点
     */
    private int start;
    /**
     * 结点
     */
    private int end;

    public ForkJoinDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= THRESHOLD;
        if(canCompute){
            for(int i=start;i<=end;i++){
                sum+=i;
            }
        }else{
            int middle=(start+end)/2;
            ForkJoinDemo leftTask=new ForkJoinDemo(start,middle);
            ForkJoinDemo rightTask=new ForkJoinDemo(middle+1,end);
            leftTask.fork();
            rightTask.fork();
            int leftResult=leftTask.join();
            int rightResult=rightTask.join();
            sum=leftResult+rightResult;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinDemo task=new ForkJoinDemo(1,100);
        Future<Integer> result=forkJoinPool.submit(task);
        System.out.println(result.get());
    }
}
