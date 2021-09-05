package cn.dizent.javaCourseCodes.week11redis;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 布谷
 * @Date: 2021/9/5 10:22
 * @Description:
 */
public class Solution {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(()->{
            String lockVal = UUID.randomUUID().toString();
            RedisLock lock = new RedisLock();
            if(lock.lock("dizentLock",lockVal)) {
                System.out.println(Thread.currentThread().getName() + lockVal);
                lock.releaseLock("dizentLock", lockVal);
            }
        });
        executorService.submit(()->{
            String lockVal = UUID.randomUUID().toString();
            RedisLock lock = new RedisLock();
            if(lock.lock("dizentLock",lockVal)) {
                System.out.println(Thread.currentThread().getName() + lockVal);
                lock.releaseLock("dizentLock", lockVal);
            }
        });
    }


}
