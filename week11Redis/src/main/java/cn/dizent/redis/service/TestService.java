package cn.dizent.redis.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: 布谷
 * @Date: 2021/9/16 09:19
 * @Description:
 */
@Service
public class TestService {

    @Transactional
    public void methodA(){
        //TODO sql update 1⃣️
        //TODO sql update 2⃣️
    }

    @Transactional
    public void methodB(){
        //TODO sql update 3⃣️
        throw new RuntimeException();
        //TODO sql update 4⃣️
    }

    public void methodC(){
        methodA();
    }

    public void methodD(){
        methodA();
        methodB();
    }
}
