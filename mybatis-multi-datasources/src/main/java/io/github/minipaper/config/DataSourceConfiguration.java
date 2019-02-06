package io.github.minipaper.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


public abstract class DataSourceConfiguration {
  public abstract DataSource dataSource();

  abstract void initialize(DataSource dataSource);

  protected BasicDataSource configureDataSource(DatasourceProperties datasourceProperties) {

    // 실제 서비스에서는 Spring 에서 제공하는 걸 사용하지 말라고 권고 (Use apache.commons.dbcp2.BasicDataSource)
    // Pooling을 하지 않는다고 함. https://blog.outsider.ne.kr/882
    // @see <a href="https://d2.naver.com/helloworld/5102792">Commons DBCP 이해하기</a>

    BasicDataSource dataSource = new BasicDataSource();

    dataSource.setDriverClassName(datasourceProperties.getDriverClassName());
    dataSource.setUrl(datasourceProperties.getUrl());
    dataSource.setUsername(datasourceProperties.getUserName());
    dataSource.setPassword(datasourceProperties.getPassword());
    dataSource.setMaxTotal(datasourceProperties.getMaxTotal());
    dataSource.setMaxIdle(datasourceProperties.getMaxIdle());
    dataSource.setMinIdle(datasourceProperties.getMinIdle());
    dataSource.setMaxWaitMillis(datasourceProperties.getMaxWaitMillis());
    dataSource.setTestOnBorrow(false);
    dataSource.setTestOnReturn(false);

    if (datasourceProperties.isInitialize())
      initialize(dataSource);

    return dataSource;
  }
}

@Configuration
@EnableTransactionManagement
class ADataSourceConfiguration extends DataSourceConfiguration {
  @Autowired
  private ADatasourceProperties datasourceProperties;

  @Override
  @Bean(name = "dataSourceA")
  @Primary
  public DataSource dataSource() {
    return configureDataSource(datasourceProperties);
  }

  @Bean(name = "transactionManagerA")
  public PlatformTransactionManager transactionManager(@Qualifier("dataSourceA") DataSource dataSource) {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
    transactionManager.setGlobalRollbackOnParticipationFailure(false);
    return transactionManager;
  }

  @Override
  void initialize(DataSource dataSource) {
    PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
    // 초기 schema, data 추가
    Resource schema = pathResolver.getResource("classpath:scripts/data-a-schema.sql");
    Resource data = pathResolver.getResource("classpath:scripts/data-a-init.sql");
    DatabasePopulator databasePopulator = new ResourceDatabasePopulator(schema, data);
    DatabasePopulatorUtils.execute(databasePopulator, dataSource);
  }
}


@Configuration
@EnableTransactionManagement
class BDataSourceConfiguration extends DataSourceConfiguration {
  @Autowired
  private BDatasourceProperties datasourceProperties;

  @Override
  @Bean(name = "dataSourceB")
  @Primary
  public DataSource dataSource() {
    return configureDataSource(datasourceProperties);
  }

  @Bean(name = "transactionManagerB")
  public PlatformTransactionManager transactionManager(@Qualifier("dataSourceB") DataSource dataSource) {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
    transactionManager.setGlobalRollbackOnParticipationFailure(false);
    return transactionManager;
  }

  @Override
  void initialize(DataSource dataSource) {
    PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
    // 초기 schema, data 추가
    Resource schema = pathResolver.getResource("classpath:scripts/data-b-schema.sql");
    Resource data = pathResolver.getResource("classpath:scripts/data-b-init.sql");
    DatabasePopulator databasePopulator = new ResourceDatabasePopulator(schema, data);
    DatabasePopulatorUtils.execute(databasePopulator, dataSource);
  }
}