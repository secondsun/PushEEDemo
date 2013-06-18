package com.example.pusheedemo;

import org.jboss.aerogear.android.unifiedpush.AGPushMessageReceiver;
import org.jboss.aerogear.android.unifiedpush.MessageHandler;
import org.jboss.aerogear.android.unifiedpush.Registrar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

public class NotifyingHandler implements MessageHandler {

	static final String TAG = "GCMDemo";
    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;
    Context ctx;
    
    public static final NotifyingHandler instance = new NotifyingHandler();
    
    private NotifyingHandler(){}
    
    
    @Override
    public void onMessage(Context context, Bundle message) {
    	ctx = context;
        sendNotification(message.getString("alert"));
    }

    // Put the GCM message into a notification and post it.
    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                ctx.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0,
                new Intent(ctx, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).putExtra("alert", msg), 0);
        
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentTitle("GCM Notification")
        .setStyle(new NotificationCompat.BigTextStyle()
        .bigText(msg))
        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

	@Override
	public void onDeleteMessage(Context context, Bundle arg0) {
		
	}

	@Override
	public void onError() {
		
	}



}
