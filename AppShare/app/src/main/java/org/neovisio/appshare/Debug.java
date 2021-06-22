package org.neovisio.appshare;

import android.content.*;
import android.widget.*;
import java.io.*;
import android.net.*;

public class Debug {
 private Context self;
 
 public String DEBUG_DIR;
 public File DEBUG_FILE;
 public Uri DEBUG_URL;
	
 public Debug(Context context) {
  this.self = context;
 
  this.DEBUG_DIR = self.getFilesDir() + "/log";
  this.DEBUG_FILE = new File(DEBUG_DIR + "/package.log");
  this.DEBUG_URL = Uri.parse("http://neovisio.org/android-api/debug-appshare.php");
 }
 
 public void sendLog(String log) {
  Toast.makeText(self, "Sending log...", 2000).show();
 }
}
