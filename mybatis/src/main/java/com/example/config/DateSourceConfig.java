package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.example.common.DatabaseType;
import com.example.datasource.MultipleDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * springboot集成mybatis的基本入口
 * 1）创建数据源(如果采用的是默认的tomcat-jdbc数据源，则不需要)
 * 2）创建SqlSessionFactory
 * 3）配置事务管理器，除非需要使用事务，否则不用配置
 *
 * @author guodahai
 * @version 2018/4/12 下午2:29
 */
@Configuration
@MapperScan(basePackages = "com.example.dao")
public class DateSourceConfig extends BaseDateSourceConfig {

    @Autowired
    private Environment environment;


    @Value("${new.db.driverClass}")
    private String newDriver;
    @Value("${new.db.jdbcUrl}")
    private String newUrl;
    @Value("${new.db.user}")
    private String newUser;
    @Value("${new.db.password}")
    private String newPassword;

    @Value("${old.db.driverClass}")
    private String oldDriver;
    @Value("${old.db.jdbcUrl}")
    private String oldUrl;
    @Value("${old.db.user}")
    private String oldUser;
    @Value("${old.db.password}")
    private String oldPassword;

    @Bean
    public DataSource newDataSource() throws Exception {
        Properties properties = new Properties();
        properties.put("driverClassName", environment.getProperty("new.db.driverClass"));
        properties.put("url", environment.getProperty("new.db.jdbcUrl"));
        properties.put("username", environment.getProperty("new.db.user"));
        properties.put("password", environment.getProperty("new.db.password"));
        return DruidDataSourceFactory.createDataSource(properties);
//        final DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(newDriver);
//        dataSource.setUrl(newUrl);
//        dataSource.setUsername(newUser);
//        dataSource.setPassword(newPassword);
//        secondaryConfig(dataSource);
//        return dataSource;
    }

    @Bean
    public DataSource oldDataSource() {
        final DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(oldDriver);
        dataSource.setUrl(oldUrl);
        dataSource.setUsername(oldUser);
        dataSource.setPassword(oldPassword);
        secondaryConfig(dataSource);
        return dataSource;
    }

    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，
     * 默认选择哪一个，而不是让@autowire注解报错
     * @DependsOn 用于强制初始化其他Bean。可以修饰Bean类或方法，
     * 使用该Annotation时可以指定一个字符串数组作为参数，
     * 每个数组元素对应于一个强制初始化的Bean。
     */
    @Bean
    @Primary
    @DependsOn({"newDataSource", "oldDataSource"})
    public MultipleDataSource dataSource() throws Exception {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.NEW_DATASOURCE, newDataSource());
        targetDataSources.put(DatabaseType.OLD_DATASOURCE, oldDataSource());
        MultipleDataSource dataSource = new MultipleDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(newDataSource());
        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(MultipleDataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        sqlSessionFactoryBean.setTypeAliasesPackage(environment.getProperty("mybatis.typeAliasesPackage"));// 指定基包
//        sqlSessionFactoryBean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources(environment.getProperty("mybatis.mapperLocations")));//

        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(MultipleDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}
