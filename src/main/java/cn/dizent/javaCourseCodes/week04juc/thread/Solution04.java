package cn.dizent.javaCourseCodes.week04juc.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 布谷
 * @Date: 2021/7/17 10:29
 * @Description: 循环判断实现
 */
public class Solution04 {

    static boolean resultFlag = false;
    static int sum = 0;

    static class Solution04Thread extends Thread {
        @Override
        public void run() {
            sum = sum();
            resultFlag = true;
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        // 异步执行 下面方法
        int result = 0;
        Solution04Thread solution04Thread = new Solution04Thread();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        executorService.execute(solution04Thread);
        while(true) {
            if(resultFlag) {
                result = sum;
                break;
            }
        }
         //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        executorService.shutdown();
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
