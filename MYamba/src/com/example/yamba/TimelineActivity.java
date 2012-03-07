package com.example.yamba;

import winterwell.jtwitter.Twitter.Status;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class TimelineActivity extends ListActivity {
	static final String TAG = TimelineActivity.class.getSimpleName();
	private ArrayAdapter<Status> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create the adapter
		adapter = new ArrayAdapter<Status>(this, android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
	}

	// --- Menu Callbacks

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_update:
			startActivity(new Intent(this, StatusActivity.class));
			return true;
		case R.id.item_prefs:
			startActivity(new Intent(this, PrefsActivity.class));
			return true;
		case R.id.item_about:
			startActivity(new Intent(this, AboutActivity.class));
			return true;
		case R.id.item_refresh:
			startService(new Intent(this, RefreshService.class));
			return true;
		default:
			return false;
		}
	}

}
