package main.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ScheduledThreadPool {

    public static void main(String[] args){
        ExecutorService scheduledThreadPool1= Executors.newScheduledThreadPool(5);
        ExecutorService scheduledThreadPool2= Executors.newScheduledThreadPool(5,new MyThreadFactory(""));
    }
}
