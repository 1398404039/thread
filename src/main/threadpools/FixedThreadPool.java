package main.threadpools;

import main.utils.ThreadTestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class FixedThreadPool {

    public static void main(String[] args) {
//        ExecutorService java.threadpools = Executors.newFixedThreadPool(5);
        MyThreadFactory myThreadFactory=new MyThreadFactory("TestPool");

        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        ExecutorService threadPool2 = Executors.newFixedThreadPool(5,myThreadFactory);
    }

}