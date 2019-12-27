package com.ocean.interceptor;

import com.ocean.common.DatabaseType;
import com.ocean.datasource.DatabaseContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author guodahai
 * @version 2018/4/13 上午9:58
 */
@Component
@Aspect
public class DataSourceInterceptor {

    private Logger logger = LoggerFactory.getLogger(DataSourceInterceptor.class);

    @Pointcut("execution(* com.ocean.dao.NewUserMapper.*(..))")
    public void newDataSource() {
    }

    @Before("newDataSource()")
    public void setNewDataSource() {
        DatabaseContextHolder.setDatabaseType(DatabaseType.NEW_DATASOURCE);
        logger.info("当前数据源为:{}", DatabaseType.NEW_DATASOURCE);
    }

    @Pointcut("execution(* com.ocean.dao.OldUserMapper.*(..))")
    public void oldDataSource() {
    }

    @Before("oldDataSource()")
    public void setoldDataSource() {
        DatabaseContextHolder.setDatabaseType(DatabaseType.OLD_DATASOURCE);
        logger.info("当前数据源为:{}", DatabaseType.OLD_DATASOURCE);
    }
}
