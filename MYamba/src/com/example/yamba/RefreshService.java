package com.example.yamba;

import java.util.List;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.Twitter.Status;
import winterwell.jtwitter.TwitterException;
import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class RefreshService extends IntentService {
	static final String TAG = "RefreshService";

	public RefreshService() {
		super(TAG);
	}

	/** Happens on a separate worker thread, non-UI. */
	@Override
	protected void onHandleIntent(Intent intent) {
		
		// Get the data
		Twitter twitter = ((YambaApp)getApplication()).getTwitter();
		try {
			List<Status> timeline = twitter.getFriendsTimeline();
			((YambaApp)getApplication()).setTimeline(timeline);
			
			// Iterate over the list
			for(Status status: timeline) {
				Log.d(TAG, String.format("%s: %s",status.user.name, status.text) );
			}
		} catch (TwitterException e) {
			Log.e(TAG, "Failed to pull timeline", e);
		} 
		
		Log.d(TAG, "onHandleIntent");
	}

}
