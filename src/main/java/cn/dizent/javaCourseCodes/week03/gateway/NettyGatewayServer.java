package cn.dizent.javaCourseCodes.week03.gateway;

import cn.dizent.javaCourseCodes.week03.gateway.inbound.HttpInboundServer;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 11:15
 * @Description:
 */
public class NettyGatewayServer {


    public static void main(String[] args) {
        String proxyPort = System.getProperty("proxyPort","9999");

        int port = Integer.parseInt(proxyPort);

        HttpInboundServer server = new HttpInboundServer();
        server.setPort(port);
        server.start();
    }
}
