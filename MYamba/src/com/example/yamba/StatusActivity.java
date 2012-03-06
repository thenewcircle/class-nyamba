package com.example.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StatusActivity extends Activity implements OnClickListener {
	static final String TAG = "StatusActivity";
	EditText editStatus;
	Button buttonUpdate;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.status);

		editStatus = (EditText) findViewById(R.id.edit_status);
		buttonUpdate = (Button) findViewById(R.id.button_update);
		buttonUpdate.setOnClickListener(this);
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
		case R.id.item_prefs:
			startActivity(new Intent(this, PrefsActivity.class));
			return true;
		default:
			return false;
		}
	}

	@Override
	public void onClick(View view) {
		String status = editStatus.getText().toString();

		new PostToTwitter().execute(status);

		Log.d(TAG, "onClicked with status: " + status);
	}

	class PostToTwitter extends AsyncTask<String, Void, Integer> {

		/** Runs on separate, non-UI thread. */
		@Override
		protected Integer doInBackground(String... statuses) {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			String username = prefs.getString("username", null);
			String password = prefs.getString("password", null);
			String server = prefs.getString("server", null);

			// Check if we have the login info
			if (username == null || password == null || server == null) {
				startActivity(new Intent(getApplicationContext(),
						PrefsActivity.class));
				return R.string.status_enter_login;
			}
 
			// Post to twitter
			try {
				Twitter twitter = new Twitter(username, password);
				twitter.setAPIRootUrl(server);
				twitter.setStatus(statuses[0]);
				return R.string.status_success;
			} catch (TwitterException e) {
				Log.e(TAG, "Failed to post", e);
				e.printStackTrace();
				return R.string.status_failed;
			}
		}

		/** Runs after doInBackground() completes. */
		@Override
		protected void onPostExecute(Integer result) {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG)
					.show();
		}

	}
}