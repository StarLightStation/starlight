package com.spring.biz.common;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration  //  설정 파일임을 명시하는 어노테이션
@MapperScan("com.spring.mapper")    //  매퍼 인터페이스 스캔
public class MyBatisConfig {

	@Bean   //  메서드를 빈으로 등록 하기
	public SqlSessionFactory sqlSession(DataSource dataSource) throws Exception {

		SqlSessionFactoryBean sqlSession = new SqlSessionFactoryBean();
		sqlSession.setDataSource(dataSource);	//	DataSource
		sqlSession.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mybatis/*.xml")); //  매퍼 XML 파일 경로
		return sqlSession.getObject();
	}

}   //  MyBatisConfig
