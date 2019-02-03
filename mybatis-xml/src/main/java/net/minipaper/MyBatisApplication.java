package net.minipaper;

import lombok.extern.slf4j.Slf4j;
import net.minipaper.dao.CityDao;
import net.minipaper.mapper.HotelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MyBatisApplication implements CommandLineRunner {


  public static void main(String[] args) {
    SpringApplication.run(MyBatisApplication.class);
  }

  private final CityDao cityDao;

  private final HotelMapper hotelMapper;

  public MyBatisApplication(CityDao cityDao, HotelMapper hotelMapper) {
    this.cityDao = cityDao;
    this.hotelMapper = hotelMapper;
  }

  @Override
  public void run(String... args) {
    log.info("MyBatisApplication Runs");
    System.out.println(this.cityDao.selectCityById(1));
    System.out.println(this.hotelMapper.selectByCityId(1));
  }
}
