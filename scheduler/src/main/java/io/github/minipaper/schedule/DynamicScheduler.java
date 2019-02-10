package io.github.minipaper.schedule;

import io.github.minipaper.util.DynamicAbstractScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component("DynamicScheduler")
public class DynamicScheduler extends DynamicAbstractScheduler {

  private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  public Trigger getTrigger() {
    // default 10 seconds
    String cron = "0/10 * * * * ?";
    return new CronTrigger(cron);
  }

  @Override
  public void exec() {
    // Execution code
    log.info("DynamicScheduler exec {}", DATE_FORMAT.format(new Date()));
  }
}
