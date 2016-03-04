package com.example.calculator;

import java.util.*;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.*;
import android.view.View.*;
import android.widget.*;


public class MainActivity extends Activity {

	private ListView phoneNum_list;
	private MyAdapter mAdapter = null;
	private List<TelPhoneNum> mTelPhoneNum = null;//号码集合
	private Context mContext = null;
	Button addTelBtn = null;
	Button clearTelBtn = null;
	private AlertDialog alert= null;
	private AlertDialog.Builder alertBuilder = null;
	private Switch mSwitch = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = MainActivity.this;
        bindViews();
        mTelPhoneNum = new LinkedList<TelPhoneNum>();
        mAdapter = new MyAdapter(mContext, (LinkedList<TelPhoneNum>)mTelPhoneNum);
        phoneNum_list.setAdapter(mAdapter);
        
        addTelBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				final LinearLayout inputNumForm = (LinearLayout)getLayoutInflater().inflate(R.layout.input_phone_num, null);
				alert = null;
				alertBuilder = new AlertDialog.Builder(mContext);
				alert = alertBuilder.setTitle("输入号码")
						.setView(inputNumForm)
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								 
							}
						})
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							//还需要做逻辑判断等
							@Override
							public void onClick(DialogInterface dialog, int which) {
								EditText et = (EditText) inputNumForm.findViewById(R.id.inputTelNum);//注意这里是inputNumForm的findViewById
								String addTelPhone = et.getText().toString();
								mAdapter.add(new TelPhoneNum(addTelPhone, R.drawable.delete_pic));
								Toast.makeText(mContext, "号码添加成功~", Toast.LENGTH_SHORT).show();
								
							}
						})
						.create();
				alert.show();
			}
        });
        
        clearTelBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {	
				alert = null;
				alertBuilder = new AlertDialog.Builder(mContext);
				alert = alertBuilder.setTitle("提示：")
						.setMessage("您是否需要清空?")
						.setNegativeButton("取消", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								 //Toast.makeText(mContext, "已取消~", Toast.LENGTH_SHORT).show();
							}
						})
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								Toast.makeText(mContext, "号码已清空~", Toast.LENGTH_SHORT).show();
								mAdapter.clear();
							}
						})
						.create();
				alert.show();
			}
        	
        });
    }
    
    private void bindViews(){
    	phoneNum_list = (ListView) findViewById(R.id.list_phoneNum);
    	addTelBtn = (Button) findViewById(R.id.addTelBtn);
    	clearTelBtn = (Button) findViewById(R.id.clearTelBtn);
    	mSwitch = (Switch) findViewById(R.id.switchView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
