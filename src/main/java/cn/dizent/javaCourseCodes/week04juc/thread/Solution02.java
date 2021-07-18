package cn.dizent.javaCourseCodes.week04juc.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 布谷
 * @Date: 2021/7/17 09:43
 * @Description: CountDownLatch实现
 */
public class Solution02 {

    static int sum = 0;
    static CountDownLatch countDownLatch = new CountDownLatch(1);


    static class Solution02Thread extends Thread {
        @Override
        public void run() {
            sum = sum();
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        // 在这里创建一个线程或线程池，
        try {
            Solution02Thread solution02Thread = new Solution02Thread();
            executorService.execute(solution02Thread);
            countDownLatch.await();
            // 异步执行 下面方法
            int result = sum; //这是得到的返回值
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);

            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        int[] dp = new int[a + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2;i <= a ;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[a];
    }
}
