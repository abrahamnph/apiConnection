package com.connection.apihub;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;



@Slf4j
public class TransactionReadonlyAspect {
    @Around("@annotation(transactional)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, org.springframework.transaction.annotation.Transactional transactional) throws Throwable {
        log.info("Aspect executed");
        try {
            if (transactional.readOnly()) {
                DbContextHolder.setDbType(UtilsTransactiion.DataSourceType.READ_ONLY);
            }
            log.info("timeout --->"+transactional.timeout());
            log.info("timeout --->"+transactional.timeoutString());
            log.info("transactional  "+transactional.readOnly());
            log.info("transactional  "+transactional.value());
            log.info("transactional  "+transactional.transactionManager());

            return proceedingJoinPoint.proceed();
        }
        finally {
            log.info("final transaction >>>>>>>>>");
            DbContextHolder.clearDbType();
        }
    }
}
