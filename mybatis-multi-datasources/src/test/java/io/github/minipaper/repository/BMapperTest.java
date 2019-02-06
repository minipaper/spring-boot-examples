package io.github.minipaper.repository;

import io.github.minipaper.domain.DatabaseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BMapperTest {
  @Autowired
  private BMapper bMapper;

  @Test
  public void findOne() {
    DatabaseInfo databaseInfo = bMapper.findOne(1L);
    System.out.println(databaseInfo.toString());
    assertEquals("This is the B database", databaseInfo.getDescription());
  }

}