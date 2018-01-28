package main.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadTestUtils {

    public static void threadTest(ExecutorService threadPool){
        String beginTime=String.format("beginTime:%s", DateUtils.getTimeNow());
        System.out.println(beginTime);
        for (int i = 0; i < 10; i++) {
        final int index = i;
        try {
            String message="put**************************************************index="+index+"***"+DateUtils.getTimeNow()+"******当前活跃线程数："+((ThreadPoolExecutor)threadPool).getActiveCount();
            System.out.println(message);
//	             Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadPool.execute(new Runnable() {
            public void run() {
                printTime(index,(ThreadPoolExecutor)threadPool);
            }
        });
    }
    String endTime=String.format("endTime:%s",DateUtils.getTimeNow());
        System.out.println(endTime);
}

    public static void printTime(int index,ThreadPoolExecutor threadPool){
        String str=Thread.currentThread().getName()+"***"+DateUtils.getTimeNow()+"========"+index+"******当前活跃线程数："+threadPool.getActiveCount();
        System.out.println(str);
    }
}
