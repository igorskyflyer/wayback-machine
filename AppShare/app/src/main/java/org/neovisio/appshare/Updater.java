package org.neovisio.appshare;

import android.content.*;
import android.os.*;
import android.widget.*;
import java.io.*;
import java.net.*;

public class Updater extends AsyncTask<Void, Void, Void> {
 public Context self;
 public int response;

 private final String UPDATE_URL ="http://neovisio.org/android/app-share/update.php";

 public Updater(Context context) {
  this.self = context;
  this.response = 0;
 }

 @Override
 protected void onPreExecute() {
  // TODO: Implement this method
  super.onPreExecute();
  Toast.makeText(self, "Checking...", Toast.LENGTH_LONG).show();
 }

 @Override
 protected Void doInBackground(Void[] p1) {
  try {
   URL http = new URL(UPDATE_URL);
   try {
	URLConnection connection = http.openConnection();
   }
   catch(IOException ioExp) {
	this.response = -1;
   }
  }
  catch(MalformedURLException malUrlExp) {
   this.response = -1;
  }
  return null;
 }

 @Override
 protected void onProgressUpdate(Void[] values) {
  // TODO: Implement this method
  super.onProgressUpdate(values);
 }

 @Override
 protected void onPostExecute(Void result) {
  // TODO: Implement this method
  super.onPostExecute(result);
  response = 10;
 }	
}
