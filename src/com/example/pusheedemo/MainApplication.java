package com.example.pusheedemo;

import java.net.MalformedURLException;
import java.net.URL;

import org.jboss.aerogear.android.Callback;
import org.jboss.aerogear.android.unifiedpush.PushConfig;
import org.jboss.aerogear.android.unifiedpush.Registrar;

import android.app.Application;
import android.util.Log;

public class MainApplication extends Application {

	private static final String TAG = MainApplication.class.getSimpleName();
	
	private Registrar registrar;
	
	@Override
	public void onCreate() {
		super.onCreate();
		try {
			final Registrar r = new Registrar(new URL("http://192.168.1.194:8080/ag-push/rest/registry/device"));
			PushConfig config = new PushConfig("272275396485");
			config.setMobileVariantId("c60807e6-ffc9-4833-bb02-8ead99e6c62b");
			config.setAlias("someGuy");
			
			r.register(getApplicationContext(), config, new Callback<Void>() {
				
				private static final long serialVersionUID = 1L;

				@Override
				public void onSuccess(Void ignore) {
					registrar  = r;
				}
				
				@Override
				public void onFailure(Exception exception) {
					Log.e(TAG, exception.getMessage(), exception);
				}
			});
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public Registrar getRegistrar() {
		return registrar;
	}
	
}
