package cn.dizent.javaCourseCodes.week04juc.thread;

import java.util.concurrent.*;

/**
 * @Auther: 布谷
 * @Date: 2021/7/17 09:37
 * @Description: callable实现
 */
public class Solution01 {

    static class Solution01Thread implements Callable<Integer> {
        @Override
        public Integer call() {
            return sum();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        Solution01Thread solution01Thread = new Solution01Thread();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        Future<Integer> future = executorService.submit(solution01Thread);

        try {
            int result = future.get(); //这是得到的返回值
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
