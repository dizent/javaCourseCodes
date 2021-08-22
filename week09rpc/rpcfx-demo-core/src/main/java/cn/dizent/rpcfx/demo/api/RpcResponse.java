package cn.dizent.rpcfx.demo.api;

import lombok.Data;

/**
 * @Auther: 布谷
 * @Date: 2021/8/22 21:16
 * @Description:
 */
@Data
public class RpcResponse {

    Object result;

    Boolean status;

    Exception exception;
}
