package com.timper.res.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.timper.res.utils.context.App;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * User: tangpeng.yang.o(tangpeng.yang.o@nio.com)
 * Date: 2017-06-18 14:01
 * Description: SharedPreferences utils
 * FIXME
 */
public class SpUtil {

  /**
   * save data to the file
   */
  public static final String FILE_NAME = "slash_sp.config";

  public static void write(String k, int v) {
    write(FILE_NAME, k, v);
  }

  public static void write(String k, boolean v) {
    write(FILE_NAME, k, v);
  }

  public static void write(String k, long v) {
    write(FILE_NAME, k, v);
  }

  public static void write(String k, String v) {
    write(FILE_NAME, k, v);
  }

  public static void write(String k, Serializable v) {
    saveObject(v, k);
  }

  public static int readInt(String k, int defv) {
    return readInt(FILE_NAME, k, defv);
  }

  public static long readLong(String k, long defv) {
    return readLong(FILE_NAME, k, defv);
  }

  public static boolean readBoolean(String k, boolean defBool) {
    return readBoolean(FILE_NAME, k, defBool);
  }

  public static String readString(String k, String defV) {
    return readString(FILE_NAME, k, defV);
  }

  public static void remove(Context context, String k) {
    remove(FILE_NAME, k);
  }

  public static void clean() {
    clean(FILE_NAME);
  }

  public static void remove(String... k) {
    SharedPreferences preference = App.context().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preference.edit();
    for (String key : k) {
      editor.remove(key);
    }
    editor.commit();
  }

  public static void write(String fileName, String k, int v) {
    SharedPreferences preference = App.context().getSharedPreferences(fileName, App.context().MODE_PRIVATE);
    SharedPreferences.Editor editor = preference.edit();
    editor.putInt(k, v);
    editor.commit();
  }

  public static void write(String fileName, String k, boolean v) {
    SharedPreferences preference = App.context().getSharedPreferences(fileName, App.context().MODE_PRIVATE);
    SharedPreferences.Editor editor = preference.edit();
    editor.putBoolean(k, v);
    editor.commit();
  }

  public static void write(String fileName, String k, long v) {
    SharedPreferences preference = App.context().getSharedPreferences(fileName, App.context().MODE_PRIVATE);
    SharedPreferences.Editor editor = preference.edit();
    editor.putLong(k, v);
    editor.commit();
  }

  public static void write(String fileName, String k, String v) {
    SharedPreferences preference = App.context().getSharedPreferences(fileName, App.context().MODE_PRIVATE);
    SharedPreferences.Editor editor = preference.edit();
    editor.putString(k, v);
    editor.commit();
  }

  public static int readInt(String fileName, String k, int defv) {
    SharedPreferences preference = App.context().getSharedPreferences(fileName, App.context().MODE_PRIVATE);
    return preference.getInt(k, defv);
  }

  public static long readLong(String fileName, String k, long defv) {
    SharedPreferences preference = App.context().getSharedPreferences(fileName, App.context().MODE_PRIVATE);
    return preference.getLong(k, defv);
  }

  public static boolean readBoolean(String fileName, String k, boolean defBool) {
    SharedPreferences preference = App.context().getSharedPreferences(fileName, App.context().MODE_PRIVATE);
    return preference.getBoolean(k, defBool);
  }

  public static String readString(String fileName, String k, String defV) {
    SharedPreferences preference = App.context().getSharedPreferences(fileName, App.context().MODE_PRIVATE);
    return preference.getString(k, defV);
  }

  public static void remove(String fileName, String k) {
    SharedPreferences preference = App.context().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preference.edit();
    editor.remove(k);
    editor.commit();
  }

  public static void clean(String fileName) {
    SharedPreferences preference = App.context().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preference.edit();
    editor.clear();
    editor.commit();
  }

  /**
   * 保存对象
   */
  public static boolean saveObject(Serializable ser, String key) {
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    try {
      fos = App.context().openFileOutput(key, App.context().MODE_PRIVATE);
      oos = new ObjectOutputStream(fos);
      oos.writeObject(ser);
      oos.flush();
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } finally {
      try {
        oos.close();
      } catch (Exception e) {
      }
      try {
        fos.close();
      } catch (Exception e) {
      }
    }
  }

  public static void deleteObject(String key) {
    String filePath = App.context().getFilesDir().getPath() + "/" + key;
    File file = new File(filePath);
    if (file.exists()) {
      file.delete();
    }
  }

  /**
   * 读取对象
   */
  public static Serializable readObject(String key) {
    if (!isExistDataCache(key)) return null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    try {
      fis = App.context().openFileInput(key);
      ois = new ObjectInputStream(fis);
      return (Serializable) ois.readObject();
    } catch (FileNotFoundException e) {
    } catch (Exception e) {
      e.printStackTrace();
      // 反序列化失败 - 删除缓存文件
      if (e instanceof InvalidClassException) {
        File data = App.context().getFileStreamPath(key);
        data.delete();
      }
    } finally {
      try {
        ois.close();
      } catch (Exception e) {
      }
      try {
        fis.close();
      } catch (Exception e) {
      }
    }
    return null;
  }

  /**
   * 判断缓存是否存在
   */
  public static boolean isExistDataCache(String key) {
    if (App.context() == null) return false;
    boolean exist = false;
    File data = App.context().getFileStreamPath(key);
    if (data.exists()) exist = true;
    return exist;
  }
}
