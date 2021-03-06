package com.sunyiyan.sensor;

import com.sunyiyan.R;
import android.os.Bundle;
import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

public class WifiActivity extends Activity {
	/** Called when the activity is first created. */
	private TextView allNetWork;
	private Button scan;
	private Button start;
	private Button stop;
	private Button check;
	private WifiAdmin mWifiAdmin;
	private WifiInfo  mWifiInfo;
	private List<WifiConfiguration> mWifiListConfiguration;
	// 扫描结果列表
	private List<ScanResult> list;
	private ScanResult mScanResult;
	private StringBuffer sb = new StringBuffer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi);
		mWifiAdmin = new WifiAdmin(WifiActivity.this);
		mWifiInfo  = mWifiAdmin.mWifiInfo;
		mWifiListConfiguration = new ArrayList<WifiConfiguration>();
		mWifiListConfiguration = mWifiAdmin.mWifiConfigurations;
		init();
	}

	public void init() {
		allNetWork = (TextView) findViewById(R.id.allNetWork);
		scan = (Button) findViewById(R.id.scan);
		start = (Button) findViewById(R.id.start);
		stop = (Button) findViewById(R.id.stop);
		check = (Button) findViewById(R.id.check);
		scan.setOnClickListener(new MyListener());
		start.setOnClickListener(new MyListener());
		stop.setOnClickListener(new MyListener());
		check.setOnClickListener(new MyListener());
	}

	private class MyListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.scan:// 扫描网络
				getAllNetWorkList();
				break;
			case R.id.start:// 打开Wifi
				mWifiAdmin.openWifi();
				Toast.makeText(WifiActivity.this,
						"当前wifi状态为：" + mWifiAdmin.checkState(), 1).show();
				break;
			case R.id.stop:// 关闭Wifi
				mWifiAdmin.closeWifi();
				Toast.makeText(WifiActivity.this,
						"当前wifi状态为：" + mWifiAdmin.checkState(), 1).show();
				break;
			case R.id.check:// Wifi状态
				Toast.makeText(WifiActivity.this,
						"当前wifi状态为：" + mWifiAdmin.checkState(), 1).show();
				break;
			default:
				break;
			}
		}

	}

	public void getAllNetWorkList() {
		// 每次点击扫描之前清空上一次的扫描结果
		if (sb != null) {
			sb = new StringBuffer();
		}
		// 开始扫描网络
		mWifiAdmin.startScan();
		list = mWifiAdmin.getWifiList();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				// 得到扫描结果
				mScanResult = list.get(i);
				sb = sb.append(" mScanResult.BSSID="+mScanResult.BSSID + "  ")
						.append(" mScanResult.SSID="+mScanResult.SSID + "   ")
						.append(" mScanResult.capabilities=" +mScanResult.capabilities+ "   ")
						.append(" mScanResult.frequency=" +mScanResult.frequency+ "   ")
						.append(" mScanResult.level=" +mScanResult.level+ "\n\n");
			}
			allNetWork.setText("扫描到的wifi网络：\n" + sb.toString()+"\n"
					           /*获得正在连接网络的相关信息*/
					           +" mWifiAdmin.getWifiInfo()="+mWifiAdmin.getWifiInfo()
					           +"\n mWifiInfo.getBSSID()="+mWifiInfo.getBSSID()
					           +"\n mWifiInfo.getIpAddress()="+mWifiInfo.getIpAddress()
					           +"\n mWifiInfo.getLinkSpeed()="+mWifiInfo.getLinkSpeed()
					           +"\n mWifiInfo.getNetworkId()="+mWifiInfo.getNetworkId()
					           +"\n mWifiInfo.getRssi()="+mWifiInfo.getRssi()
					           +"\n mWifiInfo.getHiddenSSID()="+mWifiInfo.getHiddenSSID()
					           
					           +"\n mWifiListConfiguration"+ mWifiListConfiguration.get(0).toString()
					           );
			
		}
	}

	public class WifiAdmin {
		// 定义一个WifiManager对象
		private WifiManager mWifiManager;
		// 定义一个WifiInfo对象
		private WifiInfo mWifiInfo;
		// 扫描出的网络连接列表
		private List<ScanResult> mWifiList;
		// 网络连接列表
		private List<WifiConfiguration> mWifiConfigurations;
		WifiLock mWifiLock;

		public WifiAdmin(Context context) {
			// 取得WifiManager对象
			mWifiManager = (WifiManager) context
					.getSystemService(Context.WIFI_SERVICE);
			// 取得WifiInfo对象
			mWifiInfo = mWifiManager.getConnectionInfo();
		}

		// 打开wifi
		public void openWifi() {
			if (!mWifiManager.isWifiEnabled()) {
				mWifiManager.setWifiEnabled(true);
			}
		}

		// 关闭wifi
		public void closeWifi() {
			if (!mWifiManager.isWifiEnabled()) {
				mWifiManager.setWifiEnabled(false);
			}
		}

		// 检查当前wifi状态
		public int checkState() {
			return mWifiManager.getWifiState();
		}

		// 锁定wifiLock
		public void acquireWifiLock() {
			mWifiLock.acquire();
		}

		// 解锁wifiLock
		public void releaseWifiLock() {
			// 判断是否锁定
			if (mWifiLock.isHeld()) {
				mWifiLock.acquire();
			}
		}

		// 创建一个wifiLock
		public void createWifiLock() {
			mWifiLock = mWifiManager.createWifiLock("test");
		}

		// 得到配置好的网络
		public List<WifiConfiguration> getConfiguration() {
			return mWifiConfigurations;
		}

		// 指定配置好的网络进行连接
		public void connetionConfiguration(int index) {
			if (index > mWifiConfigurations.size()) {
				return;
			}
			// 连接配置好指定ID的网络
			mWifiManager.enableNetwork(
					mWifiConfigurations.get(index).networkId, true);
		}

		public void startScan() {
			mWifiManager.startScan();
			// 得到扫描结果
			mWifiList = mWifiManager.getScanResults();
			// 得到配置好的网络连接
			mWifiConfigurations = mWifiManager.getConfiguredNetworks();
		}

		// 得到网络列表
		public List<ScanResult> getWifiList() {
			return mWifiList;
		}

		// 查看扫描结果
		public StringBuffer lookUpScan() {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < mWifiList.size(); i++) {
				sb.append("Index_" + new Integer(i + 1).toString() + ":");
				// 将ScanResult信息转换成一个字符串包
				// 其中把包括：BSSID、SSID、capabilities、frequency、level
				sb.append((mWifiList.get(i)).toString()).append("\n");
			}
			return sb;
		}

		public String getMacAddress() {
			return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
		}

		public String getBSSID() {
			return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
		}

		public int getIpAddress() {
			return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
		}

		// 得到连接的ID
		public int getNetWordId() {
			return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
		}

		// 得到wifiInfo的所有信息
		public String getWifiInfo() {
			return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
		}

		// 添加一个网络并连接
		public void addNetWork(WifiConfiguration configuration) {
			int wcgId = mWifiManager.addNetwork(configuration);
			mWifiManager.enableNetwork(wcgId, true);
		}

		// 断开指定ID的网络
		public void disConnectionWifi(int netId) {
			mWifiManager.disableNetwork(netId);
			mWifiManager.disconnect();
		}
	}

	/**
	 * WIFI就是一种无线联网技术，常见的是使用无线路由器。那么在这个无线路由器的信号覆盖的范围内都可以采用WIFI连接的方式进行联网。
	 * 如果无线路由器连接了一个ADSL线路或其他的联网线路，则又被称为“热点”。
	 * 
	 * 1.ScanResult
	 * 
	 * 主要用来描述已经检测出的接入点，包括接入点的地址，接入点的名称，身份认证，频率，信号强度等信息。
	 * 
	 * 2.WifiConfiguration
	 * 
	 * Wifi网络的配置，包括安全设置等。
	 * 
	 * 3.WifiInfo
	 * 
	 * wifi无线连接的描述，包括接入点，网络连接状态，隐藏的接入点，IP地址，连接速度，MAC地址，网络ID，信号强度等信息。
	 * 这里简单介绍一下这里的方法:
	 * 
	 * getBSSID() 获取BSSID
	 * 
	 * getDetailedStateOf() 获取客户端的连通性
	 * 
	 * getHiddenSSID() 获得SSID 是否被隐藏
	 * 
	 * getIpAddress() 获取IP 地址
	 * 
	 * getLinkSpeed() 获得连接的速度
	 * 
	 * getMacAddress() 获得Mac 地址
	 * 
	 * getRssi() 获得802.11n 网络的信号
	 * 
	 * getSSID() 获得SSID
	 * 
	 * getSupplicanState() 返回具体客户端状态的信息
	 * */

}
