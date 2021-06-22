package org.neovisio.appshare;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.widget.*;
import java.io.*;
import java.util.*;

public class AppAdapter extends BaseAdapter {
 private Context self;
 
 private ArrayList<App> apps;
 private boolean[] selected;
 private boolean isSelectMode;
 
 private String mFilter;
 private Activity mActivity;
 private View mActionSend;
 
 private void sendFile(String apk, Activity activity) {
  if(activity == null) return;
   
  Intent intent = new Intent(Intent.ACTION_SEND);
  intent.setType("application/vnd.android.package-archive");
  intent.setPackage("com.android.bluetooth");
  intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(apk));
  intent.putExtra("res", 1);
  activity.startActivityForResult(Intent.createChooser(intent, "Share app via Bluetooth..."), Api.FILE_SEND_SINGLE);

  if(apk.toString().startsWith("org.neovisio."))
   Toast.makeText(self, "Thank you for sharing. :)", 3000).show();
 }
 
 private boolean getSelectMode() {
  boolean result = false;
  int size = selected.length;
  
  for(int i = 0; i < size; i++) {
   if(selected[i]) {
	result = true;
	break;
   }
  }
  
  return result;
 }
 
 private void openInfo(String packageName) {
  Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
  intent.setData(Uri.parse("package:" + packageName));
  self.startActivity(intent);
 }
 
 private void appInfo(final View view, final int id) {
  final App app = apps.get(id);
  PopupMenu popup = new PopupMenu(self, view);
  popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
  
  popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
   public boolean onMenuItemClick(MenuItem item) {
	if(!new File(app.apk).exists()) {
     apps.remove(id);
	 
	 Animation animRemove = AnimationUtils.loadAnimation(self, R.anim.abc_slide_out_top);
	 animRemove.setAnimationListener(new Animation.AnimationListener() {
	  public void onAnimationStart(Animation animation) {}
	  public void onAnimationRepeat(Animation animation) {}
	  
	  public void onAnimationEnd(Animation animation) {
	   notifyDataSetInvalidated();
	  }
	 });
	 
	 view.startAnimation(animRemove);
	 
	 return false;
	}
	
	switch(item.getItemId()) {
	 case R.id.popup_share_wifi:
	  Toast.makeText(self, "Paid version only.", 3000).show();
	 break;
		
	 case R.id.popup_share_bt:
	  sendFile(app.apk, mActivity);
	 break;
	 
	 case R.id.popup_save:
	  ProgressDialog dialog = new ProgressDialog(self);
	  dialog.setIndeterminate(true);
	  dialog.setCancelable(false);
	  dialog.setCanceledOnTouchOutside(false);
	
	  try {
	   ApkSaver saver = new ApkSaver(self, app.apk);
	   saver.setDialog(dialog);
	   saver.setShowToast(true);
	   saver.execute();
	  }
	  catch(Exception exp) {}
	  break;

	 case R.id.popup_info:
	  openInfo(app.packageName); 
	 break;

	 default:
	  onMenuItemClick(item);
	 break;
	}
	return true;  
   }  
  });
  
  popup.show();
 }
 
 public AppAdapter(Context context, ArrayList<App> apps) {
  this.self = context;
  this.isSelectMode = false;
  this.apps = apps;
  this.selected = new boolean[apps.size()];

  this.mFilter = "";
  this.mActivity = null;
  this.mActionSend = null;
 }

 public void setFilter(String filter) {
  mFilter = filter; 
 }
 
 public String getFilter() {
  return mFilter;
 }
 
 public void setActivity(Activity activity) {
  mActivity = activity;
 }
 
 public Activity getActivity() {
  return mActivity;
 }
 
 public void setActionSend(View view) {
  mActionSend = view;
 }
 
 public View getActionSend() {
  return mActionSend;
 }
 
 @Override
 public long getItemId(int index) {
  return index;
 }

 @Override
 public int getCount() {
  return apps.size();
 }

 @Override
 public Object getItem(int index) {
  return apps.get(index);
 }
 
 @Override
 public View getView(final int index, View item, ViewGroup p3) {
  final View appItem;
  App app = apps.get(index);
  
  String iconFile = app.packageName + ".png";
  File icon = new File(self.getExternalFilesDir("cache"), iconFile); 
 
  if(item == null) { 
   LayoutInflater inflater = (LayoutInflater) self.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
   item = new RelativeLayout(self);
   //item.setTag(app);
   item = inflater.inflate(R.layout.app_grid, null);
  }
  
  appItem = item;
  final CheckBox selectBox = ((CheckBox) item.findViewById(R.id.appSelect));
  final ImageView appIcon = ((ImageView) item.findViewById(R.id.appIcon));
  final TextView appLabel = ((TextView) item.findViewById(R.id.appLabel));
  
  appLabel.setText(app.name);
 // appLabel.setVisibility(View.GONE);
  selectBox.setChecked(selected[index]);
  selectBox.setVisibility(selected[index] ? View.VISIBLE : View.INVISIBLE);
  selectBox.setClickable(false);
  item.setLongClickable(true);
  appIcon.setLongClickable(true);
  appLabel.setLongClickable(true);
  
  item.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	if(isSelectMode) {
	 boolean state = !selected[index];
	 
     selectBox.setChecked(state);
	 selectBox.setVisibility(state ? View.VISIBLE : View.GONE);
	 selected[index] = state;
	 isSelectMode = getSelectMode();
	 
	 if(mActionSend != null)
      mActionSend.setVisibility(isSelectMode ? View.VISIBLE : View.GONE);	
	}
	else {
     appInfo(view, index);
	 view.startAnimation(AnimationUtils.loadAnimation(self, R.anim.app));
    }
   }
  });
  
  item.setOnLongClickListener(new OnLongClickListener() {
   public boolean onLongClick(View view) {
	isSelectMode = true;
    selectBox.setVisibility(View.VISIBLE);
	selectBox.setChecked(true);
	
	if(mActionSend != null)
     mActionSend.setVisibility(View.VISIBLE);
	
	selected[index] = true;
	return true;
   }
  });
  
  appIcon.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
    appItem.performClick();
   }  
  });
  
  appIcon.setOnLongClickListener(new OnLongClickListener() {
   public boolean onLongClick(View view) {
	appItem.performLongClick();
	return true;
   }  
  });
  
  appLabel.setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
	appItem.performClick();
   }  
  });
  
  appLabel.setOnLongClickListener(new OnLongClickListener() {
   public boolean onLongClick(View view) {
    appItem.performLongClick();
	return true;
   }  
  });
 
  if(icon.exists()) {
   try {
    appIcon.setImageBitmap(BitmapFactory.decodeFile(icon.toString()));
   }
   catch(Exception exp) {
    MainActivity.HAS_LOG = true;
   }
  }
  
  return item;
 }
}
