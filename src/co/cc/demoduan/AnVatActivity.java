package co.cc.demoduan;

import java.util.ArrayList;
import java.util.List;

import co.cc.demoduan.adapter.CustomListView;
import co.cc.demoduan.adapter.DataBaseHelper;
import co.cc.demoduan.adapter.CustomListView;
import co.cc.demoduan.adapter.Obj;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AnVatActivity extends Activity {
	ListView lvWordList2;
	CustomListView adapter;
	ArrayList<Obj> arr = new ArrayList<Obj>();
	DataBaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_an_vat);
		lvWordList2 = (ListView) findViewById(R.id.lvWordList2);
		db = new DataBaseHelper(getApplicationContext());
		db.openDataBase();
		arr = db.getAll2();
		adapter=new CustomListView(getApplicationContext(),R.layout.word_list2_item,arr);
		lvWordList2.setAdapter(adapter);
		
		lvWordList2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent chuyen=new Intent(getApplicationContext(),ListAnVatActivity.class);
				String b=arr.get(position).getWord_meaning();
				chuyen.putExtra("B", b);
				startActivity(chuyen);
			}
			
		});
	}@SuppressLint("NewApi") @TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_xem_list_view, menu);

		 SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
	  //      SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
	        SearchView searchView=(SearchView) menu.findItem(R.id.search).getActionView();
	            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
	          //  searchView.setIconifiedByDefault(false);   

	        SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener() 
	        {
	            @Override
	            public boolean onQueryTextChange(String newText) 
	            {
	                
	                return true;
	            }
	            @Override
	            public boolean onQueryTextSubmit(String query) 
	            {
	                // this is your adapter that will be filtered
	                adapter.getFilter().filter(query);
	                //System.out.println("on query submit: "+query);
	                return true;
	            }
	        };
	        searchView.setOnQueryTextListener(textChangeListener);

	        return super.onCreateOptionsMenu(menu);
	}
}
