package cn.dizent.springcorestarter.school_starter;

import cn.dizent.springcorestarter.school_starter.props.SpringBootPropertiesConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author dizent
 * @Auther: 布谷
 * @Date: 2021/7/25 14:53
 * @Description:
 */
@EnableAutoConfiguration
@ComponentScan(value = "cn.dizent.springcorestarter.school_starter.props")
@EnableConfigurationProperties(SpringBootPropertiesConfiguration.class)
public class SpringBootConfiguration {

}
