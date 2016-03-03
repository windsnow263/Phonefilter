package com.example.calculator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class MyAdapter extends BaseAdapter {
	
	private LinkedList<TelPhoneNum> mTelPhoneNum;
	private Context mContext;
	private AlertDialog alert= null;
	private AlertDialog.Builder alertBuilder = null;
	
	public MyAdapter(){}
	
	public MyAdapter(Context mContext, LinkedList<TelPhoneNum> mTelPhoneNum) {
		this.mContext = mContext;
		this.mTelPhoneNum = mTelPhoneNum;
	}

	@Override
	public int getCount() {
		return mTelPhoneNum.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = LayoutInflater.from(mContext).inflate(R.layout.phone_num_list,parent,false);
			holder = new ViewHolder();
			holder.num_content = (TextView) convertView.findViewById(R.id.eachTelPhoneNum);
			holder.delete_pic = (ImageButton) convertView.findViewById(R.id.deletePic);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.delete_pic.setImageResource(mTelPhoneNum.get(position).getImgId());
        holder.num_content.setText(mTelPhoneNum.get(position).getPhoneNum());
        holder.delete_pic.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				alert = null;
				alertBuilder = new AlertDialog.Builder(mContext);
				alert = alertBuilder.setTitle("提示：")
						.setMessage("您是否删除该号码?")
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								 //Toast.makeText(mContext, "已取消~", Toast.LENGTH_SHORT).show();
							}
						})
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Toast.makeText(mContext, "号码删除~", Toast.LENGTH_SHORT).show();
								remove(position);
							}
						})
						.create();
				alert.show();			
			}
        	
        });
		return convertView;
	}
	
	private class ViewHolder{
		TextView num_content;
		ImageButton delete_pic;
	}

	public void add(TelPhoneNum nTelPhoneNum){
		if(mTelPhoneNum == null){
			mTelPhoneNum = new LinkedList<TelPhoneNum>();
		}
		mTelPhoneNum.add(nTelPhoneNum);
		notifyDataSetChanged();
	}
	public void clear(){
		mTelPhoneNum.clear();
		notifyDataSetChanged();
	}
	public void remove(int position){
		if(mTelPhoneNum != null){
			mTelPhoneNum.remove(position);
		}
		notifyDataSetChanged();
	}
}
