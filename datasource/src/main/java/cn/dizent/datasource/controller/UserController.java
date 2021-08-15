package cn.dizent.datasource.controller;

import cn.dizent.datasource.entity.TUserInfo;
import cn.dizent.datasource.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: 布谷
 * @Date: 2021/8/10 14:06
 * @Description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("getinfo")
    public String getUserInfo(Integer userId){
        return userService.getUserInfo(userId).toString();
    }

    @PostMapping("addUser")
    public Integer addUser(@RequestBody TUserInfo userInfo){
        return userService.addNewUser(userInfo);
    }
}
