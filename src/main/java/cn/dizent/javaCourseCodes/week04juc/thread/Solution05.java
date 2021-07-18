package cn.dizent.javaCourseCodes.week04juc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 布谷
 * @Date: 2021/7/18 19:29
 * @Description:
 */
public class Solution05 {

    static volatile int sum = 0;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Solution05Thread solution05Thread = new Solution05Thread();

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.execute(solution05Thread);
        try {
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

    static class Solution05Thread implements Runnable{
        @Override
        public void run() {
            sum = sum();
        }
    }

    private static int sum() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fibo(36);
    }

    private static int fibo(int a) {
        int[] dp = new int[a + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= a; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[a];
    }
}
