package com.example.yamba;

import java.util.List;

import winterwell.jtwitter.Twitter.Status;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class TimelineActivity extends ListActivity {
	static final String TAG = TimelineActivity.class.getSimpleName();
	private ArrayAdapter<Status> adapter;
	private TimelineReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Create the adapter
		adapter = new ArrayAdapter<Status>(this, android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
		
		receiver = new TimelineReceiver();
	}

	private static final IntentFilter FILTER = new IntentFilter(
			YambaApp.YAMBA_NEW_TIMELINE);

	@Override
	protected void onStart() {
		super.onStart();
		registerReceiver(receiver, FILTER);
	}

	@Override
	protected void onStop() {
		super.onStop();
		unregisterReceiver(receiver);
	}

	class TimelineReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			List<Status> timeline = ((YambaApp)getApplication()).getTimeline();
			adapter.clear();
			adapter.addAll(timeline);
			Log.d(TAG, "TimelineReceiver onReceived");
		}
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
