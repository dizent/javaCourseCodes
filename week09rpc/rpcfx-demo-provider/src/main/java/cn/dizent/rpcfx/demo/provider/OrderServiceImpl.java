package cn.dizent.rpcfx.demo.provider;

import cn.dizent.rpcfx.demo.common.IOrderService;
import cn.dizent.rpcfx.demo.common.Order;

import java.math.BigDecimal;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 21:40
 * @Description:
 */
public class OrderServiceImpl implements IOrderService {
    @Override
    public Order findOrderById(int orderId) {
        return new Order(orderId,"mock order info id = " + orderId, BigDecimal.valueOf(999.99));
    }
}
