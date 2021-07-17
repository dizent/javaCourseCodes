package cn.dizent.javaCourseCodes.week04juc.thread;

import java.util.concurrent.Callable;

/**
 * @Auther: 布谷
 * @Date: 2021/7/17 09:37
 * @Description:
 */
public class Solution01 {

    static class MyThread implements Callable<Integer> {
        @Override
        public Integer call() {
            return sum();
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        MyThread myThread = new MyThread();
        try {
            int result = myThread.call(); //这是得到的返回值
            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
