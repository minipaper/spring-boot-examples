package io.github.minipaper.config;

import io.github.minipaper.support.ASchema;
import io.github.minipaper.support.BSchema;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

public abstract class MyBatisConfiguration {
  public static final String BASE_PACKAGE_PREFIX = "io.github.minipaper";

  public static final String TYPE_ALIASES_PACKAGE = "io.github.minipaper.domain";

  public static final String CONFIG_LOCATION_PATH = "classpath:mybatis/mybatis-config.xml";

  public static final String MAPPER_LOCATIONS_PATH = "classpath:mybatis/mapper/**/*.xml";

  protected void configureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource) throws IOException {
    PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
    sessionFactoryBean.setDataSource(dataSource);
    sessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
    sessionFactoryBean.setConfigLocation(pathResolver.getResource(CONFIG_LOCATION_PATH));
    sessionFactoryBean.setMapperLocations(pathResolver.getResources(MAPPER_LOCATIONS_PATH));
  }
}

@Configuration
@MapperScan(
    basePackages = MyBatisConfiguration.BASE_PACKAGE_PREFIX,
    annotationClass = ASchema.class,
    sqlSessionFactoryRef = "ASqlSessionFactory"
)
@Import(value = {ADataSourceConfiguration.class})
class AMybatisConfig extends MyBatisConfiguration {

  @Bean(name = "ASqlSessionFactory")
  @Primary
  public SqlSessionFactory ASqlSessionFactory(@Qualifier("dataSourceA") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    configureSqlSessionFactory(sessionFactoryBean, dataSource);
    return sessionFactoryBean.getObject();
  }
}

@Configuration
@MapperScan(
    basePackages = MyBatisConfiguration.BASE_PACKAGE_PREFIX,
    annotationClass = BSchema.class, // 어노테이션으로 나누기 싫다면 basePackages 로 나눌수 있다
    sqlSessionFactoryRef = "BSqlSessionFactory"
)
@Import(value = {BDataSourceConfiguration.class})
class BMybatisConfig extends MyBatisConfiguration {

  @Bean(name = "BSqlSessionFactory")
  public SqlSessionFactory BSqlSessionFactory(@Qualifier("dataSourceB") DataSource dataSource) throws Exception {
    SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
    configureSqlSessionFactory(sessionFactoryBean, dataSource);
    return sessionFactoryBean.getObject();
  }
}