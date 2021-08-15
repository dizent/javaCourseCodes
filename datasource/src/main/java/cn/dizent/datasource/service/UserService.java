package cn.dizent.datasource.service;

import cn.dizent.datasource.dao.TUserInfoRepository;
import cn.dizent.datasource.dsConfig.Ds;
import cn.dizent.datasource.entity.TUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Auther: 布谷
 * @Date: 2021/8/10 14:43
 * @Description:
 */
@Service
@Transactional
public class UserService {

    @Autowired
    TUserInfoRepository userInfoRepository;

    @Ds
    public int addNewUser(TUserInfo tUserInfo){
        tUserInfo =  userInfoRepository.saveAndFlush(tUserInfo);
        return tUserInfo.getId();
    }

    @Ds(dsName = "readDatasource")
    public TUserInfo getUserInfo(Integer userId){
        return userInfoRepository.getOne(userId);
    }
}
