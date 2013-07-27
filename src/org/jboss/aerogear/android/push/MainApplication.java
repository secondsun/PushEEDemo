/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.android.push;

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
	
	private static final String VARIANT_ID = "c54d6bcd-b669-4626-b456-cbf375b86052";
	private static final String SECRET = "0fceb381-dd25-45b9-9de2-7ee2ffd895ca";
	private static final String GCM_SENDER_ID = "272275396485";
	private static final String UNIFIED_PUSH_URL = "http://192.168.1.194:8080/ag-push/rest/registry/device";
	private static final String MY_ALIAS = "john";
	
	@Override
	public void onCreate() {
		super.onCreate();
		try {
			final Registrar r = new Registrar(new URL(UNIFIED_PUSH_URL));
			PushConfig config = new PushConfig(GCM_SENDER_ID);
			config.setVariantID(VARIANT_ID);
			config.setSecret(SECRET);
			config.setAlias(MY_ALIAS);
			
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
