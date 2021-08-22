package cn.dizent.rpcfx.demo.service;

import cn.dizent.rpcfx.demo.api.RpcRequest;
import cn.dizent.rpcfx.demo.api.RpcResolver;
import cn.dizent.rpcfx.demo.api.RpcResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 21:44
 * @Description:
 */
public class RpcInvoker {

    private RpcResolver resolver;

    public RpcInvoker(RpcResolver resolver) {
        this.resolver = resolver;
    }

    public RpcResponse invoke(RpcRequest request) {

        RpcResponse response = new RpcResponse();
        String className = request.getClassName();
        try {
            Object service = resolver.resolve(Class.forName(className));
            Method method = resolveMethodFromClass(service.getClass(),request.getMethod());
            Object result = method.invoke(service,request.getParams());

            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            return response;
        } catch (Exception e){
            response.setStatus(false);
            response.setException(e);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
}
