package cn.dizent.javaCourseCodes.week03.gateway.outbound;

import cn.dizent.javaCourseCodes.week03.gateway.NamedThreadFactory;
import cn.dizent.javaCourseCodes.week03.gateway.filter.NettyRequestFilter;
import cn.dizent.javaCourseCodes.week03.gateway.filter.NettyResponseFilter;
import cn.dizent.javaCourseCodes.week03.gateway.getData.HttpClientRequestService;
import cn.dizent.javaCourseCodes.week03.gateway.getData.OkHttpRequestService;
import cn.dizent.javaCourseCodes.week03.gateway.router.NettyRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

/**
 * @Auther: 布谷
 * @Date: 2021/7/11 13:29
 * @Description:
 */
public class HttpOutboundServer extends ChannelOutboundHandlerAdapter {

    private final ThreadPoolExecutor executorService;

    NettyRouter router;
    NettyResponseFilter responseFilter;

    public HttpOutboundServer(NettyRouter router, NettyResponseFilter responseFilter) {
        this.router = router;
        this.responseFilter = responseFilter;
        executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
                Runtime.getRuntime().availableProcessors() * 2 + 1,
                1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(500),
                new NamedThreadFactory("nio-d"),
                new ThreadPoolExecutor.AbortPolicy());
    }

    public void handler(final FullHttpRequest fullHttpRequest, final ChannelHandlerContext ctx,final NettyRequestFilter requestFilter) {
        String url = fullHttpRequest.uri();

        final String occurUrl = router.route() + url;

        requestFilter.filter(fullHttpRequest, ctx);

//        executorService.submit(() -> {
//            String result = HttpClientRequestService.INSTANCE.httpClientGet(occurUrl);
//            fetchResult(ctx, fullHttpRequest, result);
//        });

        executorService.submit(()->{
            String result = OkHttpRequestService.INSTANCE.okHttpGet(occurUrl);
            fetchResult(ctx,fullHttpRequest,result);});

//        executorService.submit(()->{
//            NettyHttpClientHandler.INSTANCE.nettyGet(ctx,occurUrl);
//            fetchResult(ctx,fullHttpRequest,result);
//            });

    }

    private void fetchResult(final ChannelHandlerContext ctx, final FullHttpRequest fullHttpRequest, final String resultBody) {
        FullHttpResponse response = null;

        try {
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(resultBody.getBytes()));
            response.headers().set("Content-Type", "application/json");
            response.headers().set("Content-Length", response.content().readableBytes());
            responseFilter.filter(response,ctx);
        } catch (Exception e) {
            System.out.print("" + e.getMessage());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
            ctx.close();
        } finally {
            if (fullHttpRequest != null) {
                if (!HttpUtil.isKeepAlive(fullHttpRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                    ctx.write(response);
                }
            }
            ctx.flush();
        }
    }
}
