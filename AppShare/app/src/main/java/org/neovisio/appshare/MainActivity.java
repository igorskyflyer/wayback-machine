package org.neovisio.appshare;

import org.neovisio.appshare.Utils.*;

import android.app.*;
import android.content.*;
import android.content.res.*;
import android.net.*;
import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.text.*;
import android.text.method.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.util.*;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import java.io.*;
import java.nio.charset.*;
import android.view.animation.*;
import android.animation.*;


public class MainActivity extends AppCompatActivity {
 public static boolean HAS_LOG = false;
 
 private GridView grid;
 private Api.Config config;
 private String filter;
 
 public static void selectMode(Activity activity, boolean state) {
  activity.findViewById(R.id.action_send_selected).setVisibility(state ? View.VISIBLE : View.GONE);
 }
 
 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.main);
  
  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
  setSupportActionBar(toolbar);

  ActionBar actionBar = getSupportActionBar();
  actionBar.setDisplayShowHomeEnabled(false);
  actionBar.setDisplayHomeAsUpEnabled(false);
  actionBar.setDisplayShowCustomEnabled(true);
  actionBar.setDisplayShowTitleEnabled(false);
  
  grid = (GridView) findViewById(R.id.gridApps);
  config = new Api.Config(this);
  filter = "";
  
  findViewById(R.id.sliderBtnFolder).setOnClickListener(new OnClickListener() {
   public void onClick(View view) {
    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    intent.setDataAndType(Uri.parse(getExternalFilesDir("").toString()), "*/*");
    startActivity(Intent.createChooser(intent, ""));
   } 
  });
  
  if(config.getString("firstRun", "yes").equals("yes")) {
   startActivity(new Intent(this, Intro.class));
   config.setString("firstRun", "no");
  }
  
  AppsFetcher fetcher = new AppsFetcher(this);
  fetcher.setSortBy(config.getInt("sortBy", Api.SORT_BY_NAME));
  fetcher.setSortOrder(config.getInt("orderBy", Api.SORT_ORDER_ASC));
	 
  final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
  dialog.setIndeterminate(true);
  dialog.setCancelable(false);
  dialog.setCanceledOnTouchOutside(false);
  dialog.setMessage("Loading applications..."); 
  
  fetcher.setOnStart(new OnStartListener() {
   public void onStart() {
	dialog.show();
   }
  });
  
  fetcher.setOnEnd(new OnEndListener() {
   public void onEnd(ArrayList<App> apps) {
    dialog.dismiss();

	AppAdapter adapter = new AppAdapter(MainActivity.this, apps);
	grid.setAdapter(adapter);
	
	grid.setVisibility(View.VISIBLE);
	Animator animation = ViewAnimationUtils.createCircularReveal(grid, grid.getWidth() / 2, grid.getHeight() / 2, 0, grid.getHeight() / 2);
	animation.start();
	//grid.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.entry));
   }
  });
  
  fetcher.execute();
 }

 @Override
 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  super.onActivityResult(requestCode, resultCode, data);
  
  if(requestCode == Api.FILE_SEND_SINGLE) {
   Toast.makeText(MainActivity.this, "Single", 2000).show();
  }
  else
  if(requestCode == Api.FILE_SEND_MUTLIPLE) {
   Toast.makeText(MainActivity.this, "Multiple", 2000).show();
   Toast.makeText(MainActivity.this, String.valueOf(resultCode == RESULT_OK), 2000).show();
  }
 }
 
 @Override
 public void onConfigurationChanged(Configuration newConfig) {
  super.onConfigurationChanged(newConfig);
  
  int columns = Integer.parseInt(config.getString("drawerColumns", "4"));
  
  if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
   grid.setNumColumns(columns);
  else
  if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
   grid.setNumColumns(columns + 2);
   
   grid.setVerticalSpacing(4);
   for(int i = 0; i < grid.getChildCount(); i++)
    grid.getChildAt(i).findViewById(R.id.appLabel).setVisibility(View.GONE);
 }
 
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
  MenuInflater menuInflater = getMenuInflater();
  menuInflater.inflate(R.menu.main_menu, menu);
  MenuItem searchItem = menu.findItem(R.id.action_search);
  android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) searchItem.getActionView();

  searchView.setOnCloseListener(new android.support.v7.widget.SearchView.OnCloseListener() {
   @Override
   public boolean onClose() {
    return true;
   }
  });
 
  searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
   @Override
   public boolean onQueryTextChange(String query) {
	filter = query;
	return true;
   }
	  
   @Override
   public boolean onQueryTextSubmit(String query) {
	AppsFetcher fetcher = new AppsFetcher(MainActivity.this);
	fetcher.setFilter(query);
	fetcher.setSortBy(Api.SORT_BY_DATE);
	fetcher.setSortOrder(Api.SORT_ORDER_DESC);
	
	fetcher.setOnEnd(new OnEndListener() {
	 public void onEnd(ArrayList<App> apps) {
	  if(apps.size() == 0)
	   Toast.makeText(MainActivity.this, "No results.", 3000).show();
	  else {
       Intent searchIntent = new Intent(MainActivity.this, Search.class);
	   Bundle bundle = new Bundle();
	   bundle.putParcelableArrayList("apps", apps);
	   searchIntent.putExtras(bundle);
       startActivity(searchIntent);
	  }
	 }
	});
	
	fetcher.execute();
    
    return true;
   }
  });
 
  SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
  searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
 
  return true;
 }

 @Override
 public void onBackPressed() {
  // super.onBackPressed();
  moveTaskToBack(false);
 }

 @Override
 protected void onResume() {
  super.onResume();
  grid.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.abc_slide_in_top));
 }

 @Override
 protected void onStop() {
  super.onStop();
 }
 
 @Override
 public boolean onOptionsItemSelected(MenuItem item) {
  switch(item.getItemId()) {
   case R.id.home:
	//onBackPressed();
	moveTaskToBack(false);
   break;
	  
   case R.id.menu_sort_by:
	final Dialog dialog = new Dialog(this);
	final Api.Config appConfig = config;
	final int initSortBy = config.getInt("sortBy", Api.SORT_BY_NAME);
	final int initOrderBy = config.getInt("orderBy", Api.SORT_ORDER_ASC);
	dialog.setContentView(R.layout.dialog_sort);
	dialog.setCancelable(false);
	dialog.setCanceledOnTouchOutside(false);
	
	switch(initSortBy) {
	 case 1:
	  ((RadioButton) dialog.findViewById(R.id.dialogSortRadioDate)).setChecked(true);
	 break;
	 
	 case 2:
	  ((RadioButton) dialog.findViewById(R.id.dialogSortRadioSize)).setChecked(true);
	 break;
	 
	 default:
	  ((RadioButton) dialog.findViewById(R.id.dialogSortRadioName)).setChecked(true);
	 break; 
	}
	
	if(initOrderBy == Api.SORT_ORDER_ASC)
     ((RadioButton) dialog.findViewById(R.id.dialogSortRadioAsc)).setChecked(true);
	else
	 ((RadioButton) dialog.findViewById(R.id.dialogSortRadioDesc)).setChecked(true);

	dialog.findViewById(R.id.dialogSortBtnClose).setOnClickListener(new OnClickListener() {
	 public void onClick(View view) {
	  dialog.dismiss();
	 }
	});
	
	dialog.findViewById(R.id.dialogSortBtnOk).setOnClickListener(new OnClickListener() {
	 public void onClick(View view) {
	  int sortBy, orderBy;
	  
	  if(((RadioButton) dialog.findViewById(R.id.dialogSortRadioName)).isChecked())
	   sortBy = Api.SORT_BY_NAME;
	  else
	  if(((RadioButton) dialog.findViewById(R.id.dialogSortRadioDate)).isChecked())
	   sortBy = Api.SORT_BY_DATE;
	  else
	   sortBy = Api.SORT_BY_SIZE;
	 
	  if(((RadioButton) dialog.findViewById(R.id.dialogSortRadioAsc)).isChecked())
	   orderBy = Api.SORT_ORDER_ASC;
	  else
	   orderBy = Api.SORT_ORDER_DESC;
	   
	  if(sortBy == initSortBy && orderBy == initOrderBy) {
	   Toast.makeText(MainActivity.this, "Nothing to sort here.", 3000).show();
	   dialog.dismiss();
	   return;
	  }
	  
	  appConfig.setInt("sortBy", sortBy);
	  appConfig.setInt("orderBy", orderBy);
	  
	  AppsFetcher fetcher = new AppsFetcher(MainActivity.this);
	  fetcher.setSortBy(sortBy);
	  fetcher.setSortOrder(orderBy);
	  
	  fetcher.setOnEnd(new OnEndListener() {
	   public void onEnd(ArrayList<App> apps) {
	    AppAdapter adapter = new AppAdapter(MainActivity.this, apps);
	    grid.setAdapter(adapter);
		grid.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.abc_slide_in_top));
       }
	  });
	  
	  fetcher.execute();
	  dialog.dismiss();
	 }
	});
	
	dialog.show();
   break;
	  
   case R.id.menu_settings:
	Intent intent = new Intent(this, Settings.class);
	startActivity(intent);
   break;
   
   case R.id.action_send_selected:
	/*new Updater(MainActivity.this).execute();
	   
	int size = grid.getChildCount();
	Queue tasks = new Queue(this);
	
	for(int i = 0; i < size; i++) {
	 if(((CheckBox) grid.getChildAt(i).findViewById(R.id.appSelect)).isChecked())
	  tasks.add(apps.get(i).uri);
	}
	
	tasks.run();*/
	Toast.makeText(MainActivity.this, grid.getChildCount(), 3000).show();
   break;
   
   case R.id.menu_about:
	View messageView = getLayoutInflater().inflate(R.layout.about, null, false);
    TextView aboutText = (TextView) messageView.findViewById(R.id.AboutText);
	aboutText.setText(Html.fromHtml("App Share is an application that allows you to easily share applications installed on your device.\n<br/><br/>Special thanks to <a href=\"http://www.flaticon.com/authors/madebyoliver\"><strong>MadBoyOliver</strong></a> for the icons.<br/><br/>App Share is a property of <a href=\"http://neovisio.org\"><strong>NeoVisio</strong></a> and its author Igor Dimitrijević."));
	aboutText.setMovementMethod(LinkMovementMethod.getInstance());
	
	android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
	builder.setIcon(R.mipmap.ic_launcher);
	builder.setTitle("About");
	 
	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	 public void onClick(DialogInterface dialog, int button) {
	  dialog.dismiss();
	 }
    });
	 
	builder.setView(messageView);
	builder.create();
	builder.show();
   break;
	  
   default:
	super.onOptionsItemSelected(item);
   break;
  } 
  
  return true;
 }
}
