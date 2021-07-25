package cn.dizent.spring.service;

import cn.dizent.spring.aop.IStudent;
import cn.dizent.spring.model.Klass;
import cn.dizent.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 08:51
 * @Description:
 */
@Service
public class StudentService01 implements IStudent {

    @Autowired(required = true)
    Klass klass;

    @Resource(name = "student2002")
    Student student;

    @Override
    public void read() {
        System.out.println(student.getName() + "read start");
    }
}
