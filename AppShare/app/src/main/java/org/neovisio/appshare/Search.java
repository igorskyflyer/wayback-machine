package org.neovisio.appshare;

import org.neovisio.appshare.Utils.*;

import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import android.support.v7.widget.Toolbar;
import java.util.*;
import android.view.animation.*;

public class Search extends AppCompatActivity {
 private GridView grid;
 private ArrayList<App> apps;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  if(!getIntent().hasExtra("apps")) {
   Toast.makeText(Search.this, "An error has occurred.", 3000).show();
   finish();
  }
  
  apps = getIntent().getExtras().getParcelableArrayList("apps");
  
  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
  setSupportActionBar(toolbar);
  
  ActionBar actionBar = getSupportActionBar();
  actionBar.setDisplayShowHomeEnabled(true);
  actionBar.setDisplayHomeAsUpEnabled(true);
  actionBar.setDisplayShowCustomEnabled(true);
  actionBar.setDisplayShowTitleEnabled(false);
 
  ((ImageView) findViewById(R.id.appIcon)).setVisibility(View.GONE);
  ((TextView) findViewById(R.id.appTitle)).setText("Search results");

  grid = (GridView) findViewById(R.id.gridApps);
  
  AppAdapter adapter = new AppAdapter(Search.this, apps);
  grid.setAdapter(adapter);
  grid.setVisibility(View.VISIBLE);
  grid.startAnimation(AnimationUtils.loadAnimation(Search.this, R.anim.search));   
 }

 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
  switch(item.getItemId()) {
   case R.id.homeAsUp:
    onBackPressed();
   break;
  }
  
  return true;
 }
}
