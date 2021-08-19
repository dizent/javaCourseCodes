package cn.dizent.week08xa.service;

import cn.dizent.week08xa.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dizent
 * @since 2021-08-15
 */
public interface OrderService extends IService<Order> {

    int addNewOrder(Order order);

    Order getByOrderId(Long orderId);
}
