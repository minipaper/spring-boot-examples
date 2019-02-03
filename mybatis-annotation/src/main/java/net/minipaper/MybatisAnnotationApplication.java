package net.minipaper;

import net.minipaper.mapper.CityMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MybatisAnnotationApplication implements CommandLineRunner {
  public static void main(String[] args) {
    SpringApplication.run(MybatisAnnotationApplication.class);
  }

  private final CityMapper cityMapper;

  public MybatisAnnotationApplication(CityMapper cityMapper) {
    this.cityMapper = cityMapper;
  }

  @Override
  public void run(String... args) {
    System.out.println(this.cityMapper.findByState("CA"));
  }
}
