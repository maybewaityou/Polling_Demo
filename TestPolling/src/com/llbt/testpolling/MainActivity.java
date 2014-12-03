package com.llbt.testpolling;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	// 轮询默认时间间隔
	private static int DEFAULT_TIME = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.start:
			System.out.println("Start polling service...");
			PollingUtils.startPollingService(this, DEFAULT_TIME,
					PollingService.class, PollingService.ACTION);
			break;
		case R.id.stop:
			System.out.println("Stop polling service...");
			PollingUtils.stopPollingService(this, PollingService.class,
					PollingService.ACTION);
			break;
		}
	}
}
