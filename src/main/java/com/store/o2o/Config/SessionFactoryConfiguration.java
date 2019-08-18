package com.store.o2o.Config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class SessionFactoryConfiguration {
    private static String mybatisConfigFile;
    private static String mapperPath;

    @Autowired
    private DataSource dataSource;

    @Value("${type_alias_package}")
    private String typeAliasPackage;


    @Value("${mybatis_config_file}")
    public void setMybatisConfigFile(String mybatisConfigFile){
        SessionFactoryConfiguration.mybatisConfigFile=mybatisConfigFile;
    }

    @Value("${mapper_path}")
    public void setMapperPath(String mapperPath) {
        SessionFactoryConfiguration.mapperPath = mapperPath;
    }

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean(){
        SqlSessionFactoryBean sessionFactoryBean= new SqlSessionFactoryBean();
        sessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver=new PathMatchingResourcePatternResolver();
        String packageSearchPath= ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+mapperPath;
        sessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResource(packageSearchPath));
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
        return sessionFactoryBean;
    }
}
