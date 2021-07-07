package cn.dizent.javaCourseCodes.week02nio.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

/**
 * @Auther: 布谷
 * @Date: 2021/7/7 14:47
 * @Description:
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            //
            FullHttpRequest fullHttpRequest = (FullHttpRequest)msg;
            String url = fullHttpRequest.uri();
            //
            if(url.contains("/test")){
                handlerTest(fullHttpRequest,ctx,"hello Dizent");
            }else{
                handlerTest(fullHttpRequest, ctx,"Hello Others");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    private void handlerTest(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx,String body) {
        FullHttpResponse response = null;

        try {
            String value = body;

            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes()));
            response.headers().set("Content-Type", "application/json");
            response.headers().set("Content-Length", response.content().readableBytes());
        }catch (Exception e){
            System.out.printf(""+ e.getMessage());
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.NO_CONTENT);
        }finally {
            if(fullHttpRequest !=null){
                if(!HttpUtil.isKeepAlive(fullHttpRequest)){
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                }else{
                    response.headers().set(CONNECTION,KEEP_ALIVE);
                    ctx.write(response);
                }
            }
        }

    }
}
