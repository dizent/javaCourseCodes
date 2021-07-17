package cn.dizent.javaCourseCodes.week04juc.thread;

import cn.dizent.javaCourseCodes.week04juc.thread.Solution01.MyThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @Auther: 布谷
 * @Date: 2021/7/17 09:43
 * @Description:
 */
public class Solution02 {

    static int sum = 0;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        try {
            Thread thread = new Thread(()->{
                sum = sum();
                countDownLatch.countDown();
            });
            thread.start();
            countDownLatch.await();
            // 异步执行 下面方法
            int result = sum; //这是得到的返回值
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);

            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        } catch (InterruptedException e) {
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
