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
 * Function: 轮询工具类
 * 
 * Modifications:
 * 
 * @author MeePwn
 * @DateTime 2014-12-3 下午4:38:42
 * @version 1.0
 */
public class PollingUtils {

	/**
	 * Function:开启轮询服务
	 * 
	 * @author MeePwn
	 * @DateTime 2014-12-3 下午4:39:54
	 * @param context
	 * @param seconds
	 * @param cls
	 * @param action
	 */
	public static void startPollingService(Context context, int seconds,
			Class<?> cls, String action) {
		// 获取AlarmManager系统服务
		AlarmManager manager = (AlarmManager) context
				.getSystemService(Context.ALARM_SERVICE);

		// 包装需要执行Service的Intent
		Intent intent = new Intent(context, cls);
		intent.setAction(action);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);

		// 触发服务的起始时间
		long triggerAtTime = SystemClock.elapsedRealtime();

		// 使用AlarmManger的setRepeating方法设置定期执行的时间间隔（seconds秒）和需要执行的Service
		manager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtTime,
				seconds * 1000, pendingIntent);
	}

	/**
	 * Function:停止轮询服务
	 * 
	 * @author MeePwn
	 * @DateTime 2014-12-3 下午4:39:41
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
		// 取消正在执行的服务
		manager.cancel(pendingIntent);
	}
}
