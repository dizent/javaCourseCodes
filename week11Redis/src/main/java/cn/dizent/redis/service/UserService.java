package cn.dizent.redis.service;

import cn.dizent.redis.config.RedisLock;
import cn.dizent.redis.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Auther: 布谷
 * @Date: 2021/9/5 21:18
 * @Description:
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "userService")
public class UserService {

    @Autowired
    RedisLock redisLock;

    @Cacheable(key="#userId",value="userCache")
    public UserEntity getById(Integer userId) {
        log.info("this is use method executed");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setUserName("userName" + userId);
        userEntity.setUserInfo("userInfo" + userId);
        return userEntity;
    }

    @Cacheable
    public UserEntity getByUserName(String userName){
        log.info("this is use method executed");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(0);
        userEntity.setUserName("userName:" + userName);
        userEntity.setUserInfo("userInfo:" + userName);
        return userEntity;
    }

    /**
     * 分布式锁的使用
     * @return
     */
    public void updateUserNum() {
        try {
            String lockVal = UUID.randomUUID().toString();
            boolean isOver = true;
            System.out.println(Thread.currentThread().getName() + "prepare to try lock");
            while (isOver) {
                if (redisLock.tryLock(RedisLock.LOCKNAME, lockVal, 20000L)) {
                    System.out.println(Thread.currentThread().getName() + "start");
                    Thread.sleep(10000L);
                    System.out.println(Thread.currentThread().getName() + "end");
                    redisLock.releaseLock(RedisLock.LOCKNAME, lockVal);
                    isOver = false;
                } else {
                    Thread.sleep(10L);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
