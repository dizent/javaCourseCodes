package cn.dizent.redis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: 布谷
 * @Date: 2021/9/5 21:19
 * @Description:
 */
@Data
public class UserEntity implements Serializable {

    Integer id;

    String userName;

    String userInfo;


}
