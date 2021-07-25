package cn.dizent.springcorestarter.school_starter.props;

import cn.dizent.spring.model.Klass;
import cn.dizent.spring.model.School;
import cn.dizent.spring.model.Student;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Properties;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 14:55
 * @Description:
 */
@Configuration
@ConfigurationProperties(prefix = "spring.school")
@Getter
@Setter
public class SpringBootPropertiesConfiguration {

    private Properties properties = new Properties();

    @Bean
    @ConditionalOnMissingBean(Student.class)
    public Student student(){
        Student student = new Student();
        student.setId(0);
        student.setName("starter-student");
        System.out.println("student starter inited");
        return student;
    }

    @Bean
    @ConditionalOnMissingBean(Klass.class)
    public Klass klass(Student student){
        Klass klass = new Klass();
        klass.setClassNo("starter-klass");
        klass.setStudentList(Collections.singletonList(student));
        return klass;
    }


    @Bean
    @ConditionalOnMissingBean(School.class)
    public School school(Klass klass,Student student){
        School school = new School();
        school.setKlass(klass);
        school.setStudent(student);
        school.setName("starter-school");
        return school;
    }
}
