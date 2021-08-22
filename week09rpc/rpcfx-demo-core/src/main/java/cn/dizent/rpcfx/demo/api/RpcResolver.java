package cn.dizent.rpcfx.demo.api;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 21:17
 * @Description:
 */
public interface RpcResolver {

    <T> T resolve(Class targetClass);
}
