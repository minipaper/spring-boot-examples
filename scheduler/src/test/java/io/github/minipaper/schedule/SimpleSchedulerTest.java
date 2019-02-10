package io.github.minipaper.schedule;

import org.awaitility.Duration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleSchedulerTest {

  @SpyBean
  private SimpleScheduler simpleScheduler;

  @Test
  public void everySecond() {
    await()
        .atMost(3, TimeUnit.SECONDS)
        .untilAsserted(() -> verify(simpleScheduler, times(3)).everySecond());
  }

//  @Test
  public void every30Seconds() {
    await()
        .atMost(Duration.ONE_MINUTE)
        .untilAsserted(() -> verify(simpleScheduler, times(2)).every30Seconds());
  }

//  @Test
  public void everyOneMinute() {
    await()
        .atMost(Duration.ONE_MINUTE)
        .untilAsserted(() -> verify(simpleScheduler, atLeast(1)).everyOneMinute());
  }
}