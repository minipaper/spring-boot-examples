package io.github.minipaper;

import io.github.minipaper.schedule.DynamicScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SchedulerApplication implements CommandLineRunner {

  @Autowired
  DynamicScheduler dynamicScheduler;

  public static void main(String[] args) {
    SpringApplication.run(SchedulerApplication.class);
  }

  @Override
  public void run(String... args) {
    dynamicScheduler.startScheduler();
  }
}
