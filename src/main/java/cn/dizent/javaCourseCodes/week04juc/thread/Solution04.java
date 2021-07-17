package cn.dizent.javaCourseCodes.week04juc.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: 布谷
 * @Date: 2021/7/17 10:29
 * @Description:
 */
public class Solution04 {


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        // 异步执行 下面方法
        int result = 0;
        try {
            result = CompletableFuture.supplyAsync(Solution04::sum).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        ; //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");


        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
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
