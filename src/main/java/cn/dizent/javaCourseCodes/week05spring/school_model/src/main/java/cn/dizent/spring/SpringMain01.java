package cn.dizent.spring;

import cn.dizent.spring.config.PackageScanConfig;
import cn.dizent.spring.aop.IStudent;
import cn.dizent.spring.aop.IStudentService03;
import cn.dizent.spring.config.AnnotationConfig;
import cn.dizent.spring.config.BeanImportConfig;
import cn.dizent.spring.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 09:11
 * @Description:
 */
@EnableSpringConfigured
public class SpringMain01 {

    public static void main(String[] args) {

        //xml装配
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student = (Student) applicationContext.getBean("student2001");

        System.out.println(student);

        IStudent studentService = applicationContext.getBean(IStudent.class);

        studentService.read();

        //包扫描装配
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(PackageScanConfig.class);

        IStudent studentService2 = configApplicationContext.getBean(IStudent.class);

        studentService2.read();

        //annotation装配
        AnnotationConfigApplicationContext configApplicationContext2 = new AnnotationConfigApplicationContext(AnnotationConfig.class);

        Student student2 = configApplicationContext2.getBean(Student.class);

        System.out.println(student2);

        //自定义包import装配
        AnnotationConfigApplicationContext configApplicationContext3 = new AnnotationConfigApplicationContext(BeanImportConfig.class);

        IStudent studentService3 = configApplicationContext3.getBean(IStudentService03.class);

        studentService3.read();

    }


}
