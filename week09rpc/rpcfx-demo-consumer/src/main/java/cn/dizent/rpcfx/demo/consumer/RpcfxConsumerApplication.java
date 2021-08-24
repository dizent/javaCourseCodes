package cn.dizent.rpcfx.demo.consumer;

import cn.dizent.rpcfx.demo.client.Rpcfx;
import cn.dizent.rpcfx.demo.common.IOrderService;
import cn.dizent.rpcfx.demo.common.Order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 20:58
 * @Description:
 */
@SpringBootApplication
public class RpcfxConsumerApplication {

    public static void main(String[] args) {

        IOrderService orderService = Rpcfx.create(IOrderService.class,"http://localhost:9001");
        Order order = orderService.findOrderById(1);
        System.out.println("find user id=1 from server: " + order.getInfo());
        Order order2 = orderService.findOrderById(2);
        System.out.println("find user id=1 from server: " + order2.getInfo());

//        SpringApplication.run(RpcfxConsumerApplication.class,args);
    }
}
