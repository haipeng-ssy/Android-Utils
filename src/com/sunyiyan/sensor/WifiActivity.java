package com.sunyiyan.sensor;

import com.sunyiyan.R;
import com.sunyiyan.R.layout;
import com.sunyiyan.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

	import java.util.List;

	import android.app.Activity;
	import android.net.wifi.ScanResult;
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
		    // 扫描结果列表  
		    private List<ScanResult> list;  
		    private ScanResult mScanResult;  
		    private StringBuffer sb=new StringBuffer();  
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        mWifiAdmin = new WifiAdmin(WifiActivity.this);  
	        init();  
	    }
	    public void init(){
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
	    private class MyListener implements OnClickListener{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (v.getId()) {
				case R.id.scan://扫描网络
					  getAllNetWorkList();  
					break;
	           case R.id.start://打开Wifi
	        		mWifiAdmin.openWifi();
					Toast.makeText(WifiActivity.this, "当前wifi状态为："+mWifiAdmin.checkState(), 1).show();
					break;
	           case R.id.stop://关闭Wifi
					mWifiAdmin.closeWifi();
					Toast.makeText(WifiActivity.this, "当前wifi状态为："+mWifiAdmin.checkState(), 1).show();
					break;
	           case R.id.check://Wifi状态
	        	   Toast.makeText(WifiActivity.this, "当前wifi状态为："+mWifiAdmin.checkState(), 1).show();
					break;
				default:
					break;
				}
			}
	    	
	    }
	    public void getAllNetWorkList(){
	    	  // 每次点击扫描之前清空上一次的扫描结果  
	    	if(sb!=null){
	    		sb=new StringBuffer();
	    	}
	    	//开始扫描网络
	    	mWifiAdmin.startScan();
	    	list=mWifiAdmin.getWifiList();
	    	if(list!=null){
	    		for(int i=0;i<list.size();i++){
	    			//得到扫描结果
	    			mScanResult=list.get(i);
	    			sb=sb.append(mScanResult.BSSID+"  ").append(mScanResult.SSID+"   ")
	    			.append(mScanResult.capabilities+"   ").append(mScanResult.frequency+"   ")
	    			.append(mScanResult.level+"\n\n");
	    		}
	    		allNetWork.setText("扫描到的wifi网络：\n"+sb.toString());
	    	}
	    }
	}
	

	public class WifiAdmin {
	    //定义一个WifiManager对象
		private WifiManager mWifiManager;
		//定义一个WifiInfo对象
		private WifiInfo mWifiInfo;
		//扫描出的网络连接列表
		private List<ScanResult> mWifiList;
		//网络连接列表
		private List<WifiConfiguration> mWifiConfigurations;
		WifiLock mWifiLock;
		public WifiAdmin(Context context){
			//取得WifiManager对象
			mWifiManager=(WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			//取得WifiInfo对象
			mWifiInfo=mWifiManager.getConnectionInfo();
		}
		//打开wifi
		public void openWifi(){
			if(!mWifiManager.isWifiEnabled()){
				mWifiManager.setWifiEnabled(true);
			}
		}
		//关闭wifi
		public void closeWifi(){
			if(!mWifiManager.isWifiEnabled()){
				mWifiManager.setWifiEnabled(false);
			}
		}
		 // 检查当前wifi状态  
	    public int checkState() {  
	        return mWifiManager.getWifiState();  
	    }  
		//锁定wifiLock
		public void acquireWifiLock(){
			mWifiLock.acquire();
		}
		//解锁wifiLock
		public void releaseWifiLock(){
			//判断是否锁定
			if(mWifiLock.isHeld()){
				mWifiLock.acquire();
			}
		}
		//创建一个wifiLock
		public void createWifiLock(){
			mWifiLock=mWifiManager.createWifiLock("test");
		}
		//得到配置好的网络
		public List<WifiConfiguration> getConfiguration(){
			return mWifiConfigurations;
		}
		//指定配置好的网络进行连接
		public void connetionConfiguration(int index){
			if(index>mWifiConfigurations.size()){
				return ;
			}
			//连接配置好指定ID的网络
			mWifiManager.enableNetwork(mWifiConfigurations.get(index).networkId, true);
		}
		public void startScan(){
			mWifiManager.startScan();
			//得到扫描结果
			mWifiList=mWifiManager.getScanResults();
			//得到配置好的网络连接
			mWifiConfigurations=mWifiManager.getConfiguredNetworks();
		}
		//得到网络列表
		public List<ScanResult> getWifiList(){
			return mWifiList;
		}
		//查看扫描结果
		public StringBuffer lookUpScan(){
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<mWifiList.size();i++){
				sb.append("Index_" + new Integer(i + 1).toString() + ":");
				 // 将ScanResult信息转换成一个字符串包  
	            // 其中把包括：BSSID、SSID、capabilities、frequency、level  
				sb.append((mWifiList.get(i)).toString()).append("\n");
			}
			return sb;	
		}
		public String getMacAddress(){
			return (mWifiInfo==null)?"NULL":mWifiInfo.getMacAddress();
		}
		public String getBSSID(){
			return (mWifiInfo==null)?"NULL":mWifiInfo.getBSSID();
		}
		public int getIpAddress(){
			return (mWifiInfo==null)?0:mWifiInfo.getIpAddress();
		}
		//得到连接的ID
		public int getNetWordId(){
			return (mWifiInfo==null)?0:mWifiInfo.getNetworkId();
		}
		//得到wifiInfo的所有信息
		public String getWifiInfo(){
			return (mWifiInfo==null)?"NULL":mWifiInfo.toString();
		}
		//添加一个网络并连接
		public void addNetWork(WifiConfiguration configuration){
			int wcgId=mWifiManager.addNetwork(configuration);
			mWifiManager.enableNetwork(wcgId, true);
		}
		//断开指定ID的网络
		public void disConnectionWifi(int netId){
			mWifiManager.disableNetwork(netId);
			mWifiManager.disconnect();
		}
	}

}
