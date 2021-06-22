package org.neovisio.appshare;

import android.content.*;
import android.os.*;
import android.preference.*;
import android.widget.*;

public class Settings extends PreferenceActivity {
 private SharedPreferences config;
 private SharedPreferences.OnSharedPreferenceChangeListener listener;
 private MyPreferenceFragment fragment;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  config = new Api.Config(this).getConfig();
  fragment = new MyPreferenceFragment(config);
  getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();

  listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
   public void onSharedPreferenceChanged(SharedPreferences preferences, String key) {
	if(key.equals("drawerStyle")) {
	 String style = preferences.getString("drawerStyle", "0");
	 fragment.findPreference("drawerColumns").setEnabled(style.equals("0"));
	 fragment.findPreference("showLabels").setEnabled(style.equals("0"));
	}
	else
    if(key.equals("drawerColumns")) {
	 int columns = Integer.parseInt(preferences.getString("drawerColumns", "3"));

	 if(columns < 3 || columns > 8) {
	  preferences.edit().putString("drawerColumns", "3").commit();
	  Toast.makeText(getApplicationContext(), "You must enter a number between 3 and 8.", 3000).show();
	 }
	}
   }
  };
 }

 @Override
 protected void onResume() {
  config.registerOnSharedPreferenceChangeListener(listener);
  super.onResume();
 }

 @Override
 protected void onPause() {
  config.unregisterOnSharedPreferenceChangeListener(listener);
  super.onPause();
 }

 public static class MyPreferenceFragment extends PreferenceFragment {
  private SharedPreferences settings;

  public MyPreferenceFragment() {

  }

  public MyPreferenceFragment(SharedPreferences config) {
   this.settings = config;
  }

  @Override
  public void onCreate(final Bundle savedInstanceState) {
   super.onCreate(savedInstanceState);
   addPreferencesFromResource(R.xml.settings);

   String style = settings.getString("drawerStyle", "0");
   boolean isGrid = style.equals("0");

   findPreference("drawerColumns").setEnabled(isGrid);
   findPreference("showLabels").setEnabled(isGrid);

   if(isGrid) {
	int numCols = Integer.parseInt(settings.getString("drawerColumns", "4"));
    findPreference("drawerColumns").setDefaultValue(numCols);
   }
  }
 }
}
