package liuzongzong.aop;

import liuzongzong.util.MultipleDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by liuyz on 2016/12/25.
 */
@Component
//@Aspect
public class DataSourceAop {

    private ConcurrentHashMap<String, Object> readOnleMethod = new ConcurrentHashMap<>();

    @Autowired
    private TransactionInterceptor txAdvice;

    //    @Pointcut("execution(* liuzongzong.service.*Service.*(..))")
    public void pointCut() {
    }

    //    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Object aThis = joinPoint.getThis();
        Signature signature = joinPoint.getSignature();
        boolean isSlave = false;
        String name = joinPoint.getSignature().getName();
        Set<Map.Entry<String, Object>> entries = readOnleMethod.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            if (PatternMatchUtils.simpleMatch(key,name)) {
                isSlave = true;
                MultipleDataSource.DataSourceHolder.put(MultipleDataSource.DataSourceHolder.slave);
                return;
            }
        }
        //事务 read only
        TransactionAttributeSource transactionAttributeSource = txAdvice.getTransactionAttributeSource();
        Field nameMap = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
        nameMap.setAccessible(true);
        try {
            Map<String, TransactionAttribute> stringTransactionAttributeMap = (Map<String, TransactionAttribute>) nameMap.get(transactionAttributeSource);
            for (Map.Entry<String, TransactionAttribute> entry : stringTransactionAttributeMap.entrySet()) {
                if (entry.getValue().isReadOnly()) {
                    String key = entry.getKey();
                    readOnleMethod.put(key, entry.getValue());
                    if (PatternMatchUtils.simpleMatch(key, name)) {
                        isSlave = true;
                        MultipleDataSource.DataSourceHolder.put(MultipleDataSource.DataSourceHolder.slave);
                        return;
                    }
                }
            }
        } catch (IllegalAccessException e) {
        }

        MultipleDataSource.DataSourceHolder.put(MultipleDataSource.DataSourceHolder.master);
    }

    //    @AfterReturning(pointcut = "pointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        MultipleDataSource.DataSourceHolder.remove();
    }

    public void after(JoinPoint joinPoint) {
        MultipleDataSource.DataSourceHolder.remove();
    }

    //    @AfterThrowing(pointcut = "pointCut()")
    public void afterThrowing(JoinPoint joinPoint) {
        MultipleDataSource.DataSourceHolder.remove();
    }
}
