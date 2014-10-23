package com.sunyiyan.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.sunyiyan.MainActivity;
import com.sunyiyan.R;
import com.sunyiyan.base.BaseActivity;

public class AdapterButtonForMA extends BaseAdapter {

	Context mContext;
	List<String> mList;

	public AdapterButtonForMA(Context context, List<String> list) {
		this.mContext = context;
		mList = new ArrayList<String>();
		this.mList = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if(convertView == null)
		{
	    viewHolder = new ViewHolder();
		convertView = LayoutInflater.from(mContext)
				     .inflate(R.layout.item_adapter_button_for_ma, null);
		viewHolder.mButton = (Button)convertView.findViewById(R.id.btn_for_ma);
		convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.mButton.setText(mList.get(position));
		viewHolder.mButton.setTypeface(Typeface.createFromAsset(mContext.getAssets(), "fonts/Roboto-Light.ttf"));
		viewHolder.mButton.setOnClickListener((android.view.View.OnClickListener)mContext);
		return convertView;
	}

	public class ViewHolder {
		Button mButton;
	}
}
