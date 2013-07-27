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

import org.jboss.aerogear.android.unifiedpush.MessageHandler;
import org.jboss.aerogear.android.unifiedpush.Registrar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
		Registrar.registerMainThreadHandler(this);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Registrar.unregisterMainThreadHandler(this);
	}

	@Override
	public void onDeleteMessage(Context context, Bundle arg0) {
		/*see:https://developer.android.com/reference/com/google/android/gms/gcm/GoogleCloudMessaging.html#MESSAGE_TYPE_DELETED*/
	}

	@Override
	public void onError() {
		/*see: https://developer.android.com/reference/com/google/android/gms/gcm/GoogleCloudMessaging.html#MESSAGE_TYPE_SEND_ERROR*/		
	}

	@Override
	public void onMessage(Context context, Bundle arg0) {
		TextView text = (TextView) findViewById(R.id.hodor);
		text.setText(arg0.getString("alert"));
		text.invalidate();
	}

	
	
}
