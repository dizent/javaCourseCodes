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
        return new Order(1,"mock order info", BigDecimal.valueOf(999.99));
    }
}
