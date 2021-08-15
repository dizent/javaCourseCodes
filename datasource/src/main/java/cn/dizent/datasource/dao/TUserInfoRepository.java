package cn.dizent.datasource.dao;

import cn.dizent.datasource.entity.TUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: 布谷
 * @Date: 2021/8/10 14:04
 * @Description:
 */
public interface TUserInfoRepository extends JpaRepository<TUserInfo,Integer> {
}
