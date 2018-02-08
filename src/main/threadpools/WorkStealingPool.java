package main.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkStealingPool {
    public static void main(String[] args){
        ExecutorService workStealingPool1= Executors.newWorkStealingPool();
        ExecutorService workStealingPool2= Executors.newWorkStealingPool(2);
    }
}
