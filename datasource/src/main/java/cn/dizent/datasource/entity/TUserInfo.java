package cn.dizent.datasource.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Auther: 布谷
 * @Date: 2021/8/10 14:02
 * @Description:
 */
@Data
@Entity
@Table(name = "t_userinfo")
public class TUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    private byte sex;

    @Column(name = "create_at",insertable = false,updatable = false)
    private LocalDateTime createAt;

    @Column(name = "update_at",insertable = false,updatable = false)
    private LocalDateTime updateAt;
}
