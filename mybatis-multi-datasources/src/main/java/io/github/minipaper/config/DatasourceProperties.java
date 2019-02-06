package io.github.minipaper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public interface DatasourceProperties {
  String getDriverClassName();

  String getUrl();

  String getUserName();

  String getPassword();

  boolean isInitialize();

  // int getMaxActive(); // Commons DBCP 1.x
  int getMaxTotal();     // Commons DBCP 2.x

  int getMaxIdle();

  int getMinIdle();

  // int getMaxWait();     // Commons DBCP 1.x
  long getMaxWaitMillis(); // Commons DBCP 2.x

}


@Component
@Data
@ConfigurationProperties(prefix = "datasource.data-a")
class ADatasourceProperties implements DatasourceProperties {
  private boolean initialize = false;
  private String driverClassName;
  private String url;
  private String userName;
  private String password;
  private int initialSize;
  private int maxTotal;
  private int maxIdle;
  private int minIdle;
  private long maxWaitMillis;
}

@Component
@Data
@ConfigurationProperties(prefix = "datasource.data-b")
class BDatasourceProperties implements DatasourceProperties {
  private boolean initialize = false;
  private String driverClassName;
  private String url;
  private String userName;
  private String password;
  private int initialSize;
  private int maxTotal;
  private int maxIdle;
  private int minIdle;
  private long maxWaitMillis;
}