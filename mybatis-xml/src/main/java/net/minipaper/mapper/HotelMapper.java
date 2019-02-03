package net.minipaper.mapper;

import net.minipaper.domain.Hotel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface HotelMapper {
  Hotel selectByCityId(int cityId);
}
