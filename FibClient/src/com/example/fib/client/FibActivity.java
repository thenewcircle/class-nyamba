package com.example.fib.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fib.common.FibRequest;
import com.example.fib.common.IFibService;

public class FibActivity extends Activity {
	static final String TAG = "FibActivity";
	EditText in;
	TextView out;
	IFibService fibService;
	FibRequest request;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		in = (EditText) findViewById(R.id.in);
		out = (TextView) findViewById(R.id.out);

		boolean bound = bindService(new Intent(
				"com.example.fib.common.IFibService"),
				new FibServiceConnection(), BIND_AUTO_CREATE);
		Log.d(TAG, "onCreate bound: "+bound);
	}

	class FibServiceConnection implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			fibService = IFibService.Stub.asInterface(binder);
			Log.d(TAG, "onServiceConnected");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			fibService = null;
			Log.d(TAG, "onServiceDisconnected");
		}
	}

	public void onClick(View v) throws RemoteException {
		long n = Long.parseLong(in.getText().toString());

		out.append("Recursive\n");
		long start = System.currentTimeMillis();
		long resultJ = fibService.fibJ(n);
		long timeJ = System.currentTimeMillis() - start;
		out.append(String.format("fibJ(%d)=%d (%d ms)\n", n, resultJ, timeJ));

		start = System.currentTimeMillis();
		long resultN = fibService.fibN(n);
		long timeN = System.currentTimeMillis() - start;
		out.append(String.format("fibN(%d)=%d (%d ms)\n", n, resultN, timeN));

		out.append("\nIterative\n");
		start = System.nanoTime();
		long resultJI = fibService.fibJI(n);
		long timeJI = System.nanoTime() - start;
		out.append(String.format("fibJI(%d)=%d (%d ms)\n", n, resultJI, timeJI));

		request = new FibRequest(4, n);
		start = System.nanoTime();
		long resultNI = fibService.fib(request);
		long timeNI = System.nanoTime() - start;
		out.append(String.format("fibNI(%d)=%d (%d ms)\n", n, resultNI, timeNI));

	}
}