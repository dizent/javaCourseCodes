package cn.dizent.redis;

import cn.dizent.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@RestController
@EnableCaching
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }


    @Bean
    ExecutorService threadPoolExecutor(){
        return Executors.newFixedThreadPool(3);
    }

    @Autowired
    ExecutorService threadPoolExecutor;

    @Autowired
    UserService userService;

    @GetMapping("/task")
    public String startTask(){
        TenSecondTask t1 = new TenSecondTask();
        TenSecondTask t2 = new TenSecondTask();
        threadPoolExecutor.submit(()->{
            userService.updateUserNum();
        });
        threadPoolExecutor.submit(()->{
            userService.updateUserNum();
        });
        return "end";
    }
}
