package cn.dizent.redis;

import cn.dizent.redis.config.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @Auther: 布谷
 * @Date: 2021/9/5 19:45
 * @Description:
 */
public class TenSecondTask implements Runnable{

    @Autowired
    RedisLock redisLock;

    @Override
    public void run() {
        try {
            String lockVal = UUID.randomUUID().toString();
            boolean isOver = true;
            while(isOver) {
                if (redisLock.tryLock(RedisLock.LOCKNAME, lockVal, 20000L)) {
                    System.out.println(Thread.currentThread().getName() + "start");
                    Thread.sleep(10000L);
                    System.out.println(Thread.currentThread().getName() + "end");
                    redisLock.releaseLock(RedisLock.LOCKNAME,lockVal);
                    isOver = false;
                }else{
                    Thread.sleep(10L);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
