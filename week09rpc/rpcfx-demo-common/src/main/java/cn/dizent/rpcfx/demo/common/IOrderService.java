package cn.dizent.rpcfx.demo.common;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 21:05
 * @Description:
 */
public interface IOrderService {

    Order findOrderById(int orderId);
}
