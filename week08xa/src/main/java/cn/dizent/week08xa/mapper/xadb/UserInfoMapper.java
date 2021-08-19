package cn.dizent.week08xa.mapper.xadb;

import cn.dizent.week08xa.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dizent
 * @since 2021-08-15
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    public int addNewUser(@Param("entity") UserInfo userInfo);
}
