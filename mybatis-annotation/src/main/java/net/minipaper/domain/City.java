package net.minipaper.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class City {
  private Long id;
  private String name;
  private String state;
  private String country;
}
