package cn.dizent.spring.model;

import lombok.Data;

import java.util.List;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 08:34
 * @Description:
 */
@Data
public class Klass {

    private String classNo;

    private List<Student> studentList;
}
