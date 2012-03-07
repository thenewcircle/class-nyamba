package com.example.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutActivity extends Activity {
	WebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);

		webview = (WebView) findViewById(R.id.webview);
		webview.setWebViewClient( new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			} 
		});

		webview.loadUrl("file:///android_asset/index.html");
	}
}
