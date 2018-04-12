package com.example.mybatis.config;

import com.alibaba.druid.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.sql.SQLFeatureNotSupportedException;


/**
 * @author guodahai
 * @version 2018/4/12 下午3:01
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    private static final Logger logger = LoggerFactory.getLogger(MultipleDataSource.class);

    /**
     * 目标数据源
     */
    private static final ThreadLocal<String> TARGET_DATA_SOURCE = new ThreadLocal<>();

    /**
     * 默认数据源--指标监控的
     */
    public static final String DEFAULT_DATA_SOURCE = "newDataSource";

    /**
     * 设置进去的当前线程数据源进行数据源切换
     *
     * @return 数据源名称
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String targetDataSource = TARGET_DATA_SOURCE.get();
        if (StringUtils.isEmpty(targetDataSource)) {
            //默认数据源为指标监控数据源
            targetDataSource = DEFAULT_DATA_SOURCE;
            TARGET_DATA_SOURCE.set(targetDataSource);
        }
        logger.debug("当前线程数据源----------------:{}", targetDataSource);
        return targetDataSource;
    }

    /**
     * 设置数据源名
     *
     * @param target
     */
    public static void setTargetDataSource(String target) {
        TARGET_DATA_SOURCE.set(target);
    }

    /**
     * 取数据源名
     *
     * @return
     */
    public static String getTargetDataSource() {
        return TARGET_DATA_SOURCE.get();
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}