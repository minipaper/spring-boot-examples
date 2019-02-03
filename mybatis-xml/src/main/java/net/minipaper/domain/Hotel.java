package net.minipaper.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("hotel")
public class Hotel {
  private Long city;
  private String name;
  private String address;
  private String zip;
}
