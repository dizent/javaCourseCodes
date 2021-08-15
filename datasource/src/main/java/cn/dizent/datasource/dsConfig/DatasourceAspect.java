package cn.dizent.datasource.dsConfig;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Auther: 布谷
 * @Date: 2021/8/10 14:36
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class DatasourceAspect implements Ordered {

    @Pointcut("@annotation(cn.dizent.datasource.dsConfig.Ds)")
    public void dataSourcePointCut(){

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        Ds ds = method.getAnnotation(Ds.class);
        if (ds == null) {
            DynamicDataSource.setDataSource("writeDatasource");
            log.info("set datasource is writeDatasource");
        } else {
            DynamicDataSource.setDataSource(ds.dsName());
            log.info("set datasource is " + ds.dsName());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
            log.info("clean datasource");
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
