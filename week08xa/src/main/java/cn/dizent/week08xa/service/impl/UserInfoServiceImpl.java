package cn.dizent.week08xa.service.impl;

import cn.dizent.week08xa.entity.UserInfo;
import cn.dizent.week08xa.mapper.xadb.UserInfoMapper;
import cn.dizent.week08xa.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dizent
 * @since 2021-08-15
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public int addNewUser(UserInfo userInfo) {
        int addRow = userInfoMapper.addNewUser(userInfo);
        return addRow;
    }

}
