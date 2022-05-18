package com.gjun.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LineWebHookReceviceController {
	//以屬性的方式注貨request也是可行的，但每次進來都會產生一個request，合不合宜有待探討
	//但如以參數的方式來用request的話沒這問題。
	/*@Autowired
	private HttpServletRequest request; -->自行取消註解測試*/ 
    // callback 進行處理方法 採用Servlet API(Java Web底層環境) MIME Type
	@PostMapping(path="/webhook/rawdata",produces = "application/json")
	public String receiveMessageProcess(HttpServletRequest request) {
		//採用底層寫法 取出串流
		String content=null;
		try {
			ServletInputStream is=request.getInputStream();
			//使用Decorator設計模式進行讀取字串
			InputStreamReader reader=new InputStreamReader(is,"UTF-8");
			//建構Buffered
			BufferedReader buff=new BufferedReader(reader);
			StringBuilder builder=new StringBuilder();
			String line=null;
			while((line=buff.readLine())!=null) {
				builder.append(line);
			}
			content=builder.toString();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return content;
	}
	
}
