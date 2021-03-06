package cn.dizent.spring.config;

import cn.dizent.spring.aop.IStudent;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Map;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 14:19
 * @Description:
 */
public class MyImportBeanDefinitionRegiestrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //扫描注解
        Map<String, Object> annotationAttributes = importingClassMetadata
                .getAnnotationAttributes(ComponentScan.class.getName());
        String[] basePackages = (String[]) annotationAttributes.get("basePackages");

        //扫描类
        ClassPathBeanDefinitionScanner scanner =
                new ClassPathBeanDefinitionScanner(registry, false);
        TypeFilter helloServiceFilter = new AssignableTypeFilter(IStudent.class);

        scanner.addIncludeFilter(helloServiceFilter);
        scanner.scan(basePackages);
    }
}
