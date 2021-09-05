package cn.dizent.dubbo.provider.impl;

import cn.dizent.rpcfx.demo.common.IOrderService;
import cn.dizent.rpcfx.demo.common.Order;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Auther: 布谷
 * @Date: 2021/8/28 18:52
 * @Description:
 */
@DubboService(version = "${dubbo.version}")
public class OrderServiceImpl implements IOrderService {
    
    @Override
    public Order findOrderById(int orderId) {
        return new Order(orderId,"dubbo interface return order info id = " + orderId, BigDecimal.valueOf(999.99));
    }
}
