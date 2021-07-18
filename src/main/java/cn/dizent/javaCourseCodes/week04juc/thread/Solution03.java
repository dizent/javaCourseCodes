package cn.dizent.javaCourseCodes.week04juc.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 布谷
 * @Date: 2021/7/17 10:08
 * @Description: 循环判断实现
 */
public class Solution03 {

    static int sum = Integer.MIN_VALUE;

    static class Solution03Thread implements Runnable {
        @Override
        public void run() {
            sum =  sum();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        Solution03Thread solution03Thread = new Solution03Thread();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        try {

            executorService.execute(solution03Thread);
            // 异步执行 下面方法
            int result = 0;
            while(true) {
                if(sum != Integer.MIN_VALUE) {
                    result = sum; //这是得到的返回值
                    break;
                }
            }
            System.out.println("异步计算结果为：" + result);
            // 确保  拿到result 并输出
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
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
