package cn.dizent.javaCourseCodes.week03.gateway.inbound;

import cn.dizent.javaCourseCodes.week03.gateway.getData.NettyHttpClientHandler;
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
import lombok.Data;

import java.util.List;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 11:18
 * @Description:
 */
@Data
public class HttpInboundServer {

    int port;

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //设置通道的选项参数
        try {

            //ChannelOption.SO_BACKLOG 用来初始化服务端可连接队列,参数为队列大小
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    //ChanneOption.SO_REUSEADDR 表示允许重复使用本地地址和端口
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    //ChannelOption.SO_KEEPALIVE 用于可能长时间没有数据交流的连接,在两小时内没有数据的通信时，TCP会自动发送一个活动探测数据报文
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //ChannelOption.SO_SNDBUF 用于设置发送区缓冲区内存大小
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    //ChannelOption.SO_RCVBUF 用于设置接收区缓冲区内存大小
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    //ChannelOption.TCP_NODELAY 该参数的作用就是禁止使用Nagle算法，使用于小数据即时传输(Nagle算法-将小的数据包组装成大的帧然后发送,而不是输入一次发送一次)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    //EpollChannelOption.SO_REUSEPORT 支持多个进程或者线程绑定到同一端口，提高服务器程序的性能
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    //ChannelOption.ALLOCATOR 使用对象池，重用缓冲区
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new InboundHttpInitializer());

            Channel channel = serverBootstrap.bind(port).sync().channel();

            channel.closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}
