/**
 * 
 * @FileName PollingUtils.java
 * 
 */
package com.llbt.testpolling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Class Name: PollingUtils.java
 * 
 * Function: ��ѯ������
 * 
 * Modifications:
 * 
 * @author MeePwn
 * @DateTime 2014-12-3 ����4:38:42
 * @version 1.0
 */
public class PollingUtils {

	/**
	 * Function:������ѯ����
	 * 
	 * @author MeePwn
	 * @DateTime 2014-12-3 ����4:39:54
	 * @param context
	 * @param seconds
	 * @param cls
	 * @param action
	 */
	public static void startPollingService(Context context, int seconds,
			Class<?> cls, String action) {
		// ��ȡAlarmManagerϵͳ����
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		// ��װ��Ҫִ��Service��Intent
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// �����������ʼʱ��
		long triggerAtTime = SystemClock.elapsedRealtime();

		// ʹ��AlarmManger��setRepeating�������ö���ִ�е�ʱ������seconds�룩����Ҫִ�е�Service
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
				seconds * 1000, pendingIntent);
	}

	/**
	 * Function:ֹͣ��ѯ����
	 * 
	 * @author MeePwn
	 * @DateTime 2014-12-3 ����4:39:41
	 * @param context
	 * @param cls
	 * @param action
	 */
	public static void stopPollingService(Context context, Class<?> cls,
			String action) {
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// ȡ������ִ�еķ���
		manager.cancel(pendingIntent);
	}
}
