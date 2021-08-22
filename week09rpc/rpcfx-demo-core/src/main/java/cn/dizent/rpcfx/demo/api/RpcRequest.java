package cn.dizent.rpcfx.demo.api;

import lombok.Data;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 21:15
 * @Description:
 */
@Data
public class RpcRequest {

    String className;

    String method;

    Object[] params;
}
