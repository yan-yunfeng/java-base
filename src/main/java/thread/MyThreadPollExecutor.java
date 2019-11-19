package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池的创建方式，不推荐用Executors创建
 * 建议手动用ThreadPoolExecutor的构造函数创建
 *
 * https://juejin.im/post/5dd2d2205188254a0e15b991
 *
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2019/11/19 10:46
 */
public class MyThreadPollExecutor {

    public static void main(String[] args) {
        //Executors创建的线程池，实际都是调用的ThreadPoolExecutor的构造函数
        //ThreadPoolExecutor的构造函数最终都是调用
        //public ThreadPoolExecutor(int corePoolSize,
        //                          int maximumPoolSize,
        //                          long keepAliveTime,
        //                          TimeUnit unit,
        //                          BlockingQueue<Runnable> workQueue,
        //                          ThreadFactory threadFactory,
        //                          RejectedExecutionHandler handler)
        //构造函数参数说明：
        //
        //corePoolSize => 线程池核心线程数量
        //maximumPoolSize => 线程池最大数量
        //keepAliveTime => 空闲线程存活时间
        //unit => 时间单位
        //workQueue => 线程池所使用的缓冲队列
        //threadFactory => 线程池创建线程使用的工厂
        //handler => 线程池对拒绝任务的处理策略
        //    拒绝任务的处理策略有如下4种
        //    AbortPolicy: 直接抛异常
        //    CallerRunsPolicy: 用调用者的线程来运行任务
        //    DiscardOldestPolicy: 丢弃线程队列里最近的一个任务，执行新提交的任务
        //    DiscardPolicy 直接将新任务丢弃

        //以下线程池允许创建Integer.MAX_VALUE个非核心线程，非常容易造成OOM异常
        //可缓存的线程池，该线程池的核心线程池为0，
        //缓冲队列用的是SynchronousQueue，该队列不会缓存任何一个元素，即可以理解为该队列中永远都是满的
        //所以该线程池永远都是新建非核心线程池来执行任务
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        //以下2个线程池，由于其缓存队列是Integer.MAX_VALUE，因此也容易造成OOM异常
        //单线程池，只有一个核心线程，缓冲队列几乎是无限大的，因此其maximumPoolSize和keepAliveTime两个值无效
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        //指定核心线程池，除了核心线程数自定义，其他和单线程池类似
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });

        cachedThreadPool.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

    }
}
