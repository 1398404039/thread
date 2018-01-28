package java.utils;

public class ThreadTestUtils1 {
    public static void printTest(String className,int index){
        System.out.println(String.format("className:%s *****index:%s ************** 测试打印数据 *****  当前时间：%s",className,index,DateUtils.getTimeNow()));
    }
}
