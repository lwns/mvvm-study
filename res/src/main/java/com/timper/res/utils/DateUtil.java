package com.timper.res.utils;

import android.app.AlarmManager;
import android.text.TextUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.timper.res.utils.StrUtil.toInt;

/**
 * @author Tp_yang
 * @version 1.0
 * @description 日期工具类
 * @createDate 2015-1-22
 */
public class DateUtil {

  private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

  private final static Pattern IMG_URL = Pattern.compile(".*?(gif|jpeg|png|jpg|bmp)");

  private final static Pattern URL = Pattern.compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");

  private final static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMMSS = new ThreadLocal<SimpleDateFormat>() {
    @Override protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    }
  };

  private final static ThreadLocal<SimpleDateFormat> YYYYMMDD = new ThreadLocal<SimpleDateFormat>() {
    @Override protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    }
  };

  private final static ThreadLocal<SimpleDateFormat> YYYYMMDDHHMM = new ThreadLocal<SimpleDateFormat>() {
    @Override protected SimpleDateFormat initialValue() {
      return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    }
  };

  private static Calendar calendar = Calendar.getInstance();
  private static SimpleDateFormat formatYearCn = new SimpleDateFormat("yyyy年MM月dd日", Locale.getDefault());
  public static final long SECOND = 1000L;
  public static final long MINUTE = 60000L;
  public static final long HOUR = 3600000L;
  public static final long DAY = 86400000L;
  public static final long LOCAL_TIME = System.currentTimeMillis();

  private static final Pattern UniversalDatePattern =
      Pattern.compile("([0-9]{4})-([0-9]{2})-([0-9]{2})[\\s]+([0-9]{2}):([0-9]{2}):([0-9]{2})");

  public static String getFormatTime(String time) {
    long mTime = Long.parseLong(time);
    Calendar.getInstance().setTimeInMillis(mTime);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;
    int date = calendar.get(Calendar.DAY_OF_MONTH);
    return year + "年" + month + "月" + date + "日";
  }

  public static String getFormatTime1(String time) {
    long mTime = Long.parseLong(time);
    Date currentTime = new Date(mTime);
    String dateString = formatYearCn.format(currentTime);
    return dateString;
  }

  public static String getCurrentTime() {
    Date currentTime = new Date(Calendar.getInstance().getTimeInMillis());
    String dateString = formatYearCn.format(currentTime);
    return dateString;
  }

  public static String getCurrentTime(String format) {
    Date currentTime = new Date(Calendar.getInstance().getTimeInMillis());
    String dateString = new SimpleDateFormat(format).format(currentTime);
    return dateString;
  }

  public static String getYesterdayTime() {
    Date currentTime = new Date(Calendar.getInstance().getTimeInMillis() - 86400000L);
    String dateString = formatYearCn.format(currentTime);
    return dateString;
  }

  public static String getTemorrowTime() {
    Date currentTime = new Date(Calendar.getInstance().getTimeInMillis() + 86400000L);
    String dateString = formatYearCn.format(currentTime);
    return dateString;
  }

  /**
   * transform date to string that's type like YYYY-MM-DD HH:mm
   */
  public static String getDateString(String sdate) {
    if (TextUtils.isEmpty(sdate)) return "";
    return YYYYMMDDHHMM.get().format(toDate(sdate));
  }

  /**
   * format time friendly
   *
   * @param sdate YYYY-MM-DD HH:mm:ss
   * @return n分钟前, n小时前, 昨天, 前天, n天前, n个月前
   */
  public static String formatSomeAgo(String sdate) {
    if (sdate == null) return "";
    Calendar calendar = parseCalendar(sdate);
    if (calendar == null) return sdate;

    Calendar mCurrentDate = Calendar.getInstance();
    long crim = mCurrentDate.getTimeInMillis(); // current
    long trim = calendar.getTimeInMillis(); // target
    long diff = crim - trim;

    int year = mCurrentDate.get(Calendar.YEAR);
    int month = mCurrentDate.get(Calendar.MONTH);
    int day = mCurrentDate.get(Calendar.DATE);

    if (diff < 60 * 1000) {
      return "刚刚";
    }
    if (diff >= 60 * 1000 && diff < AlarmManager.INTERVAL_HOUR) {
      return String.format("%s分钟前", diff / 60 / 1000);
    }
    mCurrentDate.set(year, month, day, 0, 0, 0);
    if (trim >= mCurrentDate.getTimeInMillis()) {
      return String.format("%s小时前", diff / AlarmManager.INTERVAL_HOUR);
    }
    mCurrentDate.set(year, month, day - 1, 0, 0, 0);
    if (trim >= mCurrentDate.getTimeInMillis()) {
      return "昨天";
    }
    mCurrentDate.set(year, month, day - 2, 0, 0, 0);
    if (trim >= mCurrentDate.getTimeInMillis()) {
      return "前天";
    }
    if (diff < AlarmManager.INTERVAL_DAY * 30) {
      return String.format("%s天前", diff / AlarmManager.INTERVAL_DAY);
    }
    if (diff < AlarmManager.INTERVAL_DAY * 30 * 12) {
      return String.format("%s月前", diff / (AlarmManager.INTERVAL_DAY * 30));
    }
    return String.format("%s年前", mCurrentDate.get(Calendar.YEAR) - calendar.get(Calendar.YEAR));
  }

  /**
   * YYYY-MM-DD HH:mm:ss格式的时间字符串转换为{@link Calendar}类型
   *
   * @param str YYYY-MM-DD HH:mm:ss格式字符串
   * @return {@link Calendar}
   */
  public static Calendar parseCalendar(String str) {
    Matcher matcher = UniversalDatePattern.matcher(str);
    Calendar calendar = Calendar.getInstance();
    if (!matcher.find()) return null;
    calendar.set(matcher.group(1) == null ? 0 : toInt(matcher.group(1)),
        matcher.group(2) == null ? 0 : toInt(matcher.group(2)) - 1, matcher.group(3) == null ? 0 : toInt(matcher.group(3)),
        matcher.group(4) == null ? 0 : toInt(matcher.group(4)), matcher.group(5) == null ? 0 : toInt(matcher.group(5)),
        matcher.group(6) == null ? 0 : toInt(matcher.group(6)));
    return calendar;
  }

  /**
   * 是否是相同的一天
   *
   * @param sdate1 sdate1
   * @param sdate2 sdate2
   */
  public static boolean isSameDay(String sdate1, String sdate2) {
    if (TextUtils.isEmpty(sdate1) || TextUtils.isEmpty(sdate2)) {
      return false;
    }
    Date date1 = toDate(sdate1);
    Date date2 = toDate(sdate2);
    if (date1 != null && date2 != null) {
      String d1 = YYYYMMDD.get().format(date1);
      String d2 = YYYYMMDD.get().format(date2);
      if (d1.equals(d2)) {
        return true;
      }
    }
    return false;
  }

  /**
   * 将字符串转位日期类型
   *
   * @param sdate string date that's type like YYYY-MM-DD HH:mm:ss
   * @return {@link Date}
   */
  public static Date toDate(String sdate) {
    return toDate(sdate, YYYYMMDDHHMMSS.get());
  }

  public static Date toDate(String sdate, SimpleDateFormat formatter) {
    try {
      return formatter.parse(sdate);
    } catch (Exception e) {
      return null;
    }
  }
}
