package io.github.minipaper.util;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

public abstract class DynamicAbstractScheduler {

  private ThreadPoolTaskScheduler scheduler;

  public void startScheduler() {
    stopScheduler();
    scheduler = new ThreadPoolTaskScheduler();
    scheduler.initialize();
    scheduler.schedule(this::exec, getTrigger());
  }

  public void changeTrigger(Trigger trigger) {
    stopScheduler();
    scheduler = new ThreadPoolTaskScheduler();
    scheduler.initialize();
    scheduler.schedule(this::exec, trigger);
  }

  public void stopScheduler() {
    if (null != scheduler) {
      scheduler.shutdown();
    }
  }

  public abstract Trigger getTrigger();

  public abstract void exec();

}
