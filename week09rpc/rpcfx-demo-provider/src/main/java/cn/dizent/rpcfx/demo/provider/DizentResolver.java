package cn.dizent.rpcfx.demo.provider;

import cn.dizent.rpcfx.demo.api.RpcResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author dizent
 * @Auther: 布谷
 * @Date: 2021/8/22 21:45
 * @Description:
 */
public class DizentResolver implements RpcResolver,ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public <T> T resolve(Class targetClass) {
        return (T)applicationContext.getBean(targetClass);
    }
}
