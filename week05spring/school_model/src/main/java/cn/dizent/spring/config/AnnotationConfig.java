package cn.dizent.spring.config;

import cn.dizent.spring.model.Klass;
import cn.dizent.spring.model.Student;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 10:59
 * @Description:
 */
@Data
@Configuration
public class AnnotationConfig {

    @Bean
    @Qualifier("student1001")
    public Student student(){
        Student student = new Student();
        student.setName("bugu1001");
        student.setId(1001);
        return student;
    }


    @Bean
    public Klass klass(@Qualifier("student1001")Student student){
        Klass klass = new Klass();
        klass.setClassNo("klass101");
        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        klass.setStudentList(studentList);
        return klass;
    }
}
