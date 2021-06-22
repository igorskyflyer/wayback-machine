package org.neovisio.appshare;

import org.neovisio.appshare.Utils.*;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;

/*
 *
 *
 APPS FETCHER CLASS
  
 Initiated at application start,
 fetches the data about the applications,
 caches locally the icons of the installed
 applications and their labels.
 *
 *
 */
 
public class AppsFetcher extends AsyncTask<Void, Object, ArrayList<App>> {
 private Context self;
 
 private String mFilter;
 private int mSortBy;
 private int mSortOrder;
 
 private OnStartListener mOnStart;
 private OnWorkListener mOnWork;
 private OnEndListener mOnEnd;
 
 private long time;
 
 private final File CACHE_DIR;
 private final File CACHE_FILE;
 private final String CACHE_DELIMITER;
 
 public ArrayList<App> getApps() {
  PackageManager pm = self.getPackageManager();
  List<PackageInfo> packages = pm.getInstalledPackages(pm.GET_META_DATA);
  int size = packages.size();
 // Cacher cacher = new Cacher(self);
  ArrayList<App> apps = new ArrayList<App>(size);
  String[] data = new String[0];

  return apps;
  /*
  if(cacher.hasCache()) {
   cacher.readCache();
   cacher.decrypt();
   data = cacher.getData();
  }
  
  long old = new Date().getTime();
  int count = 0;
  
  for(int i = 0; i < size; i++) {
   PackageInfo app = packages.get(i);
   String appName = "";
   
   if(((app.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1)) continue;
   
   if(cacher.hasCache()) {
	int cacheSize = data.length - 1;
	
	if(!cacher.getCache().contains(app.packageName) || cacheSize <= 0) {
	 boolean launchable = pm.getLaunchIntentForPackage(app.packageName) != null;

	 if(!launchable)
	  cacher.append("~");

	 cacher.append(app.packageName);
	 cacher.append(CACHE_DELIMITER);

	 if(launchable) {
	  appName = pm.getApplicationLabel(app.applicationInfo).toString();
	  cacher.append(appName);
	 }

	 cacher.append(CACHE_DELIMITER);
	 mismatch = true;
	}
	 
	for(int j = 0; j < cacheSize; j = j + 2) {
	 if(mismatch) continue;
	 
	 String pkg = data[j];
	 
	 if(pkg != null && pkg.length() > 0 && pkg.charAt(0) == '~' && pkg.substring(1).equals(app.packageName))
	  continue;
	 
     if(pkg.equals(app.packageName)) {
	  appName = data[j + 1];
	  break;
	 }
	}
   }
   else
   if(!hasCache) {
    boolean launchable = pm.getLaunchIntentForPackage(app.packageName) != null;
    
	if(!launchable)
	 cacher.append("~");

    cacher.append(app.packageName);
	cacher.append(CACHE_DELIMITER);
	
	if(launchable) {
	 appName = pm.getApplicationLabel(app.applicationInfo).toString();
     cacher.append(appName);
	 mismatch = false;
	}
	 
    cacher.append(CACHE_DELIMITER);
   }
   
   if(appName.equals("")) continue;
   if(mFilter != "" && !appName.toLowerCase().contains(mFilter.toLowerCase())) continue;

   File appApk = new File(app.applicationInfo.sourceDir);
   long apkSize = 0;
   
   if(appApk.exists())
	apkSize = appApk.length();
   
   apps.add(new App(appName, app.packageName, app.applicationInfo.sourceDir, app.firstInstallTime, app.versionName, apkSize));
   count++;
   
   publishProgress(count, appName);
	   
   if(!new File(CACHE_DIR, app.packageName + ".png").exists())
    cacheIcon(self, pm.getApplicationIcon(app.applicationInfo), app.packageName);
  }
  
  if(mSortBy == Api.SORT_BY_NAME) {
   Collections.sort(apps, new Comparator<App>() {
    public int compare(App first, App second) {
	 int result;
	 
	 if(mSortOrder == Api.SORT_ORDER_ASC)
	  result = first.name.toString().compareToIgnoreCase(second.name.toString());
	 else
	  result = second.name.toString().compareToIgnoreCase(first.name.toString());
		 
     return result;
    }
   });
  }
  else
  if(mSortBy == Api.SORT_BY_DATE) {
   Collections.sort(apps, new Comparator<App>() {
    public int compare(App first, App second) {
	 int result;

	 if(mSortOrder == Api.SORT_ORDER_ASC)
	  result = new Date(first.installDate).compareTo(new Date(second.installDate));
	 else
	  result = new Date(second.installDate).compareTo(new Date(first.installDate));
	
	 return result;
	}
  });  
  }
  else
  if(mSortBy == Api.SORT_BY_SIZE) {
   Collections.sort(apps, new Comparator<App>() {
    public int compare(App first, App second) {
	 int result;

	 if(mSortOrder == Api.SORT_ORDER_ASC)
	  result = (int) (first.size - second.size);
	 else
	  result = (int) (second.size - first.size);

	 return result;
	}
   });
  }
 
  time = new Date().getTime() - old;
  
  if(!hasCache || mismatch)
   writeCache(cacher.toString());
  
  return apps;*/
 }
 
 public void setSortBy(int sortBy) {
  mSortBy = sortBy;
 }
 
 public int getSortBy() {
  return mSortBy;
 }
 
 public void setSortOrder(int sortOrder) { 
  mSortOrder = sortOrder;
 }
 
 public int getSortOrder() {
  return mSortOrder;
 }
 
 public void setFilter(String filter) {
  mFilter = filter;
 }
 
 public String getFilter() { 
  return mFilter;
 }
 
 public void setOnStart(OnStartListener function) {
  mOnStart = function;
 }

 public void setOnWork(OnWorkListener function) {
  mOnWork = function;
 }
 
 public void setOnEnd(OnEndListener function) {
  mOnEnd = function;
 }
 
 public AppsFetcher(Context context) {
  this.self = context;
  this.mSortBy = Api.SORT_BY_NAME;
  this.mSortOrder = Api.SORT_ORDER_ASC;
  this.mFilter = "";
  
  this.mOnStart = null;
  this.mOnWork = null;
  this.mOnEnd = null;
  
  this.CACHE_DIR = self.getExternalFilesDir("cache");
  this.CACHE_FILE = new File(self.getExternalFilesDir(""), "/.cache");
  this.CACHE_DELIMITER = ";";
	
  /*

	 if(mFilter == null)
	  animation = R.anim.entry;
	 else
	  animation = R.anim.search;
	 
    mGrid.startAnimation(AnimationUtils.loadAnimation(self, animation));
    }
   });
  }*/
 }
   
 @Override
 protected void onPreExecute() {
  super.onPreExecute();
  
  if(mOnStart != null)
   mOnStart.onStart();
 }

 @Override
 protected ArrayList<App> doInBackground(Void[] p1) {
  return getApps();
 }
   
 @Override
 protected void onProgressUpdate(Object[] values) {
  super.onProgressUpdate(values);
  
  if(mOnWork != null)
   mOnWork.onWork(new WorkParams((int) values[0], (String) values[1]));
 }

 @Override
 protected void onPostExecute(ArrayList<App> result) {
  Toast.makeText(self, String.valueOf(time) + "ms", 3000).show();
  if(mOnEnd != null)
   mOnEnd.onEnd(result);
 }
}
