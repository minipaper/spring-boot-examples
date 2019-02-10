package io.github.minipaper.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class SimpleScheduler {

  private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  // Run every second
  @Scheduled(cron = "* * * * * ?")
  public void everySecond() {
    log.info("everySecond {}", DATE_FORMAT.format(new Date()));
  }

  // Run every 30 seconds. Count from the start of the program.
  @Scheduled(fixedRate = 30 * 1000)
  public void every30Seconds() {
    log.info("every30Seconds {}", DATE_FORMAT.format(new Date()));
  }

  // Run every 1 minute
  @Scheduled(cron = "0 * * * * ?")
  public void everyOneMinute() {
    log.info("everyOneMinute {}", DATE_FORMAT.format(new Date()));
  }

}
