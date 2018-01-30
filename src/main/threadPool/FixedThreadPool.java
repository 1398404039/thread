package main.threadpool;

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
//        ExecutorService java.threadpool = Executors.newFixedThreadPool(5);
        MyThreadFactory myThreadFactory=new MyThreadFactory("TestPool");
        ExecutorService threadPool = Executors.newFixedThreadPool(5,myThreadFactory);
        ThreadTestUtils.threadTest(threadPool);
    }

}

class MyThreadFactory implements ThreadFactory{
    private int          counter;
    private String       name;
    private List<String> stats;

    public MyThreadFactory(String name)
    {
        counter = 1;
        this.name = name;
        stats = new ArrayList<String>();
    }

    @Override
    public Thread newThread(Runnable runnable)
    {
        Thread t = new Thread(runnable, name + "-Thread_" + counter);
        counter++;
        stats.add(String.format("Created thread %d with name %s on %s \n", t.getId(), t.getName(), new Date()));
        return t;
    }

    public String getStats()
    {
        StringBuffer buffer = new StringBuffer();
        Iterator<String> it = stats.iterator();
        while (it.hasNext())
        {
            buffer.append(it.next());
        }
        return buffer.toString();
    }
}
