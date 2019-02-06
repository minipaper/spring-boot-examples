package io.github.minipaper;

import io.github.minipaper.domain.DatabaseInfo;
import io.github.minipaper.repository.AMapper;
import io.github.minipaper.repository.BMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;


@SpringBootApplication(
    exclude = {DataSourceTransactionManagerAutoConfiguration.class, DataSourceAutoConfiguration.class}
)
public class MyBatisMultiDatasourcesApplication implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(MyBatisMultiDatasourcesApplication.class);
  }

  @Autowired
  private AMapper aMapper;

  @Autowired
  private BMapper bMapper;

  @Override
  public void run(String... args) {
    System.out.println("Mybatis Multi Datasources");

    DatabaseInfo databaseInfo = aMapper.findOne(1L);
    System.out.println(databaseInfo.getDescription());

    databaseInfo = bMapper.findOne(1L);
    System.out.println(databaseInfo.getDescription());
  }
}
