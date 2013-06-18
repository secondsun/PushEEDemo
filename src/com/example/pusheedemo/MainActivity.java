package com.example.pusheedemo;

import org.jboss.aerogear.android.unifiedpush.MessageHandler;
import org.jboss.aerogear.android.unifiedpush.Registrar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements MessageHandler{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (getIntent() != null && getIntent().hasExtra("alert")) {
			onMessage(this, getIntent().getExtras());
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Registrar.unregisterBackgroundThreadHandler(NotifyingHandler.instance);
		Registrar.registerMainThreadHandler(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Registrar.unregisterMainThreadHandler(this);
		Registrar.registerBackgroundThreadHandler(NotifyingHandler.instance);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onDeleteMessage(Context context, Bundle arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(Context context, Bundle arg0) {
		TextView text = (TextView) findViewById(R.id.hodor);
		text.setText(arg0.getString("alert"));
		text.invalidate();
	}

	
	
}
