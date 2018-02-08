package main.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadScheduledExecutor {
    public static void main(String[] args){
        ExecutorService singleThreadScheduledExecutor1= Executors.newSingleThreadScheduledExecutor();
        ExecutorService singleThreadScheduledExecutor2= Executors.newSingleThreadScheduledExecutor(new MyThreadFactory(""));
    }
}
