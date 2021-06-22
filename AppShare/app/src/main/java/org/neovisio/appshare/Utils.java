package org.neovisio.appshare;

import java.util.*;

public class Utils {
 public static class WorkParams {
  public int id;
  public String name;
  
  public WorkParams(int id, String name) {
   this.id = id;
   this.name = name;
  }
 }
	
 public static abstract class Function {
  public void execute() {}
  public void execute(int param) {}
  public void execute(String param) {}
  public void execute(boolean param) {}
 }
 
 public static abstract class OnStartListener {
  public abstract void onStart();
 }
 
 public static abstract class OnWorkListener {
  public abstract void onWork(WorkParams params);
 }
 
 public static abstract class OnEndListener {
  public abstract void onEnd(ArrayList<App> apps);
 }
 
}
