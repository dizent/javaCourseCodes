package cn.dizent.javaCourseCodes.week03.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.internal.StringUtil;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 16:23
 * @Description:
 */
public class NettyRequestFilter {

    public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        String gateway = fullHttpRequest.headers().get("gateway");
        if (StringUtil.isNullOrEmpty(gateway)) {
            fullHttpRequest.headers().set("gateway", "netty");
        } else {
            fullHttpRequest.headers().set("nio", "netty");
        }
    }


}
