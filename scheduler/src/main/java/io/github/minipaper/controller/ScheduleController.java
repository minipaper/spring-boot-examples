package io.github.minipaper.controller;

import io.github.minipaper.util.ContextProvider;
import io.github.minipaper.util.DynamicAbstractScheduler;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScheduleController {

  @PostMapping("/start")
  public ResponseEntity<String> startScheduler() {
    DynamicAbstractScheduler scheduler = (DynamicAbstractScheduler) ContextProvider.getBean("DynamicScheduler");
    scheduler.startScheduler();

    return ResponseEntity.ok("It started");
  }

  @PostMapping("/stop")
  public ResponseEntity<String> stopScheduler() {
    DynamicAbstractScheduler scheduler = (DynamicAbstractScheduler) ContextProvider.getBean("DynamicScheduler");
    scheduler.stopScheduler();

    return ResponseEntity.ok("It stopped");
  }

  @PostMapping("/change")
  public ResponseEntity<String> changeScheduler(@RequestParam(required = false, defaultValue = "0/5 * * * * ?") String cron) {
    DynamicAbstractScheduler scheduler = (DynamicAbstractScheduler) ContextProvider.getBean("DynamicScheduler");
    scheduler.changeTrigger(new CronTrigger(cron));

    return ResponseEntity.ok("It changed");
  }
}
