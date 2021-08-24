package cn.dizent.rpcfx.demo.client;

import cn.dizent.rpcfx.demo.api.RpcFilter;
import cn.dizent.rpcfx.demo.api.RpcRequest;
import cn.dizent.rpcfx.demo.api.RpcResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 21:21
 * @Description:
 */
public final class Rpcfx {

    static {
        ParserConfig.getGlobalInstance().addAccept("cn.dizent");
    }

    public static <T> T create(final Class<T> serviceClass, final String url, RpcFilter... filters) {

        // 0. 替换动态代理 -> 字节码生成
//        return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(),
//                new Class[]{serviceClass},
//                new RpcInvocationHandler(serviceClass, url, filters));

        return new RpcProxy(serviceClass,url,filters).createProxy(serviceClass);

    }

    public static class RpcProxy implements MethodInterceptor{

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final String url;
        private final RpcFilter[] filters;

        public <T> RpcProxy(Class<T> serviceClass, String url, RpcFilter... filters) {
            this.serviceClass = serviceClass;
            this.url = url;
            this.filters = filters;
        }

        public <T> T createProxy(Class<T> serviceClass){
            Enhancer e = new Enhancer();
            e.setSuperclass(serviceClass);
            e.setCallback(this);
            Object proxy = e.create();
            return (T)proxy;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
            RpcRequest request = new RpcRequest();
            request.setClassName(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);

            if (null!=filters) {
                for (RpcFilter filter : filters) {
                    if (!filter.filter(request)) {
                        return null;
                    }
                }
            }

            RpcResponse response = post(request, url);

            // 加filter地方之三
            // Student.setTeacher("cuijing");

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException

            return JSON.parse(response.getResult().toString());
        }

        private RpcResponse post(RpcRequest req, String url) throws IOException {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: "+reqJson);

            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSONTYPE, reqJson))
                    .build();
            String respJson = client.newCall(request).execute().body().string();
            System.out.println("resp json: "+respJson);
            return JSON.parseObject(respJson, RpcResponse.class);
        }
    }

    public static class RpcInvocationHandler implements InvocationHandler {

        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;
        private final String url;
        private final RpcFilter[] filters;

        public <T> RpcInvocationHandler(Class<T> serviceClass, String url, RpcFilter... filters) {
            this.serviceClass = serviceClass;
            this.url = url;
            this.filters = filters;
        }

        // 可以尝试，自己去写对象序列化，二进制还是文本的，，，rpcfx是xml自定义序列化、反序列化，json: code.google.com/p/rpcfx
        // int byte char float double long bool
        // [], data class

        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {

            // 加filter地方之二
            // mock == true, new Student("hubao");

            RpcRequest request = new RpcRequest();
            request.setClassName(this.serviceClass.getName());
            request.setMethod(method.getName());
            request.setParams(params);

            if (null!=filters) {
                for (RpcFilter filter : filters) {
                    if (!filter.filter(request)) {
                        return null;
                    }
                }
            }

            RpcResponse response = post(request, url);

            // 加filter地方之三
            // Student.setTeacher("cuijing");

            // 这里判断response.status，处理异常
            // 考虑封装一个全局的RpcfxException

            return JSON.parse(response.getResult().toString());
        }

        private RpcResponse post(RpcRequest req, String url) throws IOException {
            String reqJson = JSON.toJSONString(req);
            System.out.println("req json: "+reqJson);

            // 1.可以复用client
            // 2.尝试使用httpclient或者netty client
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSONTYPE, reqJson))
                    .build();
            String respJson = client.newCall(request).execute().body().string();
            System.out.println("resp json: "+respJson);
            return JSON.parseObject(respJson, RpcResponse.class);
        }
    }
}
