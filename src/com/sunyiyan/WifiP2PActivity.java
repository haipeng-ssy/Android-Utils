package com.sunyiyan;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class WifiP2PActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi_p2_p);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wifi_p2, menu);
		return true;
	}

	/**
	 * 现在，Android的支持Wi-Fi的直接点对点点对点（P2P）Android系统的供电设备和其他类型的设备，
	 * 没有一个热点或互联网连接之间的连接。
	 * Android框架提供了一套Wi-Fi的P2P的API，
	 * 允许你去发现和连接到其他设备时，每个设备的Wi-Fi的直接支持，
	 * 然后沟通跨越距离远远长于蓝牙连接迅速​​连接。
	 * android api >14 才可以用
	 * Android中的WiFi P2P能够允许一定范围内的设备通过Wifi直接互连而不必通过热点或互联网。
	 * ● android.permission.INTERNET (WiFi P2P并不需要连接互联网，但是因为要用到Java Socket，所以要加这个权限)
	 * */
}
