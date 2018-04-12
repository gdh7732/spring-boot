package com.example.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author guodahai
 * @version 2018/4/12 下午2:29
 */
@Configuration
public class NewDateSourceConfig extends BaseDateSourceConfig {
    @Value("${new.db.driverClass}")
    private String dbUrl;
    @Value("${new.db.jdbcUrl}")
    private String dbUser;
    @Value("${new.db.user}")
    private String dbPassword;
    @Value("${new.db.password}")
    private String driver;

    @Bean(name = "newDataSource")
    @Primary
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
