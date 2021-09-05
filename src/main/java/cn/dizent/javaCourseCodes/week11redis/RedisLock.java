package cn.dizent.javaCourseCodes.week11redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

/**
 * @author dizent
 * @Auther: 布谷
 * @Date: 2021/9/5 10:17
 * @Description:
 */
public class RedisLock {

    static Jedis jedis = new Jedis("localhost",6379);

    public boolean lock(String lock,String lockVal){
        return jedis.set(lock, lockVal, new SetParams().nx().ex(10L)) != null;
    }

    public boolean releaseLock(String lock,String lockVal){
        if(jedis.eval("if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else\n" +
                "return 0 end",2,lock,lockVal) != null){
            return true;
        }
        return false;
    }
}
