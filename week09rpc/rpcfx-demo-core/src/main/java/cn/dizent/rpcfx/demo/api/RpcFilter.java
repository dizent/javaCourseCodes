package cn.dizent.rpcfx.demo.api;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 21:22
 * @Description:
 */
public interface RpcFilter {

    boolean filter(RpcRequest request);
}
