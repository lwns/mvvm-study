package com.timper.res.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * Created by acer on 2017/3/31.
 */

public class DoubleUtil {

  /**
   * 修改精度
   */
  public static double changeDecimal(double value, int num) {
    BigDecimal b = new BigDecimal(value);
    double v;
    if (isIntegerForDouble(value)) {
      v = b.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
    } else {
      v = value;
    }
    return v;
  }

  public static String changeDecimal(double value) {
    if (Double.valueOf(value).isNaN()) {
      return "0";
    }
    DecimalFormat df = new DecimalFormat("###,###,###,###.##");
    return df.format(value);
  }

  public static String doubleDecimal(double value) {
    if (Double.valueOf(value).isNaN()) {
      return "0";
    }
    DecimalFormat df = new DecimalFormat("############.##");
    return df.format(value);
  }

  public static String changeDecimalToStrFormat(double value) {
    if (Double.valueOf(value).isNaN()) {
      return "0";
    }
    BigDecimal b = new BigDecimal(value);
    int v = b.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();

    NumberFormat numberFormat = NumberFormat.getNumberInstance();
    return numberFormat.format(v);
  }

  /**
   * 两个double相加方法
   */
  public static Double doubleAdd(Double a, Double b) {
    BigDecimal b1 = new BigDecimal(Double.toString(a));
    BigDecimal b2 = new BigDecimal(Double.toString(b));
    return b1.add(b2).doubleValue();
  }

  /**
   * 两个double相加方法,并保留指定精度
   */
  public static Double doubleAdd(Double a, Double b, int num) {
    return changeDecimal(doubleAdd(a, b), num);
  }

  /**
   * 两个double相减方法
   */
  public static Double doubleSub(Double a, Double b) {
    BigDecimal b1 = new BigDecimal(Double.toString(a));
    BigDecimal b2 = new BigDecimal(Double.toString(b));
    return b1.subtract(b2).doubleValue();
  }

  /**
   * 两个double相减方法,并保留指定精度
   */
  public static Double doubleSub(Double a, Double b, int num) {
    return changeDecimal(doubleSub(a, b), num);
  }

  /**
   * 两个double相乘方法
   */
  public static Double doubleMul(Double a, Double b) {
    BigDecimal b1 = new BigDecimal(Double.toString(a));
    BigDecimal b2 = new BigDecimal(Double.toString(b));
    return b1.multiply(b2).doubleValue();
  }

  /**
   * 两个double相乘方法,并保留指定精度
   */
  public static Double doubleMul(Double a, Double b, int num) {
    return changeDecimal(doubleMul(a, b), num);
  }

  /**
   * 两个double相除方法,并保留指定精度
   */
  public static Double doubleDiv(Double a, Double b, int scale) {
    BigDecimal b1 = new BigDecimal(Double.toString(a));
    BigDecimal b2 = new BigDecimal(Double.toString(b));
    return Double.valueOf(b1.divide(b2, scale, 4).doubleValue());
  }


  /**
   * 将double转换成千分位数字
   */
  public static String doubleToMoney2(double a, int num) {
    if (Double.valueOf(a).isNaN()) {
      return "0";
    }
    NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
    //        "\u00A5"
    return "\u00A5" + changeDecimal(a);
  }

  /**
   * 将千分位数字转换成double
   */
  public static double moneyToDouble(String a) {
    double d1 = 0;
    try {
      d1 = new DecimalFormat().parse(a).doubleValue();
      return d1;
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return d1;
  }

  /**
   * 判断double是否为整数
   */
  public static boolean isIntegerForDouble(double obj) {
    double eps = 1e-10;  // 精度范围
    return obj - Math.floor(obj) < eps;
  }
}
