package main.threadpools;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * @author: 0
 * @create: 2018-01-29 14:42
 * @description: 通过ThreadPoolExecutor来创建线程池
 **/
public class ThreadPoolExecutorDemo {
    private static LocalDate date ;
    private static LocalTime time ;


    public static void main(String[] args){
       /* TimeUnit */
//        TimeUnit.NANOSECONDS;   纳秒
//        TimeUnit.MICROSECONDS;  微秒
//        TimeUnit.MILLISECONDS;  毫秒
//        TimeUnit.SECONDS;       秒
//        TimeUnit.MINUTES;       分钟
//        TimeUnit.HOURS;         小时
//        TimeUnit.DAYS;          天

       /* RejectedExecutionHandler  拒绝策略*/
//        ThreadPoolExecutor.AbortPolicy;          如果线程池队列满了，会丢掉这个任务并且抛出RejectedExecutionException异常
//        ThreadPoolExecutor.CallerRunsPolicy;     添加到线程池失败，那么主线程会自己去执行该任务，不会等待线程池中的线程去执行。就像是个急脾气的人，我等不到别人来做这件事就干脆自己干
//        ThreadPoolExecutor.DiscardOldestPolicy;  如果线程池队列满了，会将最早进入队列的任务删掉腾出空间，再尝试加入队列；因为队列是队尾进，队头出，所以队头元素是最老的，因此每次都是移除对头元素后再尝试入队。
//        ThreadPoolExecutor.DiscardPolicy;        如果线程池队列满了，会直接丢掉这个任务并且不会有任何异常。


       /* BlockingQueue */
//        ArrayBlockingQueue  基于数组的阻塞队列实现，在ArrayBlockingQueue内部，维护了一个定长数组，以便缓存队列中的数据对象，这是一个常用的阻塞队列，除了一个定长数组外，ArrayBlockingQueue内部还保存着两个整形变量，
//                            分别标识着队列的头部和尾部在数组中的位置。ArrayBlockingQueue在生产者放入数据和消费者获取数据，都是共用同一个锁对象，由此也意味着两者无法真正并行运行，这点尤其不同于LinkedBlockingQueue；
//                            按照实现原理来分析，ArrayBlockingQueue完全可以采用分离锁，从而实现生产者和消费者操作的完全并行运行。Doug Lea之所以没这样去做，也许是因为ArrayBlockingQueue的数据写入和获取操作已经足够轻巧，
//                            以至于引入独立的锁机制，除了给代码带来额外的复杂性外，其在性能上完全占不到任何便宜。 ArrayBlockingQueue和LinkedBlockingQueue间还有一个明显的不同之处在于，前者在插入或删除元素时不会产生或销
//                            毁任何额外的对象实例，而后者则会生成一个额外的Node对象。这在长时间内需要高效并发地处理大批量数据的系统中，其对于GC的影响还是存在一定的区别。而在创建ArrayBlockingQueue时，我们还可以控制对象
//                            的内部锁是否采用公平锁，默认采用非公平锁。


//        LinkedBlockingQueue 基于链表的阻塞队列，同ArrayListBlockingQueue类似，其内部也维持着一个数据缓冲队列（该队列由一个链表构成），当生产者往队列中放入一个数据时，队列会从生产者手中获取数据，并缓存在队列内部，
//                            而生产者立即返回；只有当队列缓冲区达到最大值缓存容量时（LinkedBlockingQueue可以通过构造函数指定该值），才会阻塞生产者队列，直到消费者从队列中消费掉一份数据，生产者线程会被唤醒，反之对
//                            于消费者这端的处理也基于同样的原理。而LinkedBlockingQueue之所以能够高效的处理并发数据，还因为其对于生产者端和消费者端分别采用了独立的锁来控制数据同步，这也意味着在高并发的情况下生产者
//                            和消费者可以并行地操作队列中的数据，以此来提高整个队列的并发性能。作为开发者，我们需要注意的是，如果构造一个LinkedBlockingQueue对象，而没有指定其容量大小，LinkedBlockingQueue会默认一个
//                            类似无限大小的容量（Integer.MAX_VALUE），这样的话，如果生产者的速度一旦大于消费者的速度，也许还没有等到队列满阻塞产生，系统内存就有可能已被消耗殆尽了。ArrayBlockingQueue和LinkedBlockingQueue
//                            是两个最普通也是最常用的阻塞队列，一般情况下，在处理多线程间的生产者消费者问题，使用这两个类足以。


//        DelayQueue          DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。DelayQueue是一个没有大小限制的队列，因此往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）
//                            才会被阻塞。 使用场景：DelayQueue使用场景较少，但都相当巧妙，常见的例子比如使用一个DelayQueue来管理一个超时未响应的连接队列。


//        PriorityQueue       基于优先级的阻塞队列（优先级的判断通过构造函数传入的Compator对象来决定），但需要注意的是PriorityBlockingQueue并不会阻塞数据生产者，而只会在没有可消费的数据时，阻塞数据的消费者。因此使用的时
//                            候要特别注意，生产者生产数据的速度绝对不能快于消费者消费数据的速度，否则时间一长，会最终耗尽所有的可用堆内存空间。在实现PriorityBlockingQueue时，内部控制线程同步的锁采用的是公平锁。


//        SynchronousQueue    一种无缓冲的等待队列，类似于无中介的直接交易，有点像原始社会中的生产者和消费者，生产者拿着产品去集市销售给产品的最终消费者，而消费者必须亲自去集市找到所要商品的直接生产者，如果一方没有找到
//                            合适的目标，那么对不起，大家都在集市等待。相对于有缓冲的BlockingQueue来说，少了一个中间经销商的环节（缓冲区），如果有经销商，生产者直接把产品批发给经销商，而无需在意经销商最终会将这些产品卖
//                            给那些消费者，由于经销商可以库存一部分商品，因此相对于直接交易模式，总体来说采用中间经销商的模式会吞吐量高一些（可以批量买卖）；但另一方面，又因为经销商的引入，使得产品从生产者到消费者中间增
//                            加了额外的交易环节，单个产品的及时响应性能可能会降低。声明一个SynchronousQueue有两种不同的方式，它们之间有着不太一样的行为。公平模式和非公平模式的区别:如果采用公平模式：SynchronousQueue会采
//                            用公平锁，并配合一个FIFO队列来阻塞多余的生产者和消费者，从而体系整体的公平策略；但如果是非公平模式（SynchronousQueue默认）：SynchronousQueue采用非公平锁，同时配合一个LIFO队列来管理多余的生产
//                            者和消费者，而后一种模式，如果生产者和消费者的处理速度有差距，则很容易出现饥渴的情况，即可能有某些生产者或者是消费者的数据永远都得不到处理。
//        小结
//　　      BlockingQueue不光实现了一个完整队列所具有的基本功能，同时在多线程环境下，他还自动管理了多线间的自动等待于唤醒功能，从而使得程序员可以忽略这些细节，关注更高级的功能。

        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(21,21,2000L,
                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(3),new ThreadPoolExecutor.AbortPolicy());

        //SynchronousQueue
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        //LinkedBlockingQueue
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        //LinkedBlockingQueue
        ExecutorService executorService5 = Executors.newSingleThreadExecutor();
        //DelayedWorkQueue
        ExecutorService executorService4 = Executors.newScheduledThreadPool(1);
        //DelayedWorkQueue
        ExecutorService executorService6 = Executors.newSingleThreadScheduledExecutor();
        
        ExecutorService executorService3 = Executors.newWorkStealingPool();

    }
}
