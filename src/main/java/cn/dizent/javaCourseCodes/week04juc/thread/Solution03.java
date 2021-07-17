package cn.dizent.javaCourseCodes.week04juc.thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 布谷
 * @Date: 2021/7/17 10:08
 * @Description:
 */
public class Solution03 {

    class MyThread implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return sum();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        try {

            ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            Future<Integer> future = executorService.submit(() -> sum());
            // 异步执行 下面方法
            int result = future.get(); //这是得到的返回值
            System.out.println("异步计算结果为：" + result);
            // 确保  拿到result 并输出
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
