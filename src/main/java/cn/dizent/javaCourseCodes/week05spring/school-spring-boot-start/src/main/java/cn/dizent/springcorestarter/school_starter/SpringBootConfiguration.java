package cn.dizent.springcorestarter.school_starter;

import cn.dizent.springcorestarter.school_starter.props.SpringBootPropertiesConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.Properties;

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
