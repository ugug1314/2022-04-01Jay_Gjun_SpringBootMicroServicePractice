package com.gjun.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gjun.domain.DHT;
import com.gjun.domain.DHTIoTData;
import com.gjun.domain.DhtData;
import com.gjun.domain.Message;
import com.google.gson.Gson;

//教室物聯網getway
@RestController
@PropertySource("classpath:appurl.properties") //註冊Resource file
public class IoTController {
	//Property Injectino
	@Value("${iot.posturl}")
	private String postURL;
	@RequestMapping(path="/iot/dht/add",method= {RequestMethod.POST}
	,consumes="application/json",produces="application/json")
	public String dhtSender(@RequestBody DhtData data) {
		//串接CHT IoT服務(HttpClient/Http Request/Header???/body JSON
		String urlString=String.format(postURL, "29010894557");
		// 1.使用HttpClient相關工廠產生一個HttpClient物件,
		// 使用介面多型化，保持向前向後相容性考量
		HttpClient client=HttpClients.createDefault();
		HttpPost request=new HttpPost();  //request物件
		try {
			request.setURI(new URI(urlString));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		//Http Request Header(Content-Type:application/json and CK:xxxxxxx)
		request.setHeader("Content-Type","application/json");
		request.setHeader("CK","PKC7WA9EC53H12SFH7");
		
		//TODO 建構DHT 整理Http Request Body HttpEntity(or Http content)
		
		DHT dht=new DHT();//溫度、濕度、地點資訊，其實可以直接使用DhtData類別來處理
	    dht.temper=data.getTemper();
	    dht.humi=data.getHumi();
	    dht.location=data.getLocation();
	    //序列化成字串
	    Gson gson=new Gson();
	    String dhtString=gson.toJson(dht); //轉換成Json
	    //建構DHTIotData物件(感測器)
	    DHTIoTData dhtIot=new DHTIoTData();
	    dhtIot.id="DHT22";//感測器編號
	    dhtIot.save=true;
	    
	    dhtIot.value=new String[] {dhtString}; //將前端收備而的資訊建立成為感測器的值
	    //對應中華電信iot的感測器jaon，最外面是一個json array[...]
		ArrayList<DHTIoTData> list=new ArrayList<DHTIoTData>();
		list.add(dhtIot); //做成jason array
		String jsonData=gson.toJson(list);
		//Request->Content->HttpEntity->StringEntity
		StringEntity entity;
		try {
			entity = new StringEntity(jsonData);
			request.setEntity(entity); //正式發出請求
			client.execute(request);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonData;
		
	}

}
