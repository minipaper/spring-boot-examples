package io.github.minipaper.util;

import org.springframework.scheduling.support.CronSequenceGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CronUtils {

  private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static boolean check(String cron) {
    boolean isSuccess = true;
    try {
      new CronSequenceGenerator(cron);
    } catch (IllegalArgumentException e) {
      isSuccess = false;
    }
    return isSuccess;
  }

  public static List<String> nextList(String cron, int nextDateCnt) {
    if (!CronUtils.check(cron)) {
      return null;
    }
    List<String> nextExecList = new ArrayList<>();

    CronSequenceGenerator cronSequenceGenerator = new CronSequenceGenerator(cron);
    Date nextExec = new Date();
    for (int i = 0; i < nextDateCnt; i++) {
      nextExec = cronSequenceGenerator.next(nextExec);
      nextExecList.add(DATE_FORMAT.format(nextExec));
    }

    return nextExecList;
  }
}
