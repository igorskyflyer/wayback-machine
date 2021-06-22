package org.neovisio.appshare;

import java.util.*;
import java.io.*;
import android.util.*;
import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import java.util.regex.*;

//@NotThreadSafe
public class Cacher {/*
  private abstract class OnCacheStartListener {
  public void OnCacheStart() {}
 }

 private abstract class OnCacheEndListener {
  public void OnCacheEnd() {}
 }

 private abstract class OnCacheMismatchListener {
  public void OnCacheMismatch(String pkg) {}
 }

 private abstract class OnCacheReadListener {
  public void OnCacheRead() {}
 }
	
 private abstract class OnCacheWriteListener {
  public void OnCacheWrite() {}
 }
 
 private abstract class OnCachingListener {
  public void OnCaching(String pkg) {}
 }
 
 private abstract class OnCacheChangeListener {
  public void OnCacheChange() {}
 }
 
 private abstract class OnCacherErrorListener {
  public void OnCacherError(String message) {}
 }
 
 private class Enigma {
  public String encrypt() {
   byte[] bytes = getCache().getBytes();
   int size = getCache().length();
   
   return Base64.encodeToString(bytes, 0, size, Base64.NO_WRAP);
  }
  
  public String decrypt() {
   byte[] bytes = getCache().getBytes();
   
   return new String(Base64.decode(bytes, Base64.NO_WRAP)); 
  }
 }
 
 // Properties
 private Context self;

 private File mCacheDir;
 private File mCacheFile;
 private String mCacheDelimiter;
 private boolean mSpeedUp;
 private boolean mSecureData;
 private File mIcon;
 
 // (De)Encrypter
 
 private Enigma security;
 private StringBuilder builder;
 
 // Internal, exposed properties
 
 private boolean pIsSecure;
 private String pLastError;
 
 // Events
 
 private OnCacheStartListener mOnCacheStart;
 private OnCacheMismatchListener mOnCacheMismatch;
 private OnCacheEndListener mOnCacheEnd;
 private OnCacheWriteListener mOnCacheWrite;
 private OnCacheReadListener mOnCacheRead;
 private OnCachingListener mOnCaching;
 private OnCacheChangeListener mOnCacheChange;
 private OnCacherErrorListener mOnCacherError;
 
 // Parameterized constructor
 
 public Cacher(Context context) {
  this.self = context;
  this.mCacheFile = null;
  this.mCacheDir = null;
  this.mCacheDelimiter = "";
  this.mSpeedUp = false;
  this.mSecureData = false;
  this.mIcon = null;
  
  this.mOnCacheStart = null;
  this.mOnCacheMismatch = null;
  this.mOnCacheEnd = null;
  this.mOnCacheRead = null;
  this.mOnCacheWrite = null;
  this.mOnCaching = null;
  this.mOnCacheChange = null;
  this.mOnCacherError = null;
  
  this.builder = new StringBuilder();
  this.security = new Enigma();
  
  this.pIsSecure = false;
  this.pLastError = "";
 }
	
 // Getters and setters
 
 public void setCacheDir(String dir) {
  mCacheDir = new File(dir);
 }
 
 public File getCacheDir() {
  return mCacheDir;
 }
 
 public void setCacheFile(String cacheFile) {
  mCacheFile = new File(cacheFile);
 }
 
 public File getCacheFile() {
  return mCacheFile;
 }
 
 public void setCacheDelimiter(String delimiter) {
  mCacheDelimiter = Pattern.quote(delimiter);
 }
 
 public String getCacheDelimiter() {
  return mCacheDelimiter;
 }
 
 public void setSpeedUp(boolean value) {
  mSpeedUp = value;
 }
 
 public String getCache() {
  return builder.toString();
 }
 
 public void setSecureData(boolean value) {
  mSecureData = value;
 }
 
 public File getIcon() {
  return mIcon;
 }
 
 // Setters for event handlers
 
 public void setOnCacheStart(OnCacheStartListener onCacheStart) {
  mOnCacheStart = onCacheStart;
 }
 
 public void setOnCacheEnd(OnCacheEndListener onCacheEnd) {
  mOnCacheEnd = onCacheEnd;
 }
 
 public void setOnCacheMismatch(OnCacheMismatchListener onCacheMismatch) {
  mOnCacheMismatch = onCacheMismatch;
 }
	
 public void setOnCacheRead(OnCacheReadListener onCacheRead) {
  mOnCacheRead = onCacheRead;
 }
	
 public void setOnCacheWrite(OnCacheWriteListener onCacheWrite) {
  mOnCacheWrite = onCacheWrite;
 }
 
 public void setOnCaching(OnCachingListener onCaching) {
  mOnCaching = onCaching;
 }
 
 public void setOnCacheChange(OnCacheChangeListener onCacheChange) {
  mOnCacheChange = onCacheChange;
 }
 
 public void setOnCacherError(OnCacherErrorListener onCacherError) {
  mOnCacherError = onCacherError;
 }
 
 // Public methods
 
 public boolean isSecure() {
  return pIsSecure;
 }
 
 public String getLastError() {
  return pLastError;
 }
 
 public boolean hasCache() {
  return (mCacheFile != null && mCacheFile.exists());
 }
 
 public void encrypt() {
  pIsSecure = true;
  builder = new StringBuilder(security.encrypt()); 
 }
 
 public void decrypt() {
  pIsSecure = false;
  builder = new StringBuilder(security.decrypt());
 }
 
 public void add(String pkg) {
  
 }
 
 public void addNonLaunchable(String pkg) {
	 
 }
 
 public void addDisabled(String pkg) {
	 
 }
 
 public void remove(String pkg) {
	 
 }
 
 public void remove(int id) {
	 
 }
 
 public void rebuildLibrary() {
	 
 }
 
 public void refreshLibrary() {

 }
 
 public void clearLibrary() {
  writeCache(true);
 }
 
 public void removeOrphan() {
	 
 }
 
 public boolean hasIcon(String pkg) {
  return true;
 }
 
 public boolean hasLabel(String pkg) {
  return true; 
 }
 
 private void cacheIcon(Context self, Drawable icon, String filename) {
  try {
   File cacheFile = new File(mCacheDir, filename + ".png");
   FileOutputStream iconFile = new FileOutputStream(cacheFile);
   try {
	((BitmapDrawable) icon).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, iconFile);
   }
   catch(Exception iconExp) {
    ((BitmapDrawable) self.getDrawable(R.id.icon)).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, iconFile);
   }
  }
  catch(IOException exp) {
   MainActivity.HAS_LOG = true;
  }
 }

 public void readCache() {
  try {
   FileInputStream cache = new FileInputStream(mCacheFile);
   byte[] bytes = new byte[cache.available()];
   cache.read(bytes);
   builder = new StringBuilder(new String(bytes, "UTF-8"));
  }
  catch(Exception exp) {}
 }
 
 public void writeCache(boolean clear) {
  try {
   Writer writer = new OutputStreamWriter(new FileOutputStream(mCacheFile), "UTF-8");
   
   if(clear)
	writer.write("");
   else
    writer.write(getCache());
	
   writer.close();
  }
  catch(Exception exp) {}
 }
	
 public void writeCache() {
  writeCache(false);
 }
 
 public String[] getData() {
  return getCache().split(mCacheDelimiter);
 }*/
 
}
