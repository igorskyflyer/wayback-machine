package org.neovisio.appshare;

import java.util.*;
import android.net.Uri;
import java.net.*;
import android.content.*;

public class Queue {
 private Context self;
 private List<Uri> tasks;
	
 private void sendFile(Uri apk) {
  Intent intent = new Intent(Intent.ACTION_SEND);
  intent.setType("application/vnd.android.package-archive");
  intent.setPackage("com.android.bluetooth");
  intent.putExtra(Intent.EXTRA_STREAM, apk);
  self.startActivity(Intent.createChooser(intent, "Share app via Bluetooth..."));
 }
 
 public Queue(Context context) {
  this.self = context;
  this.tasks = new ArrayList<Uri>();
 }
 
 public void add(Uri apk) {
  tasks.add(apk);
 }
 
 public void remove(int index) {
  tasks.remove(index);
 }
 
 public int size() {
  return tasks.size();
 }
 
 public void run() {
  if(tasks.size() == 0) return;
  sendFile(tasks.get(0));
  remove(0);
 }
 
 public void runAll() {
  if(tasks.size() == 0) return;
  
  int size = tasks.size();
  
  for(int i = 0; i < size; i++) {
   sendFile(tasks.get(i));
   remove(i);
  }
 }
}
