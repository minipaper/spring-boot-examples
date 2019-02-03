package net.minipaper.mapper;

import net.minipaper.domain.Hotel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@MybatisTest
public class HotelMapperTest {
  @Autowired
  HotelMapper hotelMapper;

  @Test
  public void selectByCityIdTest() {
    Hotel hotel = hotelMapper.selectByCityId(1);
    assertThat(hotel.getName()).isEqualTo("Conrad Treasury Place");
    assertThat(hotel.getAddress()).isEqualTo("William & George Streets");
    assertThat(hotel.getZip()).isEqualTo("4001");
  }

}