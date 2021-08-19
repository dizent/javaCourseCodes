package cn.dizent.week08xa.mapper.xadb;

import cn.dizent.week08xa.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dizent
 * @since 2021-08-15
 */
public interface OrderMapper extends BaseMapper<Order> {

    int addNewOrder(@Param("entity")Order order);

    Order getOrderById(Long orderId);
}
