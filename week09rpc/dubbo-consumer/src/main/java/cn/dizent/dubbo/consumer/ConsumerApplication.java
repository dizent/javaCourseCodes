package cn.dizent.dubbo.consumer;

import cn.dizent.rpcfx.demo.common.IOrderService;
import cn.dizent.rpcfx.demo.common.Order;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dizent
 */
@SpringBootApplication
@EnableDubboConfig
@EnableDubbo(scanBasePackages = "cn.dizent.dubbo.consumer")
@RestController
public class ConsumerApplication {


    @DubboReference(version = "${dubbo.version}",check = true,interfaceClass = IOrderService.class)
    private IOrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @GetMapping("/rpc")
    public void testDubboRpc(){
        Order order = orderService.findOrderById(1);
        System.out.println("find user id=1 from server: " + order.getInfo());
        Order order2 = orderService.findOrderById(2);
        System.out.println("find user id=1 from server: " + order2.getInfo());

    }

}
