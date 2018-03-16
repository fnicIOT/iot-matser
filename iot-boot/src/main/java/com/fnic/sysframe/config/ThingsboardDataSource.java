package com.fnic.sysframe.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.fnic.mybatis.thingsboard", sqlSessionTemplateRef  = "thingsboardSqlSessionTemplate")
public class ThingsboardDataSource {

    @Bean(name = "tbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.thingsboard")
    @Primary
    public DataSource buildDateSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "thingsboardSessionFactory")
    @Primary
    public SqlSessionFactory thingsboardSessionFactory(@Qualifier("tbDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/fnic/mybatis/thingsboard/dao/*.xml"));
        return  bean.getObject();
    }

    @Bean(name = "thingsboardTransactionManager")
    @Primary
    public PlatformTransactionManager thingsboardTransactionManager(@Qualifier("tbDataSource") DataSource dataSource) {
        return  new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "thingsboardSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate thingsboardSqlSessionTemplate(@Qualifier("thingsboardSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return  new SqlSessionTemplate(sqlSessionFactory);
    }
}
