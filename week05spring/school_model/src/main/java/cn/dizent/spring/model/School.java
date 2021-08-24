package cn.dizent.spring.model;

import lombok.Data;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 08:35
 * @Description:
 */
@Data
public class School {

    private String name;

    private Klass klass;

    private Student student;
}
