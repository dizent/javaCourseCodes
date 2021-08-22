package cn.dizent.rpcfx.demo.provider;

import cn.dizent.rpcfx.demo.api.RpcRequest;
import cn.dizent.rpcfx.demo.api.RpcResolver;
import cn.dizent.rpcfx.demo.api.RpcResponse;
import cn.dizent.rpcfx.demo.common.IOrderService;
import cn.dizent.rpcfx.demo.service.RpcInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 20:55
 * @Description:
 */
@SpringBootApplication
@RestController
public class RpcfxServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcfxServiceApplication.class,args);
    }

    @Autowired
    RpcInvoker invoker;

    @PostMapping("/")
    public RpcResponse invoke(@RequestBody RpcRequest request){
        return invoker.invoke(request);
    }

    @Bean
    public RpcInvoker createInvoker(@Autowired RpcResolver resolver){
        return new RpcInvoker(resolver);
    }

    @Bean
    public RpcResolver createResolver(){
        return new DizentResolver();
    }

    @Bean(name = "cn.dizent.rpcfx.demo.common.IOrderService")
    public IOrderService createOrderService(){
        return new OrderServiceImpl();
    }
}
