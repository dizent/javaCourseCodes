package cn.dizent.javaCourseCodes.week04juc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 布谷
 * @Date: 2021/7/18 19:56
 * @Description:
 */
public class Solution06 {


    static Condition condition = new ReentrantLock().newCondition();


    static int sum = 0;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Solution06Thread solution06Thread = new Solution06Thread();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.execute(solution06Thread);
        try {
//            condition.await(3, TimeUnit.SECONDS);
            int result = sum;
            //这是得到的返回值
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        executorService.shutdown();
    }

    static class Solution06Thread implements Runnable {

        @Override
        public void run() {
            sum = sum();
//            condition.signalAll();
        }
    }

    private static int sum() {

        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
}
