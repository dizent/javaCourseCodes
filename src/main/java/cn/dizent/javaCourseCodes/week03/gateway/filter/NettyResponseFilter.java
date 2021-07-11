package cn.dizent.javaCourseCodes.week03.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 20:55
 * @Description:
 */
public class NettyResponseFilter {

    public void filter(FullHttpResponse fullHttpResponse, ChannelHandlerContext ctx) {
        fullHttpResponse.headers().set("gateway", "netty");
    }
}
