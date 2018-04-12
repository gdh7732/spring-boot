package com.example.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author guodahai
 * @version 2018/4/12 下午2:29
 */
@Configuration
public class OldDateSourceConfig extends BaseDateSourceConfig {

    @Value("${old.db.driverClass}")
    private String dbUrl;
    @Value("${old.db.jdbcUrl}")
    private String dbUser;
    @Value("${old.db.user}")
    private String dbPassword;
    @Value("${old.db.password}")
    private String driver;

    @Bean(name = "oldDataSource")
    public DataSource dataSource() {
        final DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        secondaryConfig(dataSource);
        return dataSource;
    }

}
