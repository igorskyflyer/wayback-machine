package org.neovisio.appshare;

import android.content.*;
import android.preference.*;

public class Api {
 public static int SORT_BY_NAME = 0x00;
 public static int SORT_BY_DATE = 0x01;
 public static int SORT_BY_SIZE = 0x02;
 public static int SORT_BY_NONE = 0x03;
 
 public static int SORT_ORDER_ASC = 0x04;
 public static int SORT_ORDER_DESC = 0x05;
 
 public static int FILE_SEND_SINGLE = 0x128;
 public static int FILE_SEND_MUTLIPLE = 0x129;
 
 public static class Config {
  private Context self;
  
  private SharedPreferences mConfig;
	 
  public Config(Context context) {
   this.self = context;
   this.mConfig = PreferenceManager.getDefaultSharedPreferences(self);
  }
  
  public SharedPreferences getConfig() {
   return mConfig;
  }
 
  public String getString(String name, String defaultValue) {
   return getConfig().getString(name, defaultValue);
  }
  
  public void setString(String name, String data) {
   getConfig().edit().putString(name, data).commit();
  }
  
  public int getInt(String name, int defaultValue) {
   return getConfig().getInt(name, defaultValue);
  }
  
  public void setInt(String name, int data) {
   getConfig().edit().putInt(name, data).commit();
  }
 }
}
