package main.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutor {
    public static void main(String[] args){
        ExecutorService singleThreadExecutor1= Executors.newSingleThreadExecutor();
        ExecutorService singleThreadExecutor2= Executors.newSingleThreadExecutor(new MyThreadFactory(""));
    }
}
