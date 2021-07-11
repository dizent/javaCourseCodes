package cn.dizent.javaCourseCodes.week03.gateway.router;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 15:02
 * @Description:
 */
public class NettyGatewayRouter implements NettyRouter{

    static List<String> servers = new ArrayList<>();

    static{
        servers.add("http://localhost:8801");
        servers.add("http://localhost:8802");
        servers.add("http://localhost:8803");
    }

    @Override
    public String route(){
        int serverSize = servers.size();
        Random random = new Random(System.currentTimeMillis());
        return servers.get(random.nextInt(serverSize));
    }
}
