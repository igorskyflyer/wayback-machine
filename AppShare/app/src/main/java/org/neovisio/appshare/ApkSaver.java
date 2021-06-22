package org.neovisio.appshare;

import android.net.*;
import android.app.*;
import android.os.*;
import android.content.*;
import android.widget.Toast;
import java.io.*;

public class ApkSaver extends AsyncTask<Void, Boolean, Boolean> {
 private Context self;
 
 private String pLastError;
	
 private boolean mWasSaved;
 private String mUri;
 private ProgressDialog mDialog;
 private boolean mShowToast;

 private boolean saveApk(String apk) {
  boolean result = false;

  try {
   FileInputStream sourceFile = new FileInputStream(apk);
   FileOutputStream destFile = new FileOutputStream(self.getExternalFilesDir("appshare") + "/apk.apk");

   try {
	byte[] bytes = new byte[sourceFile.available()];
	sourceFile.read(bytes);
	destFile.write(bytes, 0, bytes.length);
	result = true;
   }
   catch(IOException expIO) {
    result = false;
	pLastError = expIO.getMessage();
   }
   finally {
	try {
	 sourceFile.close();
	 destFile.close();
	}
	catch(IOException expIOClose) {
	 result = false;
	 pLastError = expIOClose.getMessage();
	}
   }
  }
  catch(FileNotFoundException expNotFound) {
   result = false;
   pLastError = expNotFound.getMessage();
  }
  return result;
 }
 
 public ApkSaver(Context context, String apk) {
  this.self = context;
  
  this.pLastError = "";
  
  this.mUri = apk;
  this.mWasSaved = false;
  this.mDialog = null;
  this.mShowToast = false;
 }
  
 public String getLastMessage() {
  return pLastError;
 }
 
 public ProgressDialog getDialog() {
  return mDialog;
 }
  
 public void setDialog(ProgressDialog dialog) {
  mDialog = dialog;
 }
	
 public void setShowToast(boolean showToast) {
  mShowToast = showToast;
 }
 
 public boolean getShowToast() {
  return mShowToast;
 }
  
 @Override
 protected void onPreExecute() {
  super.onPreExecute();
   
  if(mDialog != null)
   mDialog.show();
 }

 @Override
 protected Boolean doInBackground(Void[] p1) {  
  return saveApk(mUri);
 }

 @Override
 protected void onPostExecute(Boolean result) {
  super.onPostExecute(result);
 
  if(mDialog != null && mDialog.isShowing())
   mDialog.dismiss();

  if(mShowToast && result)
   Toast.makeText(self, "Saved.", 3000).show();
  else
   Toast.makeText(self, "The installer could not be saved.", 3000).show();
 } 
}
