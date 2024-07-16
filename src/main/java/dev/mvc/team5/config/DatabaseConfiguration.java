package dev.mvc.team5.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // Spring Boot 환경 설정 파일임으로 읽어들여 설정 할 것
@PropertySource("classpath:/application.properties")  // 설정 파일 위치
@MapperScan(basePackages = {"dev.mvc.category", 
                            "dev.mvc.notice",
                            "dev.mvc.noticeFile",
                            "dev.mvc.shoes",
                            "dev.mvc.option",
                            "dev.mvc.member",
                            "dev.mvc.review",
                            "dev.mvc.report",
                            "dev.mvc.reportType",
                            "dev.mvc.likes",
                            "dev.mvc.hates",
                            "dev.mvc.basket",
                            "dev.mvc.shoesInquiry",
                            "dev.mvc.paymentInquiry",
                            "dev.mvc.otherInquiry",
                            "dev.mvc.loginHistory",
                            "dev.mvc.payment",
                            "dev.mvc.paymentTotal",
                            "dev.mvc.paymentDetails",
                            "dev.mvc.delivery",
                            "dev.mvc.shoesFile"})  // DAO interface를 찾는 위치
public class DatabaseConfiguration {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")  // 설정 파일의 접두사 선언 spring.datasource.hikari....
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }
    
    @Bean
    public DataSource dataSource() throws Exception{
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println(dataSource.toString());  // 정상적으로 연결 되었는지 해시코드로 확인
        return dataSource;
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // "/src/main/resources/mybatis" 폴더의 파일명이 "xml"로 끝나는 파일 매핑
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mybatis/**/*.xml"));
        //sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis/config/mybatis-config.xml"));
        
        
        
        return sqlSessionFactoryBean.getObject();
    }
    
    
    
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
