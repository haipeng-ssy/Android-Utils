package com.sunyiyan;

import java.util.ArrayList;
import java.util.List;

import com.sunyiyan.adapter.AdapterButtonForMA;
import com.sunyiyan.base.BaseActivity;
import com.sunyiyan.sensor.WifiActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends BaseActivity implements OnClickListener {

	private ListView mListView = null;
	AdapterButtonForMA mAdapterButtonForMA;
	Intent intent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.listView_mainActivity);

		mAdapterButtonForMA = new AdapterButtonForMA(this, getList());
		mListView.setAdapter(mAdapterButtonForMA);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Button button = (Button) arg1.findViewById(R.id.btn_for_ma);
				String text = (String) button.getText();
				startYourActivity(getActivityClass(text));
				finish();
			}
		});

	}

	public List<String> getList() {
		List<String> mList = new ArrayList<String>();
		String str[] = this.getResources().getStringArray(
				R.array.main_activity_button_text);
		for (int i = 0; i < str.length; i++) {
			mList.add(str[i]);
		}
		return mList;
	}

	public Class getActivityClass(String text) {
		Class class1 = null;
		if (text.equals("my_widget_circle")) {
			class1 = MyWidgetActivity.class;
		} else if (text.equals("VideoViewPlayer")) {
			class1 = VideoViewPlayerActivity.class;
		} else if (text.equals("canvas_bitmap_btn")) {
			class1 = CanvasWithBitmapActivity.class;
		} else if (text.equals("SurfaceViewPlayer")) {
			class1 = SurfaceViewPlayerActivity.class;
		} else if (text.equals("wifi_btn")) {
			class1 = WifiActivity.class;
		} else if (text.equals("rect_btn")) {
			class1 = CanvasAnimationRectA.class;
		}
		return class1;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	// 跳转到指定的activity
	public void startYourActivity(Class<?> yourActivity) {
		intent = new Intent(this, yourActivity);
		startActivity(intent);
	}

}
