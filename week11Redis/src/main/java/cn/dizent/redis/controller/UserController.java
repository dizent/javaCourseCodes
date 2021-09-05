package cn.dizent.redis.controller;

import cn.dizent.redis.entity.UserEntity;
import cn.dizent.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 布谷
 * @Date: 2021/9/5 21:17
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("getByid")
    public @ResponseBody
    UserEntity getUserByid(Integer userId){
        return userService.getById(userId);
    }

    @GetMapping("getByName")
    public @ResponseBody
    UserEntity getByUserName(String userName){
        return userService.getByUserName(userName);
    }

}
