package cn.dizent.week08xa.controller;


import cn.dizent.week08xa.entity.Order;
import cn.dizent.week08xa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dizent
 * @since 2021-08-15
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("addNewOrder")
    public @ResponseBody String addNewOrder(@RequestBody Order order){
        return String.valueOf(orderService.addNewOrder(order));
    }

    @PostMapping("getOrder")
    public @ResponseBody Order getOrderById(Long orderId){
        return orderService.getByOrderId(orderId);
    }

}

