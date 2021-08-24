package cn.dizent.javaCourseCodes.week03.gateway.inbound;

import cn.dizent.javaCourseCodes.week03.gateway.filter.NettyRequestFilter;
import cn.dizent.javaCourseCodes.week03.gateway.outbound.HttpOutboundServer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 13:14
 * @Description:
 */
public class InboundHandler extends ChannelInboundHandlerAdapter {

    HttpOutboundServer outboundServer;

    private NettyRequestFilter requestFilter = new NettyRequestFilter();

    public InboundHandler(HttpOutboundServer outboundServer) {
        this.outboundServer = outboundServer;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;


            outboundServer.handler(fullHttpRequest,ctx,requestFilter);

        }finally {
            ReferenceCountUtil.release(msg);
        }
    }


}
