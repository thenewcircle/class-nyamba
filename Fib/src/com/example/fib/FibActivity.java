package com.example.fib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FibActivity extends Activity {
	EditText in;
	TextView out;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        in = (EditText) findViewById(R.id.in);
        out = (TextView) findViewById(R.id.out);
    }
    
    public void onClick(View v) {
    		long n = Long.parseLong( in.getText().toString() );
    		
    		out.append("Recursive\n");
    		long start = System.currentTimeMillis();
    		long resultJ = FibLib.fibJ(n);
    		long timeJ = System.currentTimeMillis()-start;
    		out.append( String.format("fibJ(%d)=%d (%d ms)\n", n, resultJ, timeJ));
    		
    		start = System.currentTimeMillis();
    		long resultN = FibLib.fibN(n);
    		long timeN = System.currentTimeMillis()-start;
    		out.append( String.format("fibN(%d)=%d (%d ms)\n", n, resultN, timeN));
    		
    		
    		out.append("\nIterative\n");
    		start = System.nanoTime();
    		long resultJI = FibLib.fibJI(n);
    		long timeJI = System.nanoTime()-start;
    		out.append( String.format("fibJI(%d)=%d (%d ms)\n", n, resultJI, timeJI));
    		
    		start = System.nanoTime();
    		long resultNI = FibLib.fibNI(n);
    		long timeNI = System.nanoTime()-start;
    		out.append( String.format("fibNI(%d)=%d (%d ms)\n", n, resultNI, timeNI));

    }
}