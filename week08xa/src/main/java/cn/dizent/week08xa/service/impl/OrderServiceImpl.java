package cn.dizent.week08xa.service.impl;

import cn.dizent.week08xa.entity.Order;
import cn.dizent.week08xa.entity.UserInfo;
import cn.dizent.week08xa.mapper.xadb.OrderMapper;
import cn.dizent.week08xa.service.OrderService;
import cn.dizent.week08xa.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dizent
 * @since 2021-08-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    UserInfoService userInfoService;

    @Override
    @ShardingTransactionType(TransactionType.XA)
    public int addNewOrder(Order order) {
        int newOrder = orderMapper.addNewOrder(order);
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("dizent");
        userInfo.setGender(1);
        userInfo.setUserId(1234567L);
        userInfoService.addNewUser(userInfo);
        System.out.println(order.toString());
        System.out.println(userInfo.toString());
        return newOrder;
    }

    @Override
    public Order getByOrderId(Long orderId) {
        return orderMapper.getOrderById(orderId);
    }
}
