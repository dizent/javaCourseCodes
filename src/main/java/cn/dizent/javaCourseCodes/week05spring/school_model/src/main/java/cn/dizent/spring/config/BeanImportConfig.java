package cn.dizent.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 14:17
 * @Description:
 */
@Configuration
@ComponentScan("cn.dizent.spring.aop")
@Import(MyImportBeanDefinitionRegiestrar.class)
public class BeanImportConfig {
}
