package cn.dizent.school_demo.controller;

import cn.dizent.spring.model.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 布谷
 * @Date: 2021/7/25 17:53
 * @Description:
 */
@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired(required = false)
    School school;


    @RequestMapping("test")
    public String schoolRead(String param){
        System.out.println("接受到请求+" + param);
        return "nihao,school";
    }
}
