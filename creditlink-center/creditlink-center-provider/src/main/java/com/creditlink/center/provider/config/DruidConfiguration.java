/*
 * Copyright (C) 2017  ShenZhen xinLianZhengXin Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳市信联征信有限公司 http://www.credlink.com/
 */
package com.creditlink.center.provider.config;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * DruidConfiguration
 * 
 * @version 2018年1月12日下午3:20:49
 * @author wuliu
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DruidConfiguration {
    
    @Value("${druid_loginUsername}")
    private String loginUsername;
    @Value("${druid_loginPassword}")
    private String loginPassword;
    @Bean
    @ConfigurationProperties("spring.datasource.*")
    public DruidDataSource dataSource(DataSourceProperties properties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.determineDriverClassName());
        dataSource.setUrl(properties.determineUrl());
        dataSource.setUsername(properties.determineUsername());
        dataSource.setPassword(properties.determinePassword());
        DatabaseDriver databaseDriver = DatabaseDriver
                .fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            dataSource.setTestOnBorrow(true);
            dataSource.setValidationQuery(validationQuery);
        }
        try {
            //开启Druid的监控统计功能，mergeStat代替stat表示sql合并,wall表示防御SQL注入攻击
            dataSource.setFilters("mergeStat,wall,log4j");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dataSource;
    }
    
    /**
     * 注册一个Druid内置的StatViewServlet，用于展示Druid的统计信息。
     * @return
     */
     @Bean
     public ServletRegistrationBean DruidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //添加初始化参数：initParams
        //白名单 (没有配置或者为空，则允许所有访问)
        //servletRegistrationBean.addInitParameter("allow","192.168.1.88,127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
       // servletRegistrationBean.addInitParameter("deny","192.168.1.80");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername",loginUsername);
        servletRegistrationBean.addInitParameter("loginPassword",loginPassword);
        //是否能够重置数据(禁用HTML页面上的“Reset All”功能)
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }
     
     /**
      * Web关联监控配置 
      * WebStatFilter用于采集web-jdbc关联监控的数据。在DruidConfiguration中配置
      * 注册一个：filterRegistrationBean,添加请求过滤规则
      * @return
      */
     @Bean
     public FilterRegistrationBean druidStatFilter(){
         FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
         //添加过滤规则.
         filterRegistrationBean.addUrlPatterns("/*");
         //添加不需要忽略的格式信息.
         filterRegistrationBean.addInitParameter(
             "exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
         return filterRegistrationBean;
     }
}
