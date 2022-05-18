package com.gjun.domain;


//JavaBean
public class AppInformation implements java.io.Serializable{
    //封裝欄位/空參數建構子/實作序列化介面/setter getter
	private String appName;
	private String copyRight;
	private String address;
	//用來識別Bean用
	private int id;

	//自訂建構子
	public AppInformation() {
		System.out.println("AppInformation物件產生");
		id=(int) (Math.random()*10000)+1;
	}
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getCopyRight() {
		return copyRight;
	}
	public void setCopyRight(String copyRight) {
		this.copyRight = copyRight;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	//要有get、set、jason才會轉換屬性
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//overriding toString
	public String toString() {
		return "識別碼:"+id;
	}
	
}
