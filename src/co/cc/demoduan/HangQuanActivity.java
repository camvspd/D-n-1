package co.cc.demoduan;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import co.cc.demoduan.adapter.DataBaseHelper;
import co.cc.demoduan.adapter.Obj;
import co.cc.demoduan.adapter.CustomListView;

public class HangQuanActivity extends Activity {
	CustomListView adapter;
	ListView lvWordList;
	ArrayList<Obj> arrList = new ArrayList<Obj>();
	DataBaseHelper db;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hang_quan);
		lvWordList = (ListView) findViewById(R.id.lvWordList);
		db = new DataBaseHelper(getApplicationContext());
		db.openDataBase();
		arrList = db.getAll();
		adapter = new CustomListView(getApplicationContext(),
				R.layout.word_list2_item, arrList);
		lvWordList.setAdapter(adapter);

		lvWordList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// When clicked, show a toast with the TextView text
				Intent chuyen = new Intent(getApplicationContext(),
						ListHangQuanActivity.class);
				String a = arrList.get(position).getWord_meaning();
				chuyen.putExtra("A", a);
				startActivity(chuyen);

			}
		});
	}

	@SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_xem_list_view, menu);
		
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		// SearchView searchView = (SearchView)
		// menu.findItem(R.id.action_search).getActionView();
		SearchView searchView = (SearchView) menu.findItem(R.id.search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		// searchView.setIconifiedByDefault(false);

		SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextChange(String newText) {
				
				return false;
			}
			@Override
			public boolean onQueryTextSubmit(String query) {
				// this is your adapter that will be filtered
				adapter.getFilter().filter(query);
				//adapter.notifyDataSetChanged();
				// System.out.println("on query submit: "+query);
				Log.d("hello","test =+=======================================");		
				return true;
			}
		};
		searchView.setOnQueryTextListener(textChangeListener);

		return super.onCreateOptionsMenu(menu);
	}
}
