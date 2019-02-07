package io.github.minipaper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 모듈로 WAR 패키지를 만들었을 경우 bootRun 으로 실행하지 않으면
 * WEB-INF 포함이 되지 않아서 gradlew bootRun 으로 실행
 *
 * ./gradlew bootRun -Dspring.profiles.active=local
 *
 * 빌드후 java -jar -Dspring.profiles.active=prod file.war
  */
@SpringBootApplication
public class WebJspApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(WebJspApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(WebJspApplication.class);
  }
}
