package com.example.calculator;

public class TelPhoneNum {
	private String phoneNum;
	private int imgId;
	
	public TelPhoneNum(){}
	
	public TelPhoneNum(String num, int imgId){
		this.phoneNum = num;
		this.imgId = imgId;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	
}
