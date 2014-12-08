/**
 * 
 * @FileName PollingService.java
 * 
 */
package com.llbt.testpolling;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Class Name: PollingService.java
 * 
 * Function:
 * 
 * Modifications:
 * 
 * @author MeePwn
 * @DateTime 2014-12-3 ����4:40:31
 * @version 1.0
 */
public class PollingService extends Service {

	public static final String ACTION = "com.ryantang.service.PollingService";

	private Notification mNotification;
	private NotificationManager mManager;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		initNotifiManager();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		new PollingThread().start();
	}

	// ��ʼ��֪ͨ������
	private void initNotifiManager() {
		mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int icon = R.drawable.ic_launcher;
		mNotification = new Notification();
		mNotification.icon = icon;
		mNotification.tickerText = "New Message";
		mNotification.defaults |= Notification.DEFAULT_SOUND;
		mNotification.flags = Notification.FLAG_AUTO_CANCEL;
	}

	// ����Notification
	@SuppressWarnings("deprecation")
	private void showNotification() {
		mNotification.when = System.currentTimeMillis();
		// ���֪ͨ����������Ӧ��ҳ��
		Intent i = new Intent(this, MessageActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i,
				Intent.FLAG_ACTIVITY_NEW_TASK);
		mNotification.setLatestEventInfo(this,
				getResources().getString(R.string.app_name),
				"You have new message!", pendingIntent);
		mManager.notify(0, mNotification);
	}

	/**
	 * Polling thread ģ����Server��ѯ���첽�߳�
	 * 
	 * @Author Ryan
	 * @Create 2013-7-13 ����10:18:34
	 */
	int count = 0;

	class PollingThread extends Thread {
		@Override
		public void run() {
			System.out.println("Polling...");
			count++;
			// �������ܱ�5����ʱ����֪ͨ
			if (count % 5 == 0) {
				showNotification();
				System.out.println("New message!");
			}
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("Service:onDestroy");
	}

}