package cn.dizent.javaCourseCodes.week02nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Auther: 布谷
 * @Date: 2021/7/7 14:55
 * @Description:
 */
public class NettyHttpServer {

    public static void main(String[] args) {
        int port = 8808;

        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childOption(ChannelOption.SO_REUSEADDR,true)
                    .childOption(ChannelOption.SO_RCVBUF,32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF,32 * 1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT,true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            bootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInitializer());

            Channel channel = bootstrap.bind(port).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为 http:localhost:" + port + '/');
            channel.closeFuture().sync();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
