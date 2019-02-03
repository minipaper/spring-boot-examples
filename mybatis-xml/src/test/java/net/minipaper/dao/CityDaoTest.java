package net.minipaper.dao;

import net.minipaper.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@RunWith(SpringRunner.class)
@Import(CityDao.class)
public class CityDaoTest {
  @Autowired
  private CityDao cityDao;

  @Test
  public void selectCityById() {
    City city = cityDao.selectCityById(1);
    assertThat(city.getName()).isEqualTo("San Francisco");
    assertThat(city.getState()).isEqualTo("CA");
    assertThat(city.getCountry()).isEqualTo("US");
  }
}