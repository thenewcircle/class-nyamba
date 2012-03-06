package com.example.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
			// Post to twitter
			try {
				Twitter twitter = new Twitter("student", "password");
				twitter.setAPIRootUrl("http://yamba.marakana.com/api");
				twitter.setStatus(statuses[0]);
				return R.string.status_success;
			} catch (TwitterException e) {
				Log.e(TAG, "Failed to post", e);
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