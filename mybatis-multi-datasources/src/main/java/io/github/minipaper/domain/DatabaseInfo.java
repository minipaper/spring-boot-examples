package io.github.minipaper.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DatabaseInfo {
  private Long seq;
  private String description;
}
