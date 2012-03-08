package com.example.fib.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class FibService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		Log.d("FibService", "onBind");
		return new IFibServiceImpl();
	}

}
