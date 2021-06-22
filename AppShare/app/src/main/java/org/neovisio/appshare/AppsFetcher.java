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
  ArrayList<App> apps = new ArrayList<App>(size);
  String[] data = new String[0];

  return apps;
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
