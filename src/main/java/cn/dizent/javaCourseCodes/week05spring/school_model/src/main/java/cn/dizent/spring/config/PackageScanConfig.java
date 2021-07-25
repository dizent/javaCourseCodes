package cn.dizent.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * @author dizent
 * @Auther: 布谷
 * @Date: 2021/7/25 14:00
 * @Description:
 */
@ComponentScans(
        @ComponentScan(value = "cn.dizent.spring.aop")
)
@Configuration
public class PackageScanConfig {
}
