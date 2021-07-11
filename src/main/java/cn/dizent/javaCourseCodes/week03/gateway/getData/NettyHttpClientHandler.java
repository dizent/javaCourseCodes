package cn.dizent.javaCourseCodes.week03.gateway.getData;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 17:32
 * @Description:
 */
public enum NettyHttpClientHandler{
    /**
     * 单例实例
     */
    INSTANCE;


    public void nettyGet(ChannelHandlerContext ctx, String url){
        try {
            URI uri = new URI(url);
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_0, HttpMethod.GET, uri.toASCIIString());
            request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
            ctx.writeAndFlush(request);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


}
