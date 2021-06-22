package org.neovisio.appshare;

import android.net.Uri;
import java.io.*;
import android.os.*;

public class App implements Parcelable {
 public String name;
 public String packageName;
 public String apk;
 public long installDate;
 public String version;
 public long size;

 public App(String name, String packageName, String apk, long installDate, String version, long size) {
  this.name = name;
  this.packageName = packageName;
  this.apk = apk;
  this.installDate = installDate;
  this.version = version;
  this.size = size;
 }

 @Override
 public int describeContents(){
  return 0;
 }

 @Override
 public void writeToParcel(Parcel dest, int p2) {
  dest.writeString(name);
  dest.writeString(packageName);
  dest.writeString(apk);
  dest.writeLong(installDate);
  dest.writeString(version);
  dest.writeLong(size);
 }

 public App(Parcel obj) {
  name = obj.readString();
  packageName = obj.readString();
  apk = obj.readString();
  installDate = obj.readLong();
  version = obj.readString();
  size = obj.readLong();
 }
 
 public static final Parcelable.Creator<App> CREATOR = new Parcelable.Creator<App>() {
  public App createFromParcel(Parcel obj) {
   return new App(obj);
  }

  public App[] newArray(int size) {
   return new App[size];
  }
 }; 
}
