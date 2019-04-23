package com.github.yiyan1992.carloan.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 数据库连接配置类
 *
 * @author admin
 */
@Configuration
public class DruidConfig {

    @Resource
    private MySqlEntity druidEntity;

    @Bean
    public DataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidEntity.getDrive());
        dataSource.setUrl(druidEntity.getUrl());
        dataSource.setUsername(druidEntity.getUsername());
        dataSource.setPassword(druidEntity.getPassword());
        return dataSource;
    }
}
