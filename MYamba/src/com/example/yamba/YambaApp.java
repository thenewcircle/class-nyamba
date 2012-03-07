package com.example.yamba;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class YambaApp extends Application {
	static final String TAG = "YambaApp";
	private Twitter twitter=null;
	
	public synchronized Twitter getTwitter() {
		if(twitter==null) {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			String username = prefs.getString("username", null);
			String password = prefs.getString("password", null);
			String server = prefs.getString("server", null);

			Log.d(TAG, String.format("%s/%s@%s", username, password, server));
			twitter = new Twitter(username, password);
			twitter.setAPIRootUrl(server);
		}
		return twitter;
	}

}
