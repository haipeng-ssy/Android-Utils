package com.sunyiyan.application;

import java.util.ArrayList;
import java.util.List;

import com.sunyiyan.widget.DrawView;

import android.app.Application;

public class MyApplication extends Application {
	List<DrawView> list = new ArrayList<DrawView>();
	private static MyApplication instance;

	public synchronized static MyApplication getInstance() {
		if (null == instance) {
			instance = new MyApplication();
		}
		return instance;
	}
    
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	public void addDrawView(DrawView dv) {
		list.add(dv);
	}

	public List<DrawView> getDrawView() {

		return list;
	}
}
