package com.timper.res.utils;

import java.security.MessageDigest;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author tangpeng.yang@nio.com
 * @date 19/10/2017
 */
public class StrUtil {

  private final static Pattern emailer = Pattern
      .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
  private final static Pattern phone = Pattern
      .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
  private final static Pattern IMG_URL = Pattern
      .compile(".*?(gif|jpeg|png|jpg|bmp)");

  private final static Pattern URL = Pattern
      .compile("^(https|http)://.*?$(net|com|.com.cn|org|me|)");


  public static String trimAllWhitespace(String str) {
    return str.replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "").toString();
  }

  public static boolean isEmpty(CharSequence str) {
    if (str == null || "".equals(str))
      return true;

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
        return false;
      }
    }
    return true;
  }

  /**
   * @param s
   * @return
   * @description MD5加密
   * @author Tp_yang
   * @createDate 2015-1-22
   */
  public static String MD5(String s) {
    try {
      byte[] btInput = s.getBytes();
      MessageDigest mdInst = MessageDigest.getInstance("MD5");
      mdInst.update(btInput);
      byte[] md = mdInst.digest();
      String str = byteArrayToHexString(md);
      return str;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }


  /**
   * 判断是不是一个合法的电子邮件地址
   */
  public static boolean isEmail(CharSequence email) {
    if (isEmpty(email))
      return false;
    return emailer.matcher(email).matches();
  }

  /**
   * 判断是不是一个合法的手机号码
   */
  public static boolean isPhone(CharSequence phoneNum) {
    if (isEmpty(phoneNum))
      return false;
    return phone.matcher(phoneNum).matches();
  }

  /**
   * 判断是否为一个合法的url地址
   *
   * @param str
   * @return
   */
  public static boolean isUrl(String str) {
    if (str == null || str.trim().length() == 0)
      return false;
    return URL.matcher(str).matches();
  }

  /**
   * 判断一个url是否为图片url
   *
   * @param url
   * @return
   */
  public static boolean isImgUrl(String url) {
    if (url == null || url.trim().length() == 0)
      return false;
    return IMG_URL.matcher(url).matches();
  }

  /**
   * 字符串转整数
   *
   * @param str
   * @param defValue
   * @return
   */
  public static int toInt(String str, int defValue) {
    try {
      return Integer.parseInt(str);
    } catch (Exception e) {
    }
    return defValue;
  }

  /**
   * 对象转整
   *
   * @param obj
   * @return 转换异常返回 0
   */
  public static int toInt(Object obj) {
    if (obj == null)
      return 0;
    return toInt(obj.toString(), 0);
  }

  /**
   * String转long
   *
   * @param obj
   * @return 转换异常返回 0
   */
  public static long toLong(String obj) {
    try {
      return Long.parseLong(obj);
    } catch (Exception e) {
    }
    return 0;
  }

  /**
   * String转double
   *
   * @param obj
   * @return 转换异常返回 0
   */
  public static double toDouble(String obj) {
    try {
      return Double.parseDouble(obj);
    } catch (Exception e) {
    }
    return 0D;
  }

  /**
   * 字符串转布尔
   *
   * @param b
   * @return 转换异常返回 false
   */
  public static boolean toBool(String b) {
    try {
      return Boolean.parseBoolean(b);
    } catch (Exception e) {
    }
    return false;
  }

  /**
   * 判断一个字符串是不是数字
   */
  public static boolean isNumber(CharSequence str) {
    try {
      Integer.parseInt(str.toString());
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * byte[]数组转换为16进制的字符串。
   *
   * @param data 要转换的字节数组。
   * @return 转换后的结果。
   */
  public static final String byteArrayToHexString(byte[] data) {
    StringBuilder sb = new StringBuilder(data.length * 2);
    for (byte b : data) {
      int v = b & 0xff;
      if (v < 16) {
        sb.append('0');
      }
      sb.append(Integer.toHexString(v));
    }
    return sb.toString().toUpperCase(Locale.getDefault());
  }

  /**
   * 16进制表示的字符串转换为字节数组。
   *
   * @param s 16进制表示的字符串
   * @return byte[] 字节数组
   */
  public static byte[] hexStringToByteArray(String s) {
    int len = s.length();
    byte[] d = new byte[len / 2];
    for (int i = 0; i < len; i += 2) {
      // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
      d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
          .digit(s.charAt(i + 1), 16));
    }
    return d;
  }

  /**
   * format count
   *
   * @param count count
   * @return formatCount
   */
  public static String formatCount(long count) {

    if (count > 1000) {
      int a = (int) (count / 100);
      int b = a % 10;
      int c = a / 10;
      String str;
      if (c <= 9 && b != 0)
        str = c + "." + b;
      else
        str = String.valueOf(c);

      return str + "k";
    } else {
      return String.valueOf(count);
    }

  }

}
