package cn.dizent.javaCourseCodes.week03.gateway.inbound;

import cn.dizent.javaCourseCodes.week03.gateway.filter.NettyRequestFilter;
import cn.dizent.javaCourseCodes.week03.gateway.filter.NettyResponseFilter;
import cn.dizent.javaCourseCodes.week03.gateway.outbound.HttpOutboundServer;
import cn.dizent.javaCourseCodes.week03.gateway.router.NettyGatewayRouter;
import cn.dizent.javaCourseCodes.week03.gateway.router.NettyRouter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 11:31
 * @Description:
 */
public class InboundHttpInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel socketChannel) {
        NettyResponseFilter nettyResponseFilter = new NettyResponseFilter();
        NettyRouter nettyGatewayRouter = new NettyGatewayRouter();

        HttpOutboundServer outboundServer = new HttpOutboundServer(nettyGatewayRouter,nettyResponseFilter);

        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new HttpServerCodec());
        channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        channelPipeline.addLast(new InboundHandler(outboundServer));
    }
}
