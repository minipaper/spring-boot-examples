package net.minipaper.mapper;

import net.minipaper.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CityMapper {
  @Select("select * from city where state = #{state}")
  City findByState(@Param("state") String state);
}
